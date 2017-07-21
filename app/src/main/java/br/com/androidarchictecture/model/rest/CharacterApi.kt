package br.com.androidarchictecture.model.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by pedrohenrique on 20/07/17.
 */
interface CharacterApi{
    @POST("api/account/address")
    fun loadCharacters(): Call<ResponseBody>
}