package br.com.androidarchictecture.pojo

/**
 * Created by pedrohenrique on 20/07/17.
 */
class Character{
    var mId: Long
    var mName: String
    var mThumbnail: String
    var mDescription: String

    constructor(){
        this.mId = 0
        this.mName = ""
        this.mThumbnail = ""
        this.mDescription = ""
    }

    constructor(mId: Long, mName: String, mThumbnail: String, mDescription: String) {
        this.mId = mId
        this.mName = mName
        this.mThumbnail = mThumbnail
        this.mDescription = mDescription
    }
}