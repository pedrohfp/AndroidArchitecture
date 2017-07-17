package br.com.androidarchictecture.view.home

import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.application.AppComponent
import dagger.Component

/**
 * Created by pedrohenrique on 15/07/17.
 */
@FragmentScoped
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(CharacterPresenterModule::class))
interface CharacterComponent{
    fun inject(main: MainActivity)
}