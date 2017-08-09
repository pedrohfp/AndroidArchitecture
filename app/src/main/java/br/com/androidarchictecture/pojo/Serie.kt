package br.com.androidarchictecture.pojo

/**
 * Created by pedrohenrique on 07/08/17.
 */
class Serie{
    var mId: Long
    var mTitle: String
    var mThumbnail: String
    var mDescription: String

    constructor(mId: Long, mTitle: String, mThumbnail: String, mDescription: String) {
        this.mId = mId
        this.mTitle = mTitle
        this.mThumbnail = mThumbnail
        this.mDescription = mDescription
    }
}