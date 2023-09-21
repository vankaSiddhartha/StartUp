package com.vanka.skillstar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanka.skillstar.clubs.ClubsFragment
import com.vanka.skillstar.cources.Cources
import com.vanka.skillstar.databinding.ActivityMainBinding
import com.vanka.skillstar.events.EventsFragment
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentFragment(R.id.frame, Cources(), this)
        binding.bottomNavigationView.setOnItemSelectedListener { id ->
            when (id.itemId) {
                R.id.clubs -> {
                    intentFragment(
                        R.id.frame,
                        ClubsFragment(),
                        this
                    )
                    true

                }
                R.id.events -> {
                    intentFragment(
                        R.id.frame,
                        EventsFragment(),
                        this
                    )
                    true
                }
                R.id.courses -> {
                    intentFragment(
                        R.id.frame,
                        Cources(),
                        this
                    )
                    true
                }
                R.id.notification -> {
                    intentFragment(
                        R.id.frame,
                        ClubsFragment(),
                        this
                    )
                    true
                }
                R.id.profile -> {
                    intentFragment(
                        R.id.frame,
                        ClubsFragment(),
                        this
                    )
                    true
                }


                else -> {
                    intentFragment(
                        R.id.frame,
                        ClubsFragment(),
                        this
                    )
                    true
                }

            }


        }


    }
}
