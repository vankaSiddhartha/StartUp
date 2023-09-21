package com.vanka.skillstar.clubs.clubsFragment.allClubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.adapter.ClubAdapter
import com.vanka.skillstar.databinding.FragmentAllClubsBinding
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading


class AllClubsFragment : Fragment() {

 private lateinit var binding:FragmentAllClubsBinding
 private lateinit var viewModel:AllClubsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =  ViewModelProvider(requireActivity())[AllClubsViewModel::class.java]
        binding = FragmentAllClubsBinding.inflate(inflater,container,false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showAlertDialogForLoading(requireContext())
            } else {
                dismissDialogForLoading()
            }
        }
        val adapter = ClubAdapter(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.dataList.observe(requireActivity()) { data ->

            adapter.setData(data)

        }






        return binding.root
    }


}