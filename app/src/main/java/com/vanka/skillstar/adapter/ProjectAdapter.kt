package com.vanka.skillstar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.databinding.ProjectItemLayoutBinding
import com.vanka.skillstar.model.ProjectModel

class ProjectAdapter(val context: Context):RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
   private var list:List<ProjectModel> = emptyList()
     class ViewHolder(val binding:ProjectItemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    fun setDate(newList: List<ProjectModel>){
        list =newList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProjectItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  Toast.makeText(context, list[position].aboutText, Toast.LENGTH_SHORT).show()
       Glide.with(context).load(list[position].img).into(holder.binding.cardImage)
        holder.binding.cardTitle.text = list[position].name
        holder.binding.cardDescription.text = list[position].aboutText
    }


}