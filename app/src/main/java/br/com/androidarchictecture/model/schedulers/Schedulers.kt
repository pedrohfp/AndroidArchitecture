package br.com.androidarchictecture.model.schedulers

/**
 * Created by Pedro on 13/07/2017.
 */

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object Schedulers {

    private var netScheduler: Scheduler? = null

    fun network(): Scheduler? {
        if (netScheduler == null) {
            netScheduler = io.reactivex.schedulers.Schedulers.newThread()
        }

        return netScheduler
    }

    fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    fun db(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    fun computation(): Scheduler {
        return io.reactivex.schedulers.Schedulers.computation()
    }

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
