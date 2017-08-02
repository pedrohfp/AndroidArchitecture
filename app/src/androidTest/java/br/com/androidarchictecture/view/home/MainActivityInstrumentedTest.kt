package br.com.androidarchictecture.view.home

import android.support.test.espresso.Espresso
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.v7.widget.RecyclerView


import br.com.androidarchictecture.R
import kotlinx.android.synthetic.main.fragment_list_characters.*
import org.junit.After
import org.junit.Before


/**
 * Created by pedrohenrique on 01/08/17.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest{

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var mIdlingResource: IdlingResource

    @Before
    fun setup(){
        mIdlingResource = mActivityRule.activity.getIdlingResource()
        Espresso.registerIdlingResources(mIdlingResource)
    }

    @Test
    fun scrollingList(){
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19, scrollTo()))
    }


    @After
    fun unregisterIdlingResource(){
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource)
        }
    }

}
