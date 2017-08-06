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
                       @Query("hash") hash: String,
                       @Query("offset") offset: String): Call<ResponseBody>

    @GET("v1/public/characters")
    fun loadCharactersBySearch(@Query("ts") timestamp: String,
                       @Query("apikey") apikey: String,
                       @Query("hash") hash: String,
                       @Query("offset") offset: String,
                       @Query("nameStartsWith") search: String): Call<ResponseBody>

    @GET("v1/public/characters/{id}")
    fun loadCharactersDetails(@Path("id") id: String,
                       @Query("ts") timestamp: String,
                       @Query("apikey") apikey: String,
                       @Query("hash") hash: String): Call<ResponseBody>
}