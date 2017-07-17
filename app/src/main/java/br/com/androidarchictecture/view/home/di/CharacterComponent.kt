package br.com.androidarchictecture.view.home.di

import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.home.MainActivity
import dagger.Component

/**
 * Created by pedrohenrique on 15/07/17.
 */
@FragmentScoped
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(CharacterPresenterModule::class))
interface CharacterComponent{
    fun inject(main: MainActivity)
}