package com.vanka.skillstar.auth.createAccount

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.vanka.skillstar.reaptedCode.FragmentIntent.intentFragment
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading

class CreateAccountViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()


    fun createAccount(email: String, password: String, context: Context, id:Int, fragment: Fragment) {
        showAlertDialogForLoading(context)
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Inside your code where you want to retrieve the token
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task->
                        if (!task.isSuccessful){
                            return@addOnCompleteListener
                        }
                        val token = task.result
                        val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("token",token.toString())
                        editor.apply()

                    }

                    intentFragment(id,fragment,context)
                    dismissDialogForLoading()
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
