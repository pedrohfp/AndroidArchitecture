package br.com.androidarchictecture.view.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import br.com.androidarchictecture.R
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import br.com.androidarchictecture.pojo.Character
import kotlinx.android.synthetic.main.fragment_list_characters.*

/**
 * A simple [Fragment] subclass.
 */
class ListCharactersFragment : Fragment(), ListCharactersView {
    override fun showMessageLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //Presenter
    lateinit var mPresenter: Presenter

    //Scrolling - RecyclerView
    val mPageSize = 20
    var mCurrentPage = 0

    //Adapter - RecyclerView
    lateinit var adapter: ListCharactersAdapter

    //List of Characters
    val listCharacters: MutableList<Character> = mutableListOf()

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): ListCharactersFragment {
            return ListCharactersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_list_characters, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= mPageSize){
                    mCurrentPage++
                    mPresenter.loadCharacters(mCurrentPage)
                }

            }
        }

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        adapter = ListCharactersAdapter(listCharacters, activity)
        recyclerView.adapter = adapter

    }

    override fun setPresenter(presenter: Presenter) {
        mPresenter = presenter
    }

    override fun loadCharacters(characters: MutableList<Character>) {
         adapter.characterList.addAll(characters)
         adapter.notifyDataSetChanged()
    }
}


