package br.com.androidarchictecture.view.application

import android.app.Application
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.application.di.AppModule
import br.com.androidarchictecture.view.application.di.DaggerAppComponent
import br.com.androidarchictecture.view.application.di.NetworkModule

/**
 * Created by pedrohenrique on 14/07/17.
 */

open class MarvelApplication: Application(){

    companion object{
       @JvmStatic lateinit var mAppComponent: AppComponent
       @JvmStatic val mBaseUrl: String = "https://gateway.marvel.com/"
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent = initDagger()
    }

    open protected fun initDagger(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(mBaseUrl))
                .build()
    }
}