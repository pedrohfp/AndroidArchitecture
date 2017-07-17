package br.com.androidarchictecture.view.application

import android.app.Application

/**
 * Created by pedrohenrique on 14/07/17.
 */

class MarvelApplication: Application(){

    var mAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        mAppComponent = initDagger(this)
    }

    fun getAppComponent(): AppComponent {
        return mAppComponent!!
    }

    protected fun initDagger(application: MarvelApplication): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .networkModule(NetworkModule("https://secure.moneto.com"))
                .build()
    }
}