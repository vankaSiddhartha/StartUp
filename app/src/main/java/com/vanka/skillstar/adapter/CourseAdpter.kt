package com.vanka.skillstar.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.MainActivity
import com.vanka.skillstar.cources.Cource_Details
import com.vanka.skillstar.cources.Cources
import com.vanka.skillstar.databinding.CourseCardBinding
import com.vanka.skillstar.model.CourseDataModel

class CourseAdpter(val context: Context):RecyclerView.Adapter<CourseAdpter.ViewHolder>() {
    var list:List<CourseDataModel> = emptyList()
    inner class ViewHolder(val binding:CourseCardBinding):RecyclerView.ViewHolder(binding.root)
    fun setData(Newlist:List<CourseDataModel>){
        list = Newlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CourseCardBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,Cource_Details::class.java)
            intent.putExtra("name",list[position].name)
            intent.putExtra("aboutText",list[position].aboutText)
            context.startActivity(intent)
        }
        holder.binding.cardDescription.text = list[position].aboutText
        holder.binding.cardTitle.text = list[position].name
        Glide.with(context).load(list[position].img).into(holder.binding.cardImage)
    }
}