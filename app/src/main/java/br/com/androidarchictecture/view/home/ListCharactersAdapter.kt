package br.com.androidarchictecture.view.home

import android.content.Context
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.androidarchictecture.R
import br.com.androidarchictecture.pojo.Character
import com.squareup.picasso.Picasso

/**
 * Created by pedrohenrique on 25/07/17.
 */

class ListCharactersAdapter(val context: Context) : RecyclerView.Adapter<ListCharactersAdapter.ViewHolder>(){

    lateinit var characterList: MutableList<Character>

    fun setCharacter(characters: MutableList<Character>){
        characterList = characters
    }

    override fun getItemCount(): Int {
          return characterList.size
    }

    override fun onBindViewHolder(holder: ListCharactersAdapter.ViewHolder, position: Int) {
         holder.bindItems(characterList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListCharactersAdapter.ViewHolder{
         val v = LayoutInflater.from(parent?.context).inflate(R.layout.characters_list_item, parent, false)
         return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         fun bindItems(character: Character, context: Context){
             val itemImageView = itemView.findViewById(R.id.itemImage) as ImageView
             val itemNameTextView = itemView.findViewById(R.id.itemName) as TextView

             Picasso.with(context).load(character.mThumbnail).into(itemImageView)
             itemNameTextView.text = character.mName
         }
    }

}