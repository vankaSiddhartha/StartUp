package com.vanka.skillstar.cources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.R
import com.vanka.skillstar.adapter.CourseAdpter
import com.vanka.skillstar.clubs.clubsInsideFragment.videoCource.ViewModelVideoCource
import com.vanka.skillstar.databinding.FragmentCourcesBinding
import com.vanka.skillstar.reaptedCode.Loading


class Cources : Fragment() {
  private lateinit var binding:FragmentCourcesBinding
    private lateinit var viewModel: ViewModelVideoCource


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[ViewModelVideoCource::class.java]
        binding = FragmentCourcesBinding.inflate(layoutInflater,container,false)
        binding.recyclerViewCource.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CourseAdpter(requireContext())
        binding.recyclerViewCource.adapter = adapter
        viewModel.dataList.observe(requireActivity()) { data ->

            adapter.setData(data)

        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                Loading.showAlertDialogForLoading(requireContext())
            } else {
                Loading.dismissDialogForLoading()
            }
        }
        return binding.root
    }


}