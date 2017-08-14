package br.com.androidarchictecture.view.details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.androidarchictecture.R
import br.com.androidarchictecture.view.details.contract.CharacterMedia
import com.squareup.picasso.Picasso

/**
 * Created by pedrohenrique on 10/08/17.
 */
class DetailsCharacterAdapter(val context: Context) : RecyclerView.Adapter<DetailsCharacterAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(id: Long)
    }

    lateinit var mediaList: MutableList<CharacterMedia>
    lateinit var listener: OnItemClickListener

    fun setCharacterMedia(medias: MutableList<CharacterMedia>){
        mediaList = medias
    }

    fun setItemClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mediaList[position], context, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailsCharacterAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.details_list_item, parent, false)
        return ViewHolder(v)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(media: CharacterMedia, context: Context, listener: OnItemClickListener){
            val itemImageView = itemView.findViewById(R.id.itemImage) as ImageView

            if(!media.mThumbnail.isEmpty()) {
                Picasso.with(context).load(media.mThumbnail).into(itemImageView)
            }

            itemView.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    listener.onItemClick(media.mId)
                }
            })
        }
    }

}