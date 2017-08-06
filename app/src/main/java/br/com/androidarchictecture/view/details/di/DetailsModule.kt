package br.com.androidarchictecture.view.details.di

import br.com.androidarchictecture.util.FragmentScoped
import br.com.androidarchictecture.view.details.DetailsPresenterImpl
import br.com.androidarchictecture.view.details.contract.DetailsActivityView
import br.com.androidarchictecture.view.details.contract.DetailsCharacterView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by pedrohenrique on 06/08/17.
 */

@Module
class DetailsModule{

    val mDetailsActivityView: DetailsActivityView
    val mDetailsCharacterView: DetailsCharacterView

    constructor(mDetailsActivityView: DetailsActivityView,
                mDetailsCharacterView: DetailsCharacterView) {
        this.mDetailsActivityView = mDetailsActivityView
        this.mDetailsCharacterView = mDetailsCharacterView
    }

    @FragmentScoped
    @Provides
    fun provideDetailsActivityView(): DetailsActivityView{
        return mDetailsActivityView
    }

    @FragmentScoped
    @Provides
    fun provideDetailsCharacterView(): DetailsCharacterView{
        return mDetailsCharacterView
    }

    @FragmentScoped
    @Provides
    fun provideDetailsPresenter(presenter: DetailsPresenterImpl): DetailsPresenter{
        return presenter
    }
}