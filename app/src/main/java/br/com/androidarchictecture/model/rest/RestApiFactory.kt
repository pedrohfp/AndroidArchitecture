package br.com.androidarchictecture.model.rest

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by pedrohenrique on 13/07/17.
 */
class RestApiFactory{
    private var retrofit: Retrofit? = null
    var readTimeout: Long = 0
    var writeTimeout: Long = 0
    var version: String? = null
    val baseUrl = "https://gateway.marvel.com/"

    fun reinitialize() {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)

        val okHttpClient = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    init {
        writeTimeout = 30
        readTimeout = 60
        version = null
        retrofit = null
    }

    fun <T> create(service: Class<T>): T {
        if (retrofit == null) {
            reinitialize()
        }

        return retrofit!!.create(service)
    }
}