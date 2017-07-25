package br.com.androidarchictecture.model

import android.util.Log
import br.com.androidarchictecture.model.rest.Authenticator
import br.com.androidarchictecture.model.rest.CharacterApi
import br.com.androidarchictecture.model.schedulers.Schedulers
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.view.home.contract.CharacterInteractorContract
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 20/07/17.
 */
class CharacterInteractor: CharacterInteractorContract{

    var mRetrofit: Retrofit

    constructor(retrofit: Retrofit){
        this.mRetrofit = retrofit
    }

    override fun loadCharacters(page: Int): Observable<MutableList<Character>>{

        return Observable.create {
            e: ObservableEmitter<MutableList<Character>> ->
                        var authenticator = Authenticator.instance

            try {

                var characterApi = mRetrofit.create(CharacterApi::class.java)


                var callBody = characterApi.loadCharacters(
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash(),
                        page.toString())

                var responseBody = callBody.execute()

                if (responseBody.isSuccessful) {

                    val jsonObject = JSONObject(responseBody.body()?.string())
                    val data = jsonObject.getJSONObject("data")
                    val result = data.getJSONArray("results")

                    val characterList: MutableList<Character> = mutableListOf()

                    for(i in 0..(result.length() - 1)){
                        val characterObject = result.getJSONObject(i)
                        val id = characterObject.getLong("id")
                        val name = characterObject.getString("name")
                        val description = characterObject.getString("description")

                        val thumbnail = characterObject.getJSONObject("thumbnail")
                        val path = thumbnail.getString("path")
                        val extension = thumbnail.getString("extension")

                        val image = path + "/standard_medium." + extension

                        val character = Character(id, name, image, description)

                        characterList.add(character)
                    }

                    e.onNext(characterList)
                    e.onComplete()
                }
            }catch (erro: Exception){
                erro.printStackTrace()
            }
        }
    }


}