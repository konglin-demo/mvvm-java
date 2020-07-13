package com.test.aap.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
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
