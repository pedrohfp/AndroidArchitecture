package br.com.androidarchictecture.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import br.com.androidarchictecture.R
import br.com.androidarchictecture.util.ActivityUtils
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.details.contract.DetailsActivityView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import br.com.androidarchictecture.view.details.di.DaggerDetailsComponent
import br.com.androidarchictecture.view.details.di.DetailsModule
import br.com.androidarchictecture.view.home.di.CharacterListModule
import br.com.androidarchictecture.view.home.di.DaggerCharacterComponent
import javax.inject.Inject

class CharacterDetailsActivity : DetailsActivityView() {

    lateinit var mPresenter: DetailsPresenter

    //Fragment
    var mDetailsCharacterFragment: DetailsCharacterFragment? = null

    companion object {

        private val INTENT_ID = "character_id"

        fun newIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, CharacterDetailsActivity::class.java)
            intent.putExtra(INTENT_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val characterId = intent.getLongExtra(INTENT_ID, 0)

        Log.e("ID CHARACTER: ", characterId.toString())

        mDetailsCharacterFragment =
                supportFragmentManager.findFragmentById(R.id.details_content) as DetailsCharacterFragment?

        if(mDetailsCharacterFragment == null){
            //Create the fragment
            mDetailsCharacterFragment = DetailsCharacterFragment.newInstance()

            ActivityUtils
                    .addFragmentToActivity(supportFragmentManager, mDetailsCharacterFragment!!, R.id.details_content)
        }

        //Create the presenter
        DaggerDetailsComponent.builder()
                .appComponent(MarvelApplication.mAppComponent)
                .detailsModule(DetailsModule(this, mDetailsCharacterFragment!!))
                .build()
                .inject(this)
    }

    @Inject
    override fun setPresenter(presenter: DetailsPresenter) {
        mPresenter = presenter
    }
}
