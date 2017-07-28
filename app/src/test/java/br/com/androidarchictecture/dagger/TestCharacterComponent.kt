package br.com.androidarchictecture.dagger

import br.com.androidarchictecture.CharacterPresenterTest
import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.home.MainActivity
import br.com.androidarchictecture.view.home.di.CharacterPresenterModule
import dagger.Component

/**
 * Created by pedrohenrique on 27/07/17.
 */
@FragmentScoped
@Component(dependencies = arrayOf(TestComponent::class), modules = arrayOf(CharacterPresenterModule::class))
interface TestCharacterComponent{
    fun inject(test: CharacterPresenterTest)
}