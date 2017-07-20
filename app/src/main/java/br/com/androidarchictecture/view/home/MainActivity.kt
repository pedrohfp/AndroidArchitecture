package br.com.androidarchictecture.view.home

import android.os.Bundle
import br.com.androidarchictecture.R
import br.com.androidarchictecture.util.ActivityUtils
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.di.CharacterPresenterModule
import br.com.androidarchictecture.view.home.di.DaggerCharacterComponent
import javax.inject.Inject

class MainActivity : ActivityView() {

    @Inject
    lateinit var mPresenter: CharacterPresenter

    //Fragment
    var mListCharactersFragment: ListCharactersFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListCharactersFragment =
                supportFragmentManager.findFragmentById(R.id.content) as ListCharactersFragment?

        if(mListCharactersFragment == null){
            //Create the fragment
            mListCharactersFragment = ListCharactersFragment.newInstance()

            ActivityUtils
                    .addFragmentToActivity(supportFragmentManager, mListCharactersFragment!!, R.id.content)
        }


        //Create the presenter
        DaggerCharacterComponent.builder()
                .appComponent(MarvelApplication.mAppComponent)
                .characterPresenterModule(CharacterPresenterModule(this, mListCharactersFragment!!))
                .build()
                .inject(this)

        mPresenter.start()

    }

}
