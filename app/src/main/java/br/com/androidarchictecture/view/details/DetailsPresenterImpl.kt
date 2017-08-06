package br.com.androidarchictecture.view.details

import br.com.androidarchictecture.view.details.contract.DetailsActivityView
import br.com.androidarchictecture.view.details.contract.DetailsCharacterView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import javax.inject.Inject

/**
 * Created by pedrohenrique on 06/08/17.
 */
class DetailsPresenterImpl: DetailsPresenter{

    var mDetailsActivityView: DetailsActivityView
    var mDetailsCharacterView: DetailsCharacterView
    var mCharacterInteractor: CharacterInteractor

    @Inject
    constructor(mDetailsActivityView: DetailsActivityView,
                mDetailsCharacterView: DetailsCharacterView,
                mCharacterInteractor: CharacterInteractor) {
        this.mDetailsActivityView = mDetailsActivityView
        this.mDetailsCharacterView = mDetailsCharacterView
        this.mCharacterInteractor = mCharacterInteractor

        mDetailsActivityView.setPresenter(this)
        mDetailsCharacterView.setPresenter(this)
    }
}