# mvvm-java
采用mvvm框架、retrofit+okhttp+rxjava网络请求、navigation页面迁移并且实现隐藏和消去。
## navigation返回保留上一个页面的状态的实现方案
其方法有两种，一是保留前一个页面创建的view 二是重写fragmentNavigator 重新创建一个navigator。现在介绍第二种
* 首先创建一个类FixFragmentNavigator使其继承FragmentNavigator，重写父类的navigate方法，将父类的拷贝过来，修改其中instantiateFragment方法和ft.replace()方法.
```
final FragmentTransaction ft = mFragmentManager.beginTransaction();
String className = destination.getClassName();
if (className.charAt(0) == '.') {
    className = mContext.getPackageName() + className;
}
// 获取当前显示的fragment
Fragment curFrag = mFragmentManager.getPrimaryNavigationFragment();
if (null != curFrag){
    ft.hide(curFrag);
}
Fragment frag = mFragmentManager.findFragmentByTag(className);
if (null == frag){
    frag = instantiateFragment(mContext, mFragmentManager, classNameargs);
    ft.add(mContainerId,frag,className);
    frag.setArguments(args);
}else {
    ft.show(frag);
}
```
* 拷贝父类的generateBackStackName(...)方法到子类，mBackStack在父类是私有的方式，所以通过反射的方式将其拿到
```
ArrayDeque<Integer> mBackStack;
try {
    Field field = FragmentNavigator.class.getDeclaredField("mBackStack");
    field.setAccessible(true);
    mBackStack = (ArrayDeque<Integer>) field.get(this);
} catch (Exception e) {
    e.printStackTrace();
    return null;
}
```
* 修改MainAcitity中的fragment控件，将其navGraph删掉，使其在activity将navi.xml 和自定义的FixFragmentNavigator进行绑定。
```
// MainActivity
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    NavController navController = Navigation.findNavController(thisR.id.fragment);
    Fragment fragment = getSupportFragmentManager().findFragmentByI(R.id.fragment);
    // 创建FixFragmentNavigator对象，addNavigator加入到Navigator
    FixFragmentNavigator fixFragmentNavigator = new FixFragmentNavigato(this, getSupportFragmentManager(), fragment.getId());
    navController.getNavigatorProvider().addNavigator(
            fixFragmentNavigator);
    navController.setGraph(R.navigation.nav);
}
```
* 在Navi.xml中修改其属性，将fragment都修改为FixFragmentNavigator定义的fragment的Name即fixFragment。
```
<fixFragment
    android:id="@+id/mainFragment"
    android:name="com.test.aap.MainFragment"
    android:label="fragment_main"
    tools:layout="@layout/fragment_main" >
    <action
        android:id="@+id/action_mainFragment_to_firstFragment"
        app:destination="@id/firstFragment" />
    <action
        android:id="@+id/action_mainFragment_to_secondFragment"
        app:destination="@id/secondFragment" />
</fixFragment>
```


