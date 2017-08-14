package br.com.androidarchictecture.pojo

import br.com.androidarchictecture.view.details.contract.CharacterMedia

/**
 * Created by pedrohenrique on 07/08/17.
 */
class Event: CharacterMedia {
    override var mId: Long
    override var mTitle: String
    override var mThumbnail: String
    override var mDescription: String

    constructor(mId: Long, mTitle: String, mThumbnail: String, mDescription: String) {
        this.mId = mId
        this.mTitle = mTitle
        this.mThumbnail = mThumbnail
        this.mDescription = mDescription
    }
}