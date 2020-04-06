package com.all_latest_in_android.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.all_latest_in_android.R;
import com.all_latest_in_android.databinding.ActivityMainBinding;
import com.all_latest_in_android.ui.base.BaseActivity;
import com.all_latest_in_android.ui.headline.HeadlineFragment;
import com.all_latest_in_android.utils.FragmentUtils;
import static com.all_latest_in_android.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, HeadlineFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
