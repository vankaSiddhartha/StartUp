package com.vanka.skillstar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vanka.skillstar.clubs.clubsInsideFragment.clubChats.ClubsChatFragment
import com.vanka.skillstar.clubs.clubsInsideFragment.projects.ProjectFragment
import com.vanka.skillstar.clubs.clubsInsideFragment.videoCource.VideosCourseFragment

class ClubsInsideAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                ClubsChatFragment()
            }
            1->{
                ProjectFragment()
            }
            2->{
                VideosCourseFragment()
            }
            else -> {
                ClubsChatFragment()
            }
        }
    }
}