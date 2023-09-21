package com.vanka.skillstar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.clubs.clubsInsideFragment.ShareTheMyClubData
import com.vanka.skillstar.databinding.PostCardLayoutBinding
import com.vanka.skillstar.model.PostModel

class PostAdapter(val context:Context):RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var list:List<PostModel> = emptyList()
    class ViewHolder(val binding:PostCardLayoutBinding):RecyclerView.ViewHolder(binding.root)

    fun setData(newList:List<PostModel>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostCardLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].postImg).into(holder.binding.postImage)

        Glide.with(context).load(list[position].authImg).into(holder.binding.userProfileImage)
        holder.binding.postContent.text =list[position].postText
        holder.binding.username.text = list[position].authName
        holder.binding.level.text = ShareTheMyClubData.level
    }
}