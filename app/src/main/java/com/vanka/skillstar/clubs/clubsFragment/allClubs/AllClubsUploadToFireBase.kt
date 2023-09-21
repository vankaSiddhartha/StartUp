package com.vanka.skillstar.clubs.clubsFragment.allClubs

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.MyClubModel
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading

object AllClubsUploadToFireBase {
     fun uploadMyClub(data: MyClubModel, context: Context){
        val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
        showAlertDialogForLoading(context)
        database.getReference("MyClubs").child(FirebaseAuth.getInstance().currentUser!!.uid).child(data.name.toString())
            .setValue(data).addOnSuccessListener {
               dismissDialogForLoading()
            }
    }
    fun deleteMyClub(key: String, context: Context){
        val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
        database.getReference("MyClubs").child(FirebaseAuth.getInstance().currentUser!!.uid).child(key).removeValue().addOnSuccessListener {
            Toast.makeText(context, "You left from club", Toast.LENGTH_SHORT).show()
        }
    }
}