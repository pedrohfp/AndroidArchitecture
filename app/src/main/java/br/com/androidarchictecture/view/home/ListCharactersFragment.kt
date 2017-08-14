package br.com.androidarchictecture.view.home


import android.content.Intent
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
import br.com.androidarchictecture.util.SimpleIdlingResource
import kotlinx.android.synthetic.main.fragment_list_characters.*
import io.reactivex.android.schedulers.AndroidSchedulers
import br.com.androidarchictecture.util.RxSearch
import br.com.androidarchictecture.view.details.CharacterDetailsActivity
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class ListCharactersFragment : Fragment(), ListCharactersView{

    //Presenter
    lateinit var mPresenter: Presenter

    //Scrolling - RecyclerView
    val mPageSize = 20
    var mCurrentPage = 0

    //Adapter - RecyclerView
    lateinit var adapter: ListCharactersAdapter

    //List of Characters
    lateinit var listCharacters: MutableList<Character>

    //IdlingResources - Espresso
    var mIdlingResources: SimpleIdlingResource? = null

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

        listCharacters = mutableListOf()

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
                    mPresenter.loadCharacters(mCurrentPage, mIdlingResources, "")
                }

            }
        }

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)

        adapter = ListCharactersAdapter(activity)
        adapter.setCharacter(listCharacters)

        adapter.setItemClick(object: ListCharactersAdapter.OnItemClickListener{
            override fun onItemClick(id: Long) {
                val intent = CharacterDetailsActivity.newIntent(activity, id)
                activity.startActivity(intent)
            }
        })

        recyclerView.adapter = adapter

        RxSearch.instance.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ query ->
                            if(query!!.isEmpty()) {
                                adapter.characterList.clear()
                                mPresenter.loadCharacters(0, mIdlingResources, "")
                            }else{
                                mPresenter.loadCharacters(0, mIdlingResources, query!!)
                            }
                })

    }

    override fun setPresenter(presenter: Presenter) {
        mPresenter = presenter
    }

    override fun showMessageLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadCharacters(characters: MutableList<Character>, idlingResource: SimpleIdlingResource?, isSearch: Boolean) {
        if(isSearch == false) {
            adapter.characterList.addAll(characters)
            adapter.notifyDataSetChanged()
        }else{
            adapter.characterList.clear()
            adapter.characterList.addAll(characters)
            adapter.notifyDataSetChanged()
        }

        mIdlingResources = idlingResource
        idlingResource!!.setIdleState(true)

    }

}


