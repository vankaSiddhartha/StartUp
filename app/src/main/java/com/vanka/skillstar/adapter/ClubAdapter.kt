package com.vanka.skillstar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.R
import com.vanka.skillstar.clubs.clubsFragment.allClubs.AllClubsUploadToFireBase.uploadMyClub
import com.vanka.skillstar.databinding.ClubLayoutBinding
import com.vanka.skillstar.model.ClubModel
import com.vanka.skillstar.model.CourseDataModel
import com.vanka.skillstar.model.MyClubModel
import com.vanka.skillstar.reaptedCode.ClubsDataStore.getListFromSharedPreferences
import com.vanka.skillstar.reaptedCode.ClubsDataStore.saveListToSharedPreferences

class ClubAdapter(val context: Context):RecyclerView.Adapter<ClubAdapter.ViewHolder>() {
    private var list: List<ClubModel> = emptyList()
    inner class ViewHolder(val binding:ClubLayoutBinding):RecyclerView.ViewHolder(binding.root)

    // Update adapter data and notify change
    fun setData(newData: List<ClubModel>) {
        list = newData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ClubLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //myClubs list
        for (i in getListFromSharedPreferences(context)){
            if (i == list[position].name){

                val pinkColor = ContextCompat.getColor(context, R.color.pink)
                holder.binding.joinBtn.setCardBackgroundColor(pinkColor)
                holder.binding.btnText.text = "Joined the club"
            }
        }
         Glide.with(context).load(list[position].img).into(holder.binding.imgClub)
         holder.binding.clubName.text = list[position].name
         holder.binding.clubDis.text = list[position].aboutText
        val cardDescription = holder.binding.clubDis
        val readMoreText = holder.binding.readmoreText


        readMoreText.setOnClickListener {
            if (cardDescription.maxLines == 2) {
                cardDescription.maxLines = Int.MAX_VALUE
                readMoreText.text = "Read Less"
            } else {
                cardDescription.maxLines = 2
                readMoreText.text = "Read More"
            }
        }



        holder.binding.joinBtn.setOnClickListener {
            // Get the current background color of the CardView
            val currentColor = (holder.binding.joinBtn as CardView).cardBackgroundColor.defaultColor
            val isPinkColor = currentColor == ContextCompat.getColor(context, R.color.pink)
            val listClubs = getListFromSharedPreferences(context)
            if (isPinkColor){

                listClubs.remove(list[position].name)
                saveListToSharedPreferences(context, listClubs)
                val black = ContextCompat.getColor(context,R.color.black)
                holder.binding.joinBtn.setCardBackgroundColor(black)
                holder.binding.btnText.text = "Join club"


            }else {

                val pinkColor = ContextCompat.getColor(context, R.color.pink)
                holder.binding.joinBtn.setCardBackgroundColor(pinkColor)
                holder.binding.btnText.text = "Joined the club "
                val  data = MyClubModel(list[position].name,list[position].img,list[position].aboutText,"Level 0")
                uploadMyClub(data,context)

                listClubs.add(holder.binding.clubName.text.toString())
                saveListToSharedPreferences(context, listClubs)
            }


        }





    }
}