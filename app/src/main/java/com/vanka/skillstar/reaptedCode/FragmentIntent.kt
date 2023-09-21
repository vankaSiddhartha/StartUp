package com.vanka.skillstar.reaptedCode

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object FragmentIntent {
    fun intentFragment(id:Int, fragment: Fragment, context: Context){

        val load = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        load.replace(id, fragment)
        load.addToBackStack(null) // Add this line to enable back navigation
        load.commit()
    }
}