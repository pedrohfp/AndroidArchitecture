package br.com.androidarchictecture.model

import br.com.androidarchictecture.pojo.Character
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 20/07/17.
 */
class CharacterInteractor{

    var mRetrofit: Retrofit

    constructor(retrofit: Retrofit){
        this.mRetrofit = retrofit
    }

    fun loadCharacter(): Observable<Character>{
        return Observable.create({ e: ObservableEmitter<Character> ->
            
        })
    }


}