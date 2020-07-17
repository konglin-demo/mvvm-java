# mvvm-java
采用mvvm框架、retrofit+okhttp+rxjava网络请求、navigation页面迁移，并做保留前一个页面的状态。
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

上述方式会导致系统按下返回键时不调用popStackBack(),使其销毁的画页无法退栈，导致在进行迁移时，取到的栈顶是没有销毁的画页，其将要迁移的action在当前栈顶不存在，如果使用当前页的action进行迁移则会在naviController中无法找到此destinationId。所以建议，使其创建是基于宿主NavHostFragment的方式，使用继承NavHostFragment中的onCreateNavController(...)的方式对添加navigator，并且在activiy.xml配置宿主是继承NavHostFragment的子类。

## 安全方式的保留上一个页面状态，创建fxfragment不变，重写NavHostFragment
```
import androidx.navigation.fragment.NavHostFragment;

import com.test.aap.R;

public class FixNavHostFragment extends NavHostFragment {
    @Override
    protected void onCreateNavController( @NonNull NavController navController ) {
        super.onCreateNavController(navController);
        navController.getNavigatorProvider().addNavigator( new FixFragmentNavigator(getContext(), getChildFragmentManager(),getContainerId()));
    }

    private int getContainerId() {
        int id = getId();
        if (id != 0 && id != View.NO_ID) {
            return id;
        }
        // Fallback to using our own ID if this Fragment wasn't added via
        // add(containerViewId, Fragment)
        return R.id.nav_host_fragment_container;
    }
}

activity.xml中
<fragment
    android:id="@+id/fragment"
    android:name="com.test.aap.base.FixNavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav"/>
```


