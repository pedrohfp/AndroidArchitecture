package br.com.androidarchictecture.view.details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.androidarchictecture.R
import br.com.androidarchictecture.view.details.contract.DetailsCharacterView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import br.com.androidarchictecture.view.home.ListCharactersFragment
import br.com.androidarchictecture.pojo.Character

class DetailsCharacterFragment : Fragment(), DetailsCharacterView {

    //Presenter
    lateinit var mPresenter: DetailsPresenter

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): DetailsCharacterFragment {
            return DetailsCharacterFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_details_character, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setPresenter(presenter: DetailsPresenter) {
         mPresenter = presenter
    }

    override fun showCharacterDetails(character: Character) {

    }

    override fun showMessageLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
