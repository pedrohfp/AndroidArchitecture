package br.com.androidarchictecture.view.application

import android.content.Context
import br.com.androidarchictecture.view.home.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 14/07/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent{
    fun getContext(): Context
}