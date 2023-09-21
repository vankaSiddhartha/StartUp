package com.vanka.skillstar.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vanka.skillstar.R
import com.vanka.skillstar.auth.createAccount.CreateAccountFragment
import com.vanka.skillstar.auth.login.Login
import com.vanka.skillstar.databinding.FragmentChooseAuthBinding
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment


class ChooseAuthFragment : Fragment() {

    private lateinit var binding:FragmentChooseAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseAuthBinding.inflate(inflater,container,false)
        binding.createAccount.setOnClickListener {
            intentFragment(R.id.authContainer, CreateAccountFragment(),requireContext())
        }
        // on clicking login button
        binding.login.setOnClickListener {
            intentFragment(R.id.authContainer, Login(),requireContext())
        }
        return binding.root
    }


}