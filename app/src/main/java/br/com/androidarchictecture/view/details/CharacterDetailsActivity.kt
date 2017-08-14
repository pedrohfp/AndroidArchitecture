package br.com.androidarchictecture.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.test.espresso.IdlingResource
import android.util.Log
import android.view.MenuItem

import br.com.androidarchictecture.R
import br.com.androidarchictecture.util.ActivityUtils
import br.com.androidarchictecture.util.SimpleIdlingResource
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

    var mCharacterId: Long? = null

    //Idling Resource - Espresso
    private var mIdlingResource: SimpleIdlingResource? = null

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

        getIdlingResource()

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mCharacterId = intent.getLongExtra(INTENT_ID, 0)

        Log.e("ID CHARACTER: ", mCharacterId.toString())

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

    override fun onResume() {
        super.onResume()

        mPresenter.loadDetailsCharacter(mCharacterId!!, mIdlingResource!!)
    }

    @Inject
    override fun setPresenter(presenter: DetailsPresenter) {
        mPresenter = presenter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getIdlingResource(): IdlingResource {
        if(mIdlingResource == null){
            mIdlingResource = SimpleIdlingResource()
        }

        return mIdlingResource!!
    }
}
