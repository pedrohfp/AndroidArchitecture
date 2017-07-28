package br.com.androidarchictecture.application

import android.app.Application
import br.com.androidarchictecture.dagger.DaggerTestComponent
import br.com.androidarchictecture.dagger.TestComponent
import br.com.androidarchictecture.dagger.TestModule
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.application.di.AppModule
import br.com.androidarchictecture.view.application.di.DaggerAppComponent
import br.com.androidarchictecture.view.application.di.NetworkModule

/**
 * Created by pedrohenrique on 27/07/17.
 */
class TestApplication: MarvelApplication() {

    companion object{
        @JvmStatic lateinit var mTestComponent: TestComponent
    }

    override fun onCreate() {
        super.onCreate()

        mTestComponent = initTestDagger()
    }

    fun initTestDagger(): TestComponent {
        return DaggerTestComponent.builder()
                .testModule(TestModule(this))
                .networkModule(NetworkModule("https://gateway.marvel.com/"))
                .build()
    }
}