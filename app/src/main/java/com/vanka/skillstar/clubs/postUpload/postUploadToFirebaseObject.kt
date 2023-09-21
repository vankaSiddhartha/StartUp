package com.vanka.skillstar.clubs.postUpload

import android.content.Context
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.PostModel
import com.vanka.skillstar.reaptedCode.Loading
import java.util.UUID

object postUploadToFirebaseObject {
  fun uploadPost(context:Context,data:PostModel,childName:String){
      val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
      Loading.showAlertDialogForLoading(context)
      database.getReference("ClbusChat").child(childName).child(UUID.randomUUID().toString()).setValue(data).addOnCompleteListener {
          Loading.dismissDialogForLoading()
      }
  }
}