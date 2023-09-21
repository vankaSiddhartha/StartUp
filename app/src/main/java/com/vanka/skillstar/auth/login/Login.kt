package com.vanka.skillstar.auth.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vanka.skillstar.R
import com.vanka.skillstar.auth.ChooseAuthFragment
import com.vanka.skillstar.databinding.FragmentLoginBinding
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment

class Login : Fragment() {
    private  lateinit var binding:FragmentLoginBinding
    private lateinit var loginAccountViewHolder:LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        loginAccountViewHolder = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(LayoutInflater.from(requireContext()))
        binding.nextBtn.setOnClickListener {
            checkNullValuesInET(binding.emailCaTv.text.toString().trim(),binding.passwordCaEt.text.toString().trim())
        }
        //changing fragment - click on back btn
        binding.backBtn.setOnClickListener {
            //intentFragment(R.id.authFrame,, requireContext())
        }
        binding.passwordCaEt.setOnClickListener {
            // Get the current drawables (start, top, end, and bottom)
            val drawables = binding.passwordCaEt.compoundDrawablesRelative
            // Create a new drawable for the end position
            val openEye = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_remove_red_eye_24)
            // Set the new drawable as the end drawable
            val closeEye =  ContextCompat.getDrawable(requireContext(),
                R.drawable.baseline_visibility_off_24
            )

            if (binding.passwordCaEt.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())){
                //if password is visible
                binding.passwordCaEt.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.passwordCaEt.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1],closeEye, drawables[3])
            }else{
                binding.passwordCaEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.passwordCaEt.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], openEye, drawables[3])
            }
            binding.backBtn.setOnClickListener {
                intentFragment(R.id.authContainer, ChooseAuthFragment(),requireContext())
            }
        }
        return binding.root
    }

    private fun checkNullValuesInET(email: String, password: String) {
        if (email.isNotEmpty()&&password.isNotEmpty()){
            loginAccountViewHolder.signIn(email,password,requireContext())
        }else{
            Toast.makeText(requireContext(), "Error!!", Toast.LENGTH_SHORT).show()
        }
    }
}