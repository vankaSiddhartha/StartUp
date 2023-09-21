package com.vanka.skillstar.auth.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading

class LoginViewModel:ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String, context: Context) {
         showAlertDialogForLoading(context)
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {


//                    Firebase.database("https://kurukshetra-esports-b7b06-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid)
//                        .addListenerForSingleValueEvent(object :ValueEventListener{
//                            override fun onDataChange(snapshot: DataSnapshot) {
//                                val data = snapshot.getValue(UserModel::class.java)
//                                val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)
//                                // Inflate the layout for this fragment
//                                val editor = sharedPreferences.edit()
//                                editor.putString("name",data?.name)
//                                editor.putString("profile",data?.profile)
//                                editor.apply()
//                                context.startActivity(Intent(context,MainActivity::class.java))
//                            }
//
//                            override fun onCancelled(error: DatabaseError) {
//
//                            }
//
//                        })
                } else {
                    // Sign-in failed
                    val exception = task.exception.toString()
                    // Handle the error
                    Toast.makeText(context, exception, Toast.LENGTH_SHORT).show()
                    dismissDialogForLoading()
                }
            }
    }


}