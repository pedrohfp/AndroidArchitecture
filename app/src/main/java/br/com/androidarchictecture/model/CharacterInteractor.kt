package br.com.androidarchictecture.model

import android.util.Log
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 20/07/17.
 */
class CharacterInteractor{

    var mRetrofit: Retrofit

    constructor(retrofit: Retrofit){
        this.mRetrofit = retrofit
    }

    fun loadCharacter(){
        Log.e("CharacterInteractor: ", "Success")
    }


}