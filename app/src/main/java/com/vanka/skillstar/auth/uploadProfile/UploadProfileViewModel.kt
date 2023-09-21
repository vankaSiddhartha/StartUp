package com.vanka.skillstar.auth.uploadProfile

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.vanka.skillstar.model.UserModel
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading
import java.util.*

class UploadProfileViewModel : ViewModel() {

    // Initialize Firebase authentication and storage instances
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference
    var upImg = false

    // Function to upload image to Firebase Storage
    fun uploadImageToFirebase(imageUri: Uri, requireContext: Context) {
        val sharedPreferences = requireContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        // Show loading dialog using a utility function
        showAlertDialogForLoading(requireContext)

        // Get the current user
        val user = auth.currentUser
        user?.let {
            val userId = it.uid

            // Create a storage reference for the image with a unique name
            val storageRef = storageReference.child("profile/$userId/${UUID.randomUUID()}")

            // Upload the image file to Firebase Storage
            storageRef.putFile(imageUri)
                .addOnSuccessListener {
                    // Once the image is successfully uploaded, get its download URL
                    storageRef.downloadUrl.addOnSuccessListener { imgLink ->
                        upImg =true
                        // Save the image URL in a shared object for later use
                        editor.putString("profile",imgLink.toString())
                        editor.apply()

                        uploadUserToFireBase(requireContext)

                    }
                }
                .addOnFailureListener {
                    // If image upload fails, show an error message
                    Toast.makeText(requireContext, "Image upload failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun uploadUserToFireBase(requireContext: Context) {
        val sharedPreferences = requireContext.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name","player")
        val img = sharedPreferences.getString("profile","sms")
        val token = sharedPreferences.getString("token","null")
        val data = UserModel(name,img,token)
        Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("user").child(FirebaseAuth.getInstance().currentUser!!.uid.toString())
            .setValue(data).addOnSuccessListener {
                // Dismiss the loading dialog using a utility function
                dismissDialogForLoading()
            }.addOnFailureListener {err->
                Toast.makeText(requireContext, err.toString(), Toast.LENGTH_SHORT).show()
                dismissDialogForLoading()
            }

    }
}
