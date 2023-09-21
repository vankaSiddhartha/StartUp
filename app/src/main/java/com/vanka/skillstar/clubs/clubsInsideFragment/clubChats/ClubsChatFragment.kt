package com.vanka.skillstar.clubs.clubsInsideFragment.clubChats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.adapter.PostAdapter
import com.vanka.skillstar.clubs.clubsInsideFragment.ShareTheMyClubData
import com.vanka.skillstar.clubs.clubsInsideFragment.clubChats.FetchData.fetchDataFromDatabase
import com.vanka.skillstar.clubs.postUpload.UploadPost
import com.vanka.skillstar.databinding.FragmentClubsChatBinding
import com.vanka.skillstar.reaptedCode.Loading

class ClubsChatFragment : Fragment() {
 private lateinit var binding:FragmentClubsChatBinding
 private lateinit var viewModel:ClubsChatViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[ClubsChatViewModel::class.java]
        binding = FragmentClubsChatBinding.inflate(inflater,container,false)
        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PostAdapter(requireContext())
        binding.recyclerView2.adapter = adapter
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

        binding.ClubChatButton.setOnClickListener{
            val intent = Intent(requireContext(),UploadPost::class.java)
            intent.putExtra("name", ShareTheMyClubData.name)

            startActivity(intent)
        }

        return binding.root
    }

}