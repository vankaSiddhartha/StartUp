package com.vanka.skillstar.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanka.skillstar.R
import com.vanka.skillstar.clubs.clubsFragment.allClubs.AllClubsUploadToFireBase
import com.vanka.skillstar.clubs.clubsFragment.allClubs.AllClubsUploadToFireBase.deleteMyClub
import com.vanka.skillstar.clubs.clubsInsideFragment.ClubFragmentHolderActivity
import com.vanka.skillstar.databinding.ClubLayoutBinding
import com.vanka.skillstar.model.MyClubModel
import com.vanka.skillstar.reaptedCode.ClubsDataStore

class MyClubAdapter(val context: Context):RecyclerView.Adapter<MyClubAdapter.ViewHolder>() {
    private var list: List<MyClubModel> = emptyList()
    inner class ViewHolder(val binding: ClubLayoutBinding):RecyclerView.ViewHolder(binding.root)

    // Update adapter data and notify change
    fun setData(newData: List<MyClubModel>) {
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
        for (i in ClubsDataStore.getListFromSharedPreferences(context)){
            if (i == list[position].name){

                val pinkColor = ContextCompat.getColor(context, R.color.pink)
                holder.binding.joinBtn.setCardBackgroundColor(pinkColor)
                holder.binding.btnText.text = "View club"
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
        holder.binding.card.setOnClickListener {
            val intent = Intent(context,ClubFragmentHolderActivity::class.java)
            intent.putExtra("clubName",list[position].name)
            intent.putExtra("aboutClub",list[position].aboutText)
            intent.putExtra("profile",list[position].img)
            intent.putExtra("level",list[position].level)
            context.startActivity(intent)
        }

        holder.binding.joinBtn.setOnClickListener {
            // Get the current background color of the CardView
            val currentColor = (holder.binding.joinBtn as CardView).cardBackgroundColor.defaultColor
            val isPinkColor = currentColor == ContextCompat.getColor(context, R.color.pink)
            val listClubs = ClubsDataStore.getListFromSharedPreferences(context)
            if (isPinkColor){
                listClubs.remove(list[position].name)
                ClubsDataStore.saveListToSharedPreferences(context, listClubs)
                val black = ContextCompat.getColor(context, R.color.black)
                holder.binding.joinBtn.setCardBackgroundColor(black)
                holder.binding.btnText.text = "Join club"
                deleteMyClub(list[position].name.toString(), context)

            }else {

                val pinkColor = ContextCompat.getColor(context, R.color.pink)
                holder.binding.joinBtn.setCardBackgroundColor(pinkColor)
                holder.binding.btnText.text = "View club"
                val  data = MyClubModel(list[position].name,list[position].img,list[position].aboutText,"0")
                AllClubsUploadToFireBase.uploadMyClub(data, context)

                listClubs.add(holder.binding.clubName.text.toString())
                ClubsDataStore.saveListToSharedPreferences(context, listClubs)
            }


        }





    }
}