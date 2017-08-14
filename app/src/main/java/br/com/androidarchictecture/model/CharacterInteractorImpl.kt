package br.com.androidarchictecture.model

import br.com.androidarchictecture.model.rest.Authenticator
import br.com.androidarchictecture.model.rest.CharacterApi
import br.com.androidarchictecture.pojo.*
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableSource
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 20/07/17.
 */
class CharacterInteractorImpl : CharacterInteractor {


    var mRetrofit: Retrofit

    constructor(retrofit: Retrofit){
        this.mRetrofit = retrofit
    }

    override fun loadCharacters(page: Int, search: String): Observable<MutableList<Character>>{

        return Observable.create {
            e: ObservableEmitter<MutableList<Character>> ->
                        var authenticator = Authenticator.instance

            try {

                var characterApi = mRetrofit.create(CharacterApi::class.java)

                var callBody: Call<ResponseBody>?

                if(search.isEmpty()) {

                    callBody = characterApi.loadCharacters(
                            authenticator.generanteTimestamp(),
                            authenticator.mApiKey,
                            authenticator.generateHash(),
                            page.toString())
                }else{

                    callBody = characterApi.loadCharactersBySearch(
                            authenticator.generanteTimestamp(),
                            authenticator.mApiKey,
                            authenticator.generateHash(),
                            page.toString(),
                            search)
                }

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

                        val character = Character(id, name, image, description, mutableListOf(), mutableListOf(), mutableListOf())

                        characterList.add(character)
                    }

                    e.onNext(characterList)
                    e.onComplete()
                }
            }catch (erro: Exception){
                erro.printStackTrace()
                e.onError(erro)
            }
        }
    }

    override fun loadCharacterDetails(id: Long): Observable<Character> {

        val basicDetails = loadCharacterBasicDetails(id)
        val comics = loadCharacterComics(id)
        val events = loadCharacterEvents(id)
        val series = loadCharacterSeries(id)

        return Observable.zip(basicDetails, comics, events, series, Function4 { details, comics, events, series ->
             Character(details.mId,
                     details.mName,
                     details.mThumbnail,
                     details.mDescription,
                     comics,
                     events,
                     series)
        })
    }

    override fun loadCharacterBasicDetails(id: Long): ObservableSource<Character> {
        return Observable.create {
            e: ObservableEmitter<Character> ->

            var authenticator = Authenticator.instance

            try{
                var characterApi = mRetrofit.create(CharacterApi::class.java)

                var callBody = characterApi.loadCharacterBasicDetails(
                        id.toString(),
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash())

                var responseBody = callBody.execute()

                if(responseBody.isSuccessful){
                    val jsonObject = JSONObject(responseBody.body()?.string())
                    val data = jsonObject.getJSONObject("data")
                    val result = data.getJSONArray("results")
                    val characterObject = result.getJSONObject(0)

                    val id = characterObject.getLong("id")
                    val name = characterObject.getString("name")
                    val description = characterObject.getString("description")

                    val thumbnail = characterObject.getJSONObject("thumbnail")
                    val path = thumbnail.getString("path")
                    val extension = thumbnail.getString("extension")

                    val image = path + "." + extension

                    val character = Character(id, name, image, description, mutableListOf(), mutableListOf(), mutableListOf())

                    e.onNext(character)
                    e.onComplete()
                }
            }catch(erro:Exception){
                erro.printStackTrace()
                e.onError(erro)
            }
        }
    }

    override fun loadCharacterComics(id: Long): ObservableSource<MutableList<Comic>> {
        return Observable.create {
            e: ObservableEmitter<MutableList<Comic>> ->

            var authenticator = Authenticator.instance

            try{
                var characterApi = mRetrofit.create(CharacterApi::class.java)

                var callBody = characterApi.loadCharacterComics(
                        id.toString(),
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash())

                var responseBody = callBody.execute()

                if (responseBody.isSuccessful){
                    val jsonObject = JSONObject(responseBody.body()?.string())
                    val data = jsonObject.getJSONObject("data")
                    val result = data.getJSONArray("results")

                    val comicsList: MutableList<Comic> = mutableListOf()

                    for(i in 0..(result.length() - 1)) {
                        val comicObject = result.getJSONObject(i)
                        val id = comicObject.getLong("id")
                        val title = comicObject.getString("title")
                        val description = comicObject.getString("description")
                        var image = ""

                        if(!comicObject.isNull("thumbnail")) {
                            val thumbnail = comicObject.getJSONObject("thumbnail")
                            val path = thumbnail.getString("path")
                            val extension = thumbnail.getString("extension")

                            image = path + "/portrait_xlarge." + extension
                        }
                        val comic = Comic(id, title, image, description)

                        comicsList.add(comic)
                    }

                    e.onNext(comicsList)
                    e.onComplete()
                }

            }catch (erro: Exception){
                erro.printStackTrace()
                e.onError(erro)
            }
        }
    }

    override fun loadCharacterEvents(id: Long): ObservableSource<MutableList<Event>> {
        return Observable.create {
            e: ObservableEmitter<MutableList<Event>> ->

            var authenticator = Authenticator.instance

            try{
                var characterApi = mRetrofit.create(CharacterApi::class.java)

                var callBody = characterApi.loadCharacterEvents(
                        id.toString(),
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash())

                var responseBody = callBody.execute()

                if (responseBody.isSuccessful){
                    val jsonObject = JSONObject(responseBody.body()?.string())
                    val data = jsonObject.getJSONObject("data")
                    val result = data.getJSONArray("results")

                    val eventsList: MutableList<Event> = mutableListOf()

                    for(i in 0..(result.length() - 1)) {
                        val eventObject = result.getJSONObject(i)
                        val id = eventObject.getLong("id")
                        val title = eventObject.getString("title")
                        val description = eventObject.getString("description")
                        var image = ""

                        if(!eventObject.isNull("thumbnail")) {
                            val thumbnail = eventObject.getJSONObject("thumbnail")
                            val path = thumbnail.getString("path")
                            val extension = thumbnail.getString("extension")

                            image = path + "/portrait_xlarge." + extension
                        }

                        val event = Event(id, title, image, description)

                        eventsList.add(event)
                    }

                    e.onNext(eventsList)
                    e.onComplete()
                }

            }catch (erro: Exception){
                erro.printStackTrace()
                e.onError(erro)
            }
        }
    }

    override fun loadCharacterSeries(id: Long): ObservableSource<MutableList<Serie>> {
        return Observable.create {
            e: ObservableEmitter<MutableList<Serie>> ->

            var authenticator = Authenticator.instance

            try{
                var characterApi = mRetrofit.create(CharacterApi::class.java)

                var callBody = characterApi.loadCharacterSeries(
                        id.toString(),
                        authenticator.generanteTimestamp(),
                        authenticator.mApiKey,
                        authenticator.generateHash())

                var responseBody = callBody.execute()

                if (responseBody.isSuccessful){
                    val jsonObject = JSONObject(responseBody.body()?.string())
                    val data = jsonObject.getJSONObject("data")
                    val result = data.getJSONArray("results")

                    val seriesList: MutableList<Serie> = mutableListOf()

                    for(i in 0..(result.length() - 1)) {
                        val serieObject = result.getJSONObject(i)
                        val id = serieObject.getLong("id")
                        val title = serieObject.getString("title")
                        val description = serieObject.getString("description")
                        var image = ""

                        if(!serieObject.isNull("thumbnail")) {
                            val thumbnail = serieObject.getJSONObject("thumbnail")
                            val path = thumbnail.getString("path")
                            val extension = thumbnail.getString("extension")

                            image = path + "/portrait_xlarge." + extension
                        }

                        val serie = Serie(id, title, image, description)

                        seriesList.add(serie)
                    }

                    e.onNext(seriesList)
                    e.onComplete()
                }

            }catch (erro: Exception){
                erro.printStackTrace()
                e.onError(erro)
            }

        }
    }




}