package com.vanka.skillstar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.databinding.EventCardBinding
import com.vanka.skillstar.model.EventModel

class EventAdapter(val context:Context):RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    var list:List<EventModel> = emptyList()
    inner class ViewHolder(val binding:EventCardBinding):RecyclerView.ViewHolder(binding.root)
    fun setData(newList:List<EventModel>){
        list = newList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventCardBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cardTitle.text = list[position].title
        holder.binding.cardDescription.text = list[position].dis
        Glide.with(context).load(list[position].img).into(holder.binding.cardImage)
    }
}