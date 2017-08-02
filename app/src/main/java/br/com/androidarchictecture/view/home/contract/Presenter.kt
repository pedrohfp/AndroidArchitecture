package br.com.androidarchictecture.view.home.contract

import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.base.BasePresenter

/**
 * Created by pedrohenrique on 13/07/17.
 */
interface Presenter : BasePresenter {
     fun loadCharacters(page: Int, idlingResource: SimpleIdlingResource?)
}