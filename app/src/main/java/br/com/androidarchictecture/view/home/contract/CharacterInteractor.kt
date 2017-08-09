package br.com.androidarchictecture.view.home.contract

import br.com.androidarchictecture.pojo.*
import io.reactivex.Observable
import io.reactivex.ObservableSource

/**
 * Created by pedrohenrique on 24/07/17.
 */
interface CharacterInteractor {
    fun loadCharacters(page: Int, search: String): Observable<MutableList<Character>>
    fun loadCharacterDetails(id: Long): Observable<Character>
    fun loadCharacterBasicDetails(id: Long): ObservableSource<Character>
    fun loadCharacterComics(id: Long): ObservableSource<MutableList<Comic>>
    fun loadCharacterEvents(id: Long): ObservableSource<MutableList<Event>>
    fun loadCharacterSeries(id: Long): ObservableSource<MutableList<Serie>>
    fun loadCharacterStories(id: Long): ObservableSource<MutableList<Storie>>

}