package br.com.androidarchictecture.model.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by pedrohenrique on 20/07/17.
 */
interface CharacterApi{
    @GET("v1/public/characters")
    fun loadCharacters(@Query("ts") timestamp: String,
                       @Query("apikey") apikey: String,
                       @Query("hash") hash: String): Call<ResponseBody>
}