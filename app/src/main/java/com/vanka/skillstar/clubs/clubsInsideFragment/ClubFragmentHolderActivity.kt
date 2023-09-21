package com.vanka.skillstar.clubs.clubsInsideFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.vanka.skillstar.MainActivity
import com.vanka.skillstar.adapter.ClubsInsideAdapter
import com.vanka.skillstar.databinding.ActivityClubFragmentHolderBinding

class ClubFragmentHolderActivity : AppCompatActivity() {
    private lateinit var binding:ActivityClubFragmentHolderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ShareTheMyClubData.name = intent.getStringExtra("clubName")!!
        ShareTheMyClubData.img = intent.getStringExtra("profile")!!
        ShareTheMyClubData.aboutText = intent.getStringExtra("aboutClub")!!
        ShareTheMyClubData.level = intent.getStringExtra("level")!!
        binding = ActivityClubFragmentHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ClubsInsideAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.back.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.viewPager.adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0->tab.text = "Discussion"
                1->tab.text ="Projects"
                2->tab.text = "Videos"
            }
        }.attach()
    }
}