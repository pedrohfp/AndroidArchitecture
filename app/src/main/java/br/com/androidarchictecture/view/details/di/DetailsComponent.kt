package br.com.androidarchictecture.view.details.di

import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.application.di.AppComponent
import br.com.androidarchictecture.view.details.CharacterDetailsActivity
import br.com.androidarchictecture.view.home.MainActivity
import br.com.androidarchictecture.view.home.di.CharacterListModule
import dagger.Component

/**
 * Created by pedrohenrique on 06/08/17.
 */
@FragmentScoped
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(DetailsModule::class))
interface DetailsComponent{
    fun inject(details: CharacterDetailsActivity)
}