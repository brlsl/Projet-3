
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ActivityListNeighbour;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ActivityListNeighbour mActivity;

    @Rule
    public ActivityTestRule<ActivityListNeighbour> mActivityRule =
            new ActivityTestRule(ActivityListNeighbour.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbour))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbour)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbour))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbour)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void activityNeighbourDetail_isLaunched() {
        onView(withId(R.id.list_neighbour))
                .perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void activityNeighbourDetail_userNameIsDisplayed(){
        onView(withId(R.id.list_neighbour))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.neighbour_name_avatar))
                .check(matches(isDisplayed()));

    }

    @Test
    public void favoriteFragmentOnlyDisplaysFavoriteNeighbour(){
        onView(withId(R.id.list_neighbour))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.add_favorite_floating_btn))
                .perform(click());
        onView(withId(R.id.back_button))
                .perform(click());
        onView(withId(R.id.list_favorite))
                .check(matches(hasMinimumChildCount(1)));
    }

}