package br.com.androidarchictecture.view.home.contract

import android.support.test.espresso.IdlingResource
import br.com.androidarchictecture.view.base.BaseActivity

/**
 * Created by pedrohenrique on 17/04/17.
 */

abstract class ActivityView : BaseActivity<Presenter>() {
    abstract fun getIdlingResource(): IdlingResource
}
