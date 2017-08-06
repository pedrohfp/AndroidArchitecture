package br.com.androidarchictecture.view.details

import android.os.Bundle

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
    lateinit var mDetailsCharacterFragment: DetailsCharacterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        mDetailsCharacterFragment =
                supportFragmentManager.findFragmentById(R.id.details_content) as DetailsCharacterFragment

        if(mDetailsCharacterFragment == null){
            //Create the fragment
            mDetailsCharacterFragment = DetailsCharacterFragment.newInstance()

            ActivityUtils
                    .addFragmentToActivity(supportFragmentManager, mDetailsCharacterFragment, R.id.details_content)
        }

        //Create the presenter
        DaggerDetailsComponent.builder()
                .appComponent(MarvelApplication.mAppComponent)
                .detailsModule(DetailsModule(this, mDetailsCharacterFragment))
                .build()
                .inject(this)
    }

    @Inject
    override fun setPresenter(presenter: DetailsPresenter) {
        mPresenter = presenter
    }
}
