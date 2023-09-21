package com.vanka.skillstar.auth.createAccount

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
import com.google.firebase.FirebaseApp
import com.vanka.skillstar.R
import com.vanka.skillstar.auth.ChooseAuthFragment
import com.vanka.skillstar.auth.uploadNam.UploadName
import com.vanka.skillstar.databinding.FragmentCreateAccountBinding
import com.vanka.skillstar.reaptedCode.ClubsDataStore.saveListToSharedPreferences
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment


class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var createAccountViewModel:CreateAccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FirebaseApp.initializeApp(requireContext())

        val emptyStringSet = mutableSetOf<String>()

        saveListToSharedPreferences(requireContext(),emptyStringSet)
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        createAccountViewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]

        // Inflate the layout for this fragment
        // Observe the loading state to show/hide loading indicator
        binding.nextBtn.setOnClickListener {
            checkNullValuesInET(binding.emailCaTv.text.toString().trim(),binding.passwordCaEt.text.toString().trim())
        }
        //changing fragment - click on back btn
        binding.backBtn.setOnClickListener {
            intentFragment(R.id.authContainer, ChooseAuthFragment(),requireContext())
        }
        binding.passwordCaEt.setOnClickListener {
            // Save the current cursor position
            val cursorPosition = binding.passwordCaEt.selectionEnd

            // Get the current drawables (start, top, end, and bottom)
            val drawables = binding.passwordCaEt.compoundDrawablesRelative

            // Create a new drawable for the end position
            val openEye = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_remove_red_eye_24)
            val closeEye = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_visibility_off_24)

            if (binding.passwordCaEt.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                // If password is visible
                binding.passwordCaEt.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.passwordCaEt.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], closeEye, drawables[3])
            } else {
                binding.passwordCaEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.passwordCaEt.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], openEye, drawables[3])
            }

            // Restore the cursor position after the transformation
            binding.passwordCaEt.setSelection(cursorPosition)
        }


        return binding.root
    }

    private fun checkNullValuesInET(email: String, password: String) {
        if (email.isNotEmpty()&&password.isNotEmpty()){
            createAccountViewModel.createAccount(email,password,requireContext(),R.id.authContainer,UploadName())
        }else{
            Toast.makeText(requireContext(), "Error!!", Toast.LENGTH_SHORT).show()
        }
    }




}