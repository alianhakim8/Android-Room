package com.alian.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alian.roomdatabase.R
import com.alian.roomdatabase.data.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvId).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvFirstName).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tvLastName).text = currentItem.lastName
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}