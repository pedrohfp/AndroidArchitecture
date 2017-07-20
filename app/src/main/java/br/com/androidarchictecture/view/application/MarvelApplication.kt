package br.com.androidarchictecture.view.application

import android.app.Application
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.application.di.AppModule
import br.com.androidarchictecture.view.application.di.DaggerAppComponent
import br.com.androidarchictecture.view.application.di.NetworkModule

/**
 * Created by pedrohenrique on 14/07/17.
 */

class MarvelApplication: Application(){

    companion object{
       @JvmStatic lateinit var mAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent = initDagger(this)
    }

    protected fun initDagger(application: MarvelApplication): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .networkModule(NetworkModule("https://secure.moneto.com"))
                .build()
    }
}