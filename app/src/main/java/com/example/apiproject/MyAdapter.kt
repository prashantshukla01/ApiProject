package com.example.apiproject

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity,val userArrayList: List<Product>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
       var title : TextView
       var image : ShapeableImageView

       init {
           title= itemView.findViewById(R.id.productTitle)
           image = itemView.findViewById(R.id.productImage)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.activity_each_row,parent,false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return userArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userArrayList[position]
        holder.title.text = currentItem.title
        //how to show image in image view if the image is in form of url, 3rd party library
        //  Picasso
        Picasso.get().load(currentItem.thumbnail).into(holder.image);


    }
}


