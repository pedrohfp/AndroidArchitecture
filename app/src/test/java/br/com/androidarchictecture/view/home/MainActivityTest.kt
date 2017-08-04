package br.com.androidarchictecture.view.home

import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.pojo.StringWord
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.home.contract.Presenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.android.controller.ActivityController


/**
 * Created by pedrohenrique on 31/07/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest{

    lateinit var mPresenter: Presenter

    lateinit var activity: MainActivity

    lateinit var mString: StringWord

    @Before
    fun setup(){

        mPresenter = mock()
        mString = StringWord()
        mString.word = ""

        var controller: ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity::class.java).create()
        activity = controller.get()
        activity.setPresenter(mPresenter)
        controller.resume()

    }

    @Test
    fun testLoadCharactersInitiate(){
        verify(mPresenter).loadCharacters(any(), eq(activity.getIdlingResource()), eq(mString.word))
    }


}