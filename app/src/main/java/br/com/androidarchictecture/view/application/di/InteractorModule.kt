package br.com.androidarchictecture.view.application.di

import br.com.androidarchictecture.model.CharacterInteractorImpl
import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 06/08/17.
 */

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun provideCharacterInteractorImpl(retrofit: Retrofit): CharacterInteractorImpl {
        return CharacterInteractorImpl(retrofit)
    }

    @Singleton
    @Provides
    fun provideCharacterInteractor(interactor: CharacterInteractorImpl): CharacterInteractor {
        return interactor
    }
}