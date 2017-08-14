package br.com.androidarchictecture.view.details

import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

/**
 * Created by pedrohenrique on 08/08/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class CharacterDetailsActivityTest{
    lateinit var mPresenter: DetailsPresenter
    lateinit var mActivity: CharacterDetailsActivity

    @Before
    fun setup(){
        mPresenter = mock()

        var controller: ActivityController<CharacterDetailsActivity> = Robolectric.buildActivity(CharacterDetailsActivity::class.java).create()
        mActivity = controller.get()
        mActivity.setPresenter(mPresenter)
        controller.resume()
    }

    @Test
    fun testLoadCharacterDetailsInitiate(){
        verify(mPresenter).loadDetailsCharacter(any(), any())
    }
}
