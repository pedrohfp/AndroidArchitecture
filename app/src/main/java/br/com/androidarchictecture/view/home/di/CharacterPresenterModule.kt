package br.com.androidarchictecture.view.home.di

import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import dagger.Module
import dagger.Provides

/**
 * Created by pedrohenrique on 15/07/17.
 */

@Module
class CharacterPresenterModule{

    val mActivityView: ActivityView
    val mListCharacterView: ListCharactersView

    constructor(mActivityView: ActivityView, mListCharacterView: ListCharactersView) {
        this.mActivityView = mActivityView
        this.mListCharacterView = mListCharacterView
    }

    @FragmentScoped
    @Provides
    fun provideActivityView(): ActivityView{
        return mActivityView
    }

    @FragmentScoped
    @Provides
    fun provideListCharacterView(): ListCharactersView{
        return mListCharacterView
    }
}