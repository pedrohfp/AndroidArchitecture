package br.com.androidarchictecture.view.application.di

import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 14/07/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, InteractorModule::class))
interface AppComponent{
    fun getRetrofit(): Retrofit
    fun getCharacterInteractor(): CharacterInteractor
}