package br.com.androidarchictecture.view.home

import android.util.Log
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import javax.inject.Inject

/**
 * Created by pedrohenrique on 13/07/17.
 */
class CharacterPresenter: Presenter {

    var mActivityView: ActivityView
    var mListCharactersView: ListCharactersView

    @Inject
    constructor(mActivityView: ActivityView, mListCharactersView: ListCharactersView) {
        this.mActivityView = mActivityView
        this.mListCharactersView = mListCharactersView

        Log.e("CharacterPresenter: ", "ESTA DANDO CERTO PORRA")

        mListCharactersView.setPresenter(this)
    }

    override fun start() {

    }

    override fun finish() {
        mActivityView.finish()
    }

}