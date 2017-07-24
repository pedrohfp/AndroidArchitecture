package br.com.androidarchictecture.model

import android.util.Log
import br.com.androidarchictecture.model.rest.Authenticator
import br.com.androidarchictecture.model.rest.CharacterApi
import br.com.androidarchictecture.model.schedulers.Schedulers
import br.com.androidarchictecture.pojo.Character
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import okhttp3.ResponseBody
import retrofit2.Call
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

        return Observable.create {
            e: ObservableEmitter<Character> ->
                        var authenticator = Authenticator.instance

            try {

                var characterApi = mRetrofit.create(CharacterApi::class.java)


                var callBody = characterApi.loadCharacters(
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash())

                var responseBody = callBody.execute()

                if (responseBody.isSuccessful) {
                    Log.e("CODE: ", responseBody.code().toString())
                    e.onComplete()
                }
            }catch (erro: Exception){
                erro.printStackTrace()
            }
        }
    }


}