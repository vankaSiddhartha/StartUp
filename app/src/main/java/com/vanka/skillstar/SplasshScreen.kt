package com.vanka.skillstar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.vanka.skillstar.auth.AuthContainer

class SplasshScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler(mainLooper).postDelayed({
            if (FirebaseAuth.getInstance().currentUser!=null)
                startActivity(Intent(this,MainActivity::class.java))
            else
            startActivity(Intent(this,AuthContainer::class.java))
            finish()
        },1000)
    }
}