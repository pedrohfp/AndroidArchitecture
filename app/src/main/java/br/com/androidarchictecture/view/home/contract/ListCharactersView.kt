package br.com.androidarchictecture.view.home.contract

import br.com.androidarchictecture.view.base.BaseView
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource

/**
 * Created by pedrohenrique on 13/07/17.
 */
interface ListCharactersView : BaseView<Presenter>{
     fun loadCharacters(characters: MutableList<Character>, idlingResource: SimpleIdlingResource?)
     fun showMessageLoadFailed()
}