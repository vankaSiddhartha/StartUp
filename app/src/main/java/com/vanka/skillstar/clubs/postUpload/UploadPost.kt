package com.vanka.skillstar.clubs.postUpload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vanka.skillstar.clubs.postUpload.postUploadToFirebaseObject.uploadPost
import com.vanka.skillstar.databinding.ActivityUploadPostBinding
import com.vanka.skillstar.model.PostModel
import java.util.UUID

class UploadPost : AppCompatActivity(),ImageUploadCallback{
    private lateinit var img1:String
    private lateinit var binding:ActivityUploadPostBinding
    private lateinit var viewModel:ViewModelUploadPost
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
            Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Pick image result launcher
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri = data?.data
            binding.imageViewPreview.setImageURI(selectedImageUri)
             viewModel.uploadImageToFirebase(selectedImageUri!!, this,this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        img1 = "NULL"
        val sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name","player")
        val img = sharedPreferences.getString("profile","sms")
        val token = sharedPreferences.getString("token","null")
        binding = ActivityUploadPostBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ViewModelUploadPost::class.java]
        setContentView(binding.root)
        binding.buttonAddImage.setOnClickListener {
            readPermission()
         //   Toast.makeText(this, img, Toast.LENGTH_SHORT).show()
        }
        binding.buttonPost.setOnClickListener {
            val data = PostModel(UUID.randomUUID().toString(),name,img,binding.editTextCaption.text.toString(),img1?:"NULL",token)
            uploadPost(this,data,intent.getStringExtra("name")!!)
        }


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
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onSuccess(imageUrl: String) {
      img1 = imageUrl
    }

    override fun onFailure(errorMessage: String) {
        img1 = "NULL"

    }


}