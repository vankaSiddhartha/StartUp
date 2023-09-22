package com.vanka.skillstar.cources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanka.skillstar.adapter.VideosAdapter
import com.vanka.skillstar.clubs.clubsInsideFragment.videoCource.ViewModelVideoCource
import com.vanka.skillstar.databinding.ActivityCourceDetailsBinding

class Cource_Details : AppCompatActivity() {
    private lateinit var binding:ActivityCourceDetailsBinding
    private lateinit var viewModel:ViewModelCources
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityCourceDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ViewModelCources::class.java]
        setContentView(binding.root)
        binding.coursesRv.layoutManager = LinearLayoutManager(this)
        var adapter = VideosAdapter(this)
        binding.coursesRv.adapter = adapter

        val nameTitle = intent.getStringExtra("name")


        if (nameTitle != null) {
            viewModel.fetchDataFromDatabase(nameTitle)
        }
        viewModel.dataList.observe(this){data->
            adapter.setData(data)

        }


    }
}