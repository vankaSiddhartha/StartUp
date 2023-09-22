package com.vanka.skillstar.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanka.skillstar.cources.VideoPlayer
import com.vanka.skillstar.databinding.CourceCardChapterBinding
import com.vanka.skillstar.model.WhatchVideoModel

class VideosAdapter(val context: Context) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {
    var list: List<WhatchVideoModel> = emptyList()

    inner class ViewHolder(val binding: CourceCardChapterBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<WhatchVideoModel>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CourceCardChapterBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = list[position].title
        holder.binding.coursesDes.text = list[position].dis
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,VideoPlayer::class.java)
            intent.putExtra("title",list[position].title)
            intent.putExtra("link",list[position].link)
            intent.putExtra("dis",list[position].dis)
            context.startActivity(intent)
        }
    }
}
