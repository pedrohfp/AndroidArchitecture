package br.com.androidarchictecture.view.home

import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.home.contract.Presenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.mockito.Mockito.*
import org.robolectric.android.controller.ActivityController


/**
 * Created by pedrohenrique on 31/07/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest{

    @Mock
    lateinit var mPresenter: Presenter

    lateinit var activity: MainActivity

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        var controller: ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity::class.java).create()
        activity = controller.get()
        activity.setPresenter(mPresenter)
        controller.resume()

    }

    @Test
    fun testLoadCharactersInitiate(){
        verify(mPresenter).loadCharacters(ArgumentMatchers.anyInt(), eq(activity.getIdlingResource()))
    }


}