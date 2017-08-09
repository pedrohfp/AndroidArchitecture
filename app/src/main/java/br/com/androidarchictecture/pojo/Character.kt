package br.com.androidarchictecture.pojo

/**
 * Created by pedrohenrique on 20/07/17.
 */
class Character{

    var mId: Long
    var mName: String
    var mThumbnail: String
    var mDescription: String
    var mComics: MutableList<Comic>
    var mEvents: MutableList<Event>
    var mSeries: MutableList<Serie>
    var mStories: MutableList<Storie>

    constructor(){
        this.mId = 0
        this.mName = ""
        this.mThumbnail = ""
        this.mDescription = ""
        this.mComics = mutableListOf()
        this.mEvents = mutableListOf()
        this.mSeries = mutableListOf()
        this.mStories = mutableListOf()
    }

    constructor(mId: Long, mName: String, mThumbnail: String, mDescription: String, mComics: MutableList<Comic>, mEvents: MutableList<Event>, mSeries: MutableList<Serie>, mStories: MutableList<Storie>) {
        this.mId = mId
        this.mName = mName
        this.mThumbnail = mThumbnail
        this.mDescription = mDescription
        this.mComics = mComics
        this.mEvents = mEvents
        this.mSeries = mSeries
        this.mStories = mStories
    }


}