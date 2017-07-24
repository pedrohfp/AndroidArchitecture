package br.com.androidarchictecture.view.home

import android.util.Log
import br.com.androidarchictecture.model.CharacterInteractor
import br.com.androidarchictecture.model.schedulers.Schedulers
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.CharacterInteractorContract
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by pedrohenrique on 13/07/17.
 */
class CharacterPresenter: Presenter {

    var mActivityView: ActivityView
    var mListCharactersView: ListCharactersView

    @Inject
    lateinit var mCharacterInteractor: CharacterInteractorContract

    @Inject
    constructor(mActivityView: ActivityView, mListCharactersView: ListCharactersView) {
        this.mActivityView = mActivityView
        this.mListCharactersView = mListCharactersView

        mListCharactersView.setPresenter(this)
    }

    override fun start() {

        var subscriptions = CompositeDisposable()

        val subscriber = mCharacterInteractor.loadCharacters()
                .subscribeOn(Schedulers.network())
                .observeOn(Schedulers.ui())
                .subscribe(
                        { character: Character ->

                        },
                        { e ->

                        }
                )
        subscriptions.add(subscriber)


    }
    override fun finish() {
        mActivityView.finish()
    }

}