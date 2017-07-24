package br.com.androidarchictecture.view.home.contract

import br.com.androidarchictecture.pojo.Character
import io.reactivex.Observable

/**
 * Created by pedrohenrique on 24/07/17.
 */
interface CharacterInteractorContract {
    fun loadCharacters(): Observable<Character>
}