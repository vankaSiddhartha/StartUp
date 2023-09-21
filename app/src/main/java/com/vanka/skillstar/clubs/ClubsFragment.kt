package com.vanka.skillstar.clubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.vanka.skillstar.adapter.ClubFragmentPageAdapter
import com.vanka.skillstar.databinding.FragmentClubsBinding


class ClubsFragment : Fragment() {
private lateinit var binding:FragmentClubsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubsBinding.inflate(inflater,container,false)
        val adapter = ClubFragmentPageAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter


        binding.viewPager.adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        when(position){
            0->tab.text = "Clubs"
            1->tab.text ="My Clubs"
        }
        }.attach()
        return binding.root
    }

}