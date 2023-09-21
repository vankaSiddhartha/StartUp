package com.vanka.skillstar.auth.uploadProfile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.vanka.skillstar.MainActivity
import com.vanka.skillstar.R
import com.vanka.skillstar.auth.uploadNam.UploadName
import com.vanka.skillstar.databinding.FragmentUploadProfileBinding
import com.vanka.skillstar.model.UserModel
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading


class UploadProfileFragment: Fragment() {
    // Variables
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var uploadProfileViewModel: UploadProfileViewModel

    // Request permissions result launcher
    private var permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        var isGranted = true
        for (item in map) {
            if (!item.value) {
                isGranted = false
            }
        }
        if (isGranted) {
            openGallery() // If permissions granted, open the gallery to pick an image
        } else {
            Toast.makeText(requireContext(), "Permission is denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Pick image result launcher
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri = data?.data
            binding.circleImageView.setImageURI(selectedImageUri)
            uploadProfileViewModel.uploadImageToFirebase(selectedImageUri!!, requireContext())
        }
    }

    private lateinit var binding: FragmentUploadProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // ViewModel initialization
        uploadProfileViewModel = ViewModelProvider(requireActivity())[UploadProfileViewModel::class.java]

        // Binding initialization
        binding = FragmentUploadProfileBinding.inflate(LayoutInflater.from(requireContext()))

        // Firebase Authentication and Storage initialization
        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        // Button and click listeners
        binding.imageButton.setOnClickListener {
            intentFragment(R.id.authContainer, UploadName(), requireContext())
        }
        val sharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
        val img = sharedPreferences.getString("profile", "sms")
        Log.d("SharedPreferences", "img value: $img")

        binding.nextBtn.setOnClickListener {
            if (uploadProfileViewModel.upImg) {

                startActivity(Intent(requireContext(), MainActivity::class.java))
            } else {

                uploadUserToFireBase()
            }
        }


        // Click listener for selecting profile image
        binding.circleImageView.setOnClickListener {
            readPermission() // Request permission to read from gallery
        }

        // Click listener to open the gallery
        binding.goGalleryBtn.setOnClickListener {
            readPermission() // Request permission to read from gallery
        }

        return binding.root
    }

    // Method to open the gallery to select an image
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }

    // Method to check and request permission to read from the gallery
    private fun readPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (!hasPermissionCheck(permission[0])) {
            permissionLauncher.launch(permission)
        } else {
            openGallery() // If permission already granted, open the gallery

        }
    }

    // Method to check if a permission is granted
    private fun hasPermissionCheck(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }
    private fun uploadUserToFireBase() {
        val sharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name","player")
        val data = UserModel(name,"nop")
        Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("user").child(
            FirebaseAuth.getInstance().currentUser!!.uid.toString())
            .setValue(data).addOnSuccessListener {
                // Dismiss the loading dialog using a utility function
                dismissDialogForLoading()
                startActivity(Intent(requireContext(),MainActivity::class.java))
            }.addOnFailureListener {err->
                Toast.makeText(requireContext( ), err.toString(), Toast.LENGTH_SHORT).show()
                dismissDialogForLoading()
            }

    }
}
