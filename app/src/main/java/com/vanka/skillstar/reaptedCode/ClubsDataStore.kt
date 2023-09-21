package com.vanka.skillstar.reaptedCode

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ClubsDataStore {
    fun saveListToSharedPreferences(context: Context, list: MutableSet<String>) {
        val gson = Gson()
        val json = gson.toJson(list)

        val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("MyClubs", json)
        editor.apply()
    }
     fun  getListFromSharedPreferences(context: Context): MutableSet<String> {
        val sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("MyClubs", "")

        val gson = Gson()
        val type = object : TypeToken<MutableSet<String>>() {}.type
         val emptyStringSet = mutableSetOf<String>()


         return gson.fromJson(json, type) ?: emptyStringSet
    }


}