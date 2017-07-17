package br.com.androidarchictecture.view.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.androidarchictecture.R
import br.com.androidarchictecture.util.ActivityUtils
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : ActivityView() {

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

    }

}
