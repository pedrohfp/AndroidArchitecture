package br.com.androidarchictecture.dagger

import br.com.androidarchictecture.view.application.di.AppModule
import br.com.androidarchictecture.view.application.di.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 27/07/17.
 */
@Singleton
@Component(modules = arrayOf(TestModule::class, NetworkModule::class))
interface TestComponent{
    fun getRetrofit(): Retrofit
}
