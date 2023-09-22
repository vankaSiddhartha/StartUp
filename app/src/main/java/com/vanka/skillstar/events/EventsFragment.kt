package com.vanka.skillstar.events

import android.app.usage.UsageEvents.Event
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.R
import com.vanka.skillstar.adapter.EventAdapter
import com.vanka.skillstar.adapter.VideosAdapter
import com.vanka.skillstar.databinding.FragmentEventsBinding
import com.vanka.skillstar.reaptedCode.Loading


class EventsFragment : Fragment() {

 private lateinit var binding:FragmentEventsBinding
 private lateinit var viewModel: ViewModelEventFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        viewModel = ViewModelProvider(requireActivity())[ViewModelEventFragment::class.java]
        val adapter = EventAdapter(requireContext())
        binding.eventRv.layoutManager =LinearLayoutManager(requireContext())
        binding.eventRv.adapter = adapter
        viewModel.dataList.observe(requireActivity()) { data ->
            adapter.setData(data)
        }

        return binding.root
    }


}