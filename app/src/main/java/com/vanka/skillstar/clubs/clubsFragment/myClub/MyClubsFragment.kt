package com.vanka.skillstar.clubs.clubsFragment.myClub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.adapter.ClubAdapter
import com.vanka.skillstar.adapter.MyClubAdapter
import com.vanka.skillstar.clubs.clubsFragment.allClubs.AllClubsViewModel
import com.vanka.skillstar.databinding.FragmentAllClubsBinding
import com.vanka.skillstar.databinding.FragmentMyClubsBinding
import com.vanka.skillstar.reaptedCode.Loading


class MyClubsFragment : Fragment() {

  private lateinit var binding:FragmentMyClubsBinding
  private lateinit var viewModel:MyClubsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyClubsBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[MyClubsViewModel::class.java]
        binding.myClubRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                Loading.showAlertDialogForLoading(requireContext())
            } else {
                Loading.dismissDialogForLoading()
            }
        }
        val adapter = MyClubAdapter(requireContext())
        binding.myClubRv.adapter = adapter
        viewModel.dataList.observe(requireActivity()) { data ->

            adapter.setData(data)

        }


        return binding.root
    }


}