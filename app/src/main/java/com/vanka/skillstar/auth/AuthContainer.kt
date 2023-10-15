  package com.vanka.skillstar.auth

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import com.vanka.skillstar.R
import com.vanka.skillstar.databinding.ActivityAuthContainerBinding
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment

  class AuthContainer : AppCompatActivity() {
      private lateinit var binding:ActivityAuthContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_container)
        binding = ActivityAuthContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val window: Window = window


        // Set the navigation bar color
        window.navigationBarColor = Color.BLACK
        intentFragment( R.id.authContainer, ChooseAuthFragment(),this)
    }
}