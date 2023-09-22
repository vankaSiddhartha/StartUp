package com.vanka.skillstar.cources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import com.vanka.skillstar.databinding.ActivityVideoPlayerBinding

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager

class VideoPlayer : AppCompatActivity() {
    private lateinit var binding:ActivityVideoPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        val link =intent.getStringExtra("link")


        binding.webview.loadData(link!!,"text/html","utf-8")
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webChromeClient = CustomWebChromeClient(this,binding.webFrame)
        setContentView(binding.root)
    }



}