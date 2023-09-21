package com.vanka.skillstar.clubs.clubsInsideFragment.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.R
import com.vanka.skillstar.adapter.ClubAdapter
import com.vanka.skillstar.adapter.PostAdapter
import com.vanka.skillstar.adapter.ProjectAdapter
import com.vanka.skillstar.databinding.FragmentProjectBinding
import com.vanka.skillstar.reaptedCode.Loading


class ProjectFragment : Fragment() {

  private lateinit var binding:FragmentProjectBinding
  private lateinit var viewModel:ViewModelProjectFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[ViewModelProjectFragment::class.java]
        binding.projectRv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProjectAdapter(requireContext())
        binding.projectRv.adapter = adapter
        viewModel.dataList.observe(requireActivity()) { data ->

            adapter.setDate(data)



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