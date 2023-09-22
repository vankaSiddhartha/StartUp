package com.vanka.skillstar.clubs.clubsInsideFragment.videoCource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.adapter.ClubAdapter
import com.vanka.skillstar.adapter.CourseAdpter
import com.vanka.skillstar.databinding.FragmentVideosCourseBinding
import com.vanka.skillstar.reaptedCode.Loading


class VideosCourseFragment : Fragment() {
private lateinit var binding:FragmentVideosCourseBinding
private lateinit var viewModel:ViewModelVideoCource
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideosCourseBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[ViewModelVideoCource::class.java]
        binding.coursesRv.layoutManager = LinearLayoutManager(requireContext())

        val adapter = CourseAdpter(requireContext())
        binding.coursesRv.adapter = adapter
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                Loading.showAlertDialogForLoading(requireContext())
            } else {
                Loading.dismissDialogForLoading()
            }
        }
        viewModel.dataList.observe(requireActivity()) { data ->

            adapter.setData(data)

        }


        return binding.root
    }


}