package br.com.androidarchictecture.model.rest

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by pedrohenrique on 20/07/17.
 */
class Authenticator{

    private var mApiKey = "d7eca9aa38c181fda0b58215c7c1848e"
    private var mPrivateKey = "f9734b014b9eeb709f21befccb04cd023aa17086"

    private object Holder {val INSTANCE = Authenticator()}

    companion object {
        val instance: Authenticator by lazy { Holder.INSTANCE }
    }

    fun generate(): String{

        val timeStamp = System.currentTimeMillis().toString()

        var authenticatorValues: String = timeStamp + mApiKey + mPrivateKey

        var md = MessageDigest.getInstance("MD5")

        val hash = BigInteger(1, md!!.digest(authenticatorValues.toByteArray()))

        val hashString = hash.toString(16)

        return hashString
    }
}
