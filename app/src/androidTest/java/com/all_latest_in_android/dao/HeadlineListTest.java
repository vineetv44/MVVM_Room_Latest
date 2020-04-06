package com.all_latest_in_android.dao;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.all_latest_in_android.R;
import com.all_latest_in_android.ui.MainActivity;
import com.all_latest_in_android.ui.headline.HeadlineFragment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HeadlineListTest {

    @Rule
    //Activity Test Rule for the activity which contains my headline fragment
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        //Initial setup method to load my fragment in the activity before beginning to test
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new HeadlineFragment()).commit();
    }

    @Test
    public void OnRecyclerViewClick() throws InterruptedException {
        //Perform type action on the view with id as email
        Espresso.onView(withId(R.id.recyclerView)).perform(click());
    }
}
