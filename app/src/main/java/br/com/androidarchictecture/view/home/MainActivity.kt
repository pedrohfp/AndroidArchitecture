package br.com.androidarchictecture.view.home

import android.os.Bundle
import android.support.test.espresso.IdlingResource
import br.com.androidarchictecture.R
import br.com.androidarchictecture.util.ActivityUtils
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.Presenter
import br.com.androidarchictecture.view.home.di.CharacterListModule
import br.com.androidarchictecture.view.home.di.DaggerCharacterComponent
import javax.inject.Inject

class MainActivity : ActivityView() {

    lateinit var mPresenter: Presenter

    //Fragment
    var mListCharactersFragment: ListCharactersFragment? = null

    //IdlingResource - Espresso
    private var mIdlingResource: SimpleIdlingResource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getIdlingResource()

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
                .characterListModule(CharacterListModule(this, mListCharactersFragment!!))
                .build()
                .inject(this)

    }

    override fun onResume() {
        super.onResume()

        mPresenter.loadCharacters(0, mIdlingResource)
    }

    @Inject
    override fun setPresenter(presenter: Presenter) {
        mPresenter = presenter
    }

    override fun getIdlingResource(): SimpleIdlingResource {
        if(mIdlingResource == null){
            mIdlingResource = SimpleIdlingResource()
        }

        return mIdlingResource!!
    }

}
