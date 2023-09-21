package com.vanka.skillstar.clubs.postUpload

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.vanka.skillstar.model.PostModel
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading
import java.util.UUID
import com.vanka.skillstar.clubs.*



class ViewModelUploadPost:ViewModel() {
    // Initialize Firebase authentication and storage instances
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference
    var upImg = false
    var img = "NULL"
    fun uploadPost(postModel: PostModel,clubName:String,context:Context){
        showAlertDialogForLoading(context)
        val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
        database.getReference(clubName).child(UUID.randomUUID().toString()).setValue(postModel).addOnSuccessListener {
            dismissDialogForLoading()
        }

    }
     fun uploadImageToFirebase(imageUri: Uri, requireContext: Context, callback: ImageUploadCallback)  {

        // Show loading dialog using a utility function
        showAlertDialogForLoading(requireContext)

        // Get the current user
        val user = auth.currentUser
        user?.let {
            val userId = it.uid

            // Create a storage reference for the image with a unique name
            val storageRef = storageReference.child("post/$userId/${UUID.randomUUID()}")

            // Upload the image file to Firebase Storage
            storageRef.putFile(imageUri)
                .addOnSuccessListener {
                    // Once the image is successfully uploaded, get its download URL
                    storageRef.downloadUrl.addOnSuccessListener { imgLink ->
                        dismissDialogForLoading()
                        upImg = true
                        img = imgLink.toString()
                        callback.onSuccess(imgLink.toString())





                    }
                }
                .addOnFailureListener {
                    // If image upload fails, show an error message
                    dismissDialogForLoading()

                    Toast.makeText(requireContext, "Image upload failed", Toast.LENGTH_SHORT).show()
                    callback.onFailure("Image upload failed")
                }
        }
    }
}