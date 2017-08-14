package br.com.androidarchictecture.view.details.contract

import br.com.androidarchictecture.view.base.BaseView
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource

/**
 * Created by pedrohenrique on 06/08/17.
 */
interface DetailsCharacterView: BaseView<DetailsPresenter> {
    fun showCharacterDetails(character: Character, idlingResource: SimpleIdlingResource)
    fun showMessageLoadFailed()
}