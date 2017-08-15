package br.com.androidarchictecture.util

import android.support.annotation.Nullable
import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by pedrohenrique on 01/08/17.
 */
class DetailsIdlingResource: IdlingResource {

    @Volatile private var mCallback: IdlingResource.ResourceCallback? = null

    //Idleness is controlled with this boolean
    private var mIsIdleNow: AtomicBoolean = AtomicBoolean(true)

    override fun getName(): String {
        return "Details"
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        mCallback = callback
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */

    fun setIdleState(isIdleNow: Boolean){
        mIsIdleNow.set(isIdleNow)
        if(isIdleNow && mCallback != null){
            mCallback!!.onTransitionToIdle()
        }
    }

}
