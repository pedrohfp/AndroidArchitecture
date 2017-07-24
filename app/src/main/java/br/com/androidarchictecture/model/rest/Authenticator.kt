package br.com.androidarchictecture.model.rest

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by pedrohenrique on 20/07/17.
 */
class Authenticator{

    var mApiKey = "d7eca9aa38c181fda0b58215c7c1848e"
    private var mPrivateKey = "f9734b014b9eeb709f21befccb04cd023aa17086"
    private var mTimeStamp: String? = null

    private object Holder {val INSTANCE = Authenticator()}

    companion object {
        val instance: Authenticator by lazy { Holder.INSTANCE }
    }

    fun generanteTimestamp(): String{

        if(mTimeStamp == null) {
            mTimeStamp = System.currentTimeMillis().toString()
        }

        return mTimeStamp!!
    }


    fun generateHash(): String{

        var authenticatorValues: String = mTimeStamp + mPrivateKey + mApiKey

        var md = MessageDigest.getInstance("MD5")

        val hash = BigInteger(1, md!!.digest(authenticatorValues.toByteArray()))

        val hashString = hash.toString(16)

        return hashString
    }
}
