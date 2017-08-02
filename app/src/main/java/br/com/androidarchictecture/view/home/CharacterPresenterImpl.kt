package br.com.androidarchictecture.view.home

import br.com.androidarchictecture.model.schedulers.Schedulers
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by pedrohenrique on 13/07/17.
 */
class CharacterPresenterImpl: Presenter {

    var mActivityView: ActivityView
    var mListCharactersView: ListCharactersView
    var mCharacterInteractor: CharacterInteractor

    @Inject
    constructor(mActivityView: ActivityView,
                mListCharactersView: ListCharactersView,
                mCharacterInteractor: CharacterInteractor) {
        this.mActivityView = mActivityView
        this.mListCharactersView = mListCharactersView
        this.mCharacterInteractor = mCharacterInteractor

        mActivityView.setPresenter(this)
        mListCharactersView.setPresenter(this)
    }

    override fun loadCharacters(page: Int, idlingResource: SimpleIdlingResource?) {

        if(idlingResource != null){
            idlingResource.setIdleState(false)
        }

        var subscriptions = CompositeDisposable()

        val subscriber = mCharacterInteractor.loadCharacters(page)
                .subscribeOn(Schedulers.network())
                .observeOn(Schedulers.ui())
                .subscribe(
                        { characters: MutableList<Character> ->
                                mListCharactersView.loadCharacters(characters, idlingResource)
                        },
                        { e ->
                                mListCharactersView.showMessageLoadFailed()
                        }
                )
        subscriptions.add(subscriber)
    }

}