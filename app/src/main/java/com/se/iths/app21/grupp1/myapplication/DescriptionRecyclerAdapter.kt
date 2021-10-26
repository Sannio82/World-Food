package com.example.newrecyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.se.iths.app21.grupp1.myapplication.Comments
import com.se.iths.app21.grupp1.myapplication.Place
import com.se.iths.app21.grupp1.myapplication.PlacesActivity
import com.se.iths.app21.grupp1.myapplication.R
import kotlinx.android.synthetic.main.activity_places.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class DescriptionRecyclerAdapter(val context: Context, val comment : List<Comments>) :
    RecyclerView.Adapter<DescriptionRecyclerAdapter.ViewHolder>(){

    val layoutInflater = LayoutInflater.from(context)
    val comments : MutableList<Comments> = mutableListOf<Comments>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]

        holder.itemView.description.text = comment.comments
        //holder.itemView.placeName.text = place.name  // måste här hämta från firebase
        holder.descriptionPosition = position

    }


    override fun getItemCount() = comments.size // måste ändra här. Detta är alltså antalet kommentarer som ligger i just den valda platsen

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionText = itemView.findViewById<TextView>(R.id.description)
        var descriptionPosition = 0

      /*  init {
            itemView.setOnClickListener {
                val intent = Intent(context, PlacesActivity::class.java)
                intent.putExtra(COMMENT_POSITION_KEY, commentPosition) // Måste kolla hur man får fram denna info från db.
                context.startActivity(intent)
            }
        }

       */

    }
}




