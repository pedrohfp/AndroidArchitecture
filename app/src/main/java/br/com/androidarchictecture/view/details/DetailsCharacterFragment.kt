package br.com.androidarchictecture.view.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.androidarchictecture.R
import br.com.androidarchictecture.view.details.contract.DetailsCharacterView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import br.com.androidarchictecture.pojo.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details_character.*

class DetailsCharacterFragment : Fragment(), DetailsCharacterView {

    //Presenter
    lateinit var mPresenter: DetailsPresenter

    //RecyclerView - Adapters
    lateinit var mComicsAdapter: DetailsCharacterAdapter
    lateinit var mEventsAdapter: DetailsCharacterAdapter
    lateinit var mSeriesAdapter: DetailsCharacterAdapter


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

        val comicsLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val eventsLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val seriesLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recyclerComics.layoutManager = comicsLayoutManager
        recyclerEvents.layoutManager = eventsLayoutManager
        recyclerSeries.layoutManager = seriesLayoutManager

        mComicsAdapter = DetailsCharacterAdapter(activity)
        mComicsAdapter.setCharacterMedia(mutableListOf())

        mComicsAdapter.setItemClick(object: DetailsCharacterAdapter.OnItemClickListener{
            override fun onItemClick(id: Long) {
            }
        })

        mEventsAdapter = DetailsCharacterAdapter(activity)
        mEventsAdapter.setCharacterMedia(mutableListOf())

        mEventsAdapter.setItemClick(object: DetailsCharacterAdapter.OnItemClickListener{
            override fun onItemClick(id: Long) {
            }
        })

        mSeriesAdapter = DetailsCharacterAdapter(activity)
        mSeriesAdapter.setCharacterMedia(mutableListOf())

        mSeriesAdapter.setItemClick(object: DetailsCharacterAdapter.OnItemClickListener{
            override fun onItemClick(id: Long) {
            }
        })

        recyclerComics.adapter = mComicsAdapter
        recyclerEvents.adapter = mEventsAdapter
        recyclerSeries.adapter = mSeriesAdapter
    }

    override fun setPresenter(presenter: DetailsPresenter) {
         mPresenter = presenter
    }

    override fun showCharacterDetails(character: Character) {

         comicsTitle.visibility = View.VISIBLE
         eventsTitle.visibility = View.VISIBLE
         seriesTitle.visibility = View.VISIBLE

         Picasso.with(activity).load(character.mThumbnail).into(imageView)
         name.text = character.mName
         description.text = character.mDescription

         if(character.mDescription.isEmpty()){
             descriptionTitle.visibility = View.GONE
             description.visibility = View.GONE
         }

         if(!character.mComics.isEmpty()) {
             mComicsAdapter.mediaList.addAll(character.mComics)
             mComicsAdapter.notifyDataSetChanged()
         }else{
             comicsTitle.visibility = View.GONE
             recyclerComics.visibility = View.GONE
         }

         if(!character.mEvents.isEmpty()){
             mEventsAdapter.mediaList.addAll(character.mEvents)
             mEventsAdapter.notifyDataSetChanged()
         }else{
             eventsTitle.visibility = View.GONE
             recyclerEvents.visibility = View.GONE
         }

         if(!character.mSeries.isEmpty()){
             mSeriesAdapter.mediaList.addAll(character.mSeries)
             mSeriesAdapter.notifyDataSetChanged()
         }else{
             seriesTitle.visibility = View.GONE
             recyclerSeries.visibility = View.GONE
         }
    }

    override fun showMessageLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
