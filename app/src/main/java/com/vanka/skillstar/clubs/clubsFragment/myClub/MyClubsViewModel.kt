package com.vanka.skillstar.clubs.clubsFragment.myClub

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.MyClubModel

class MyClubsViewModel:ViewModel() {
    private val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("MyClubs")
    val dataList = MutableLiveData<List<MyClubModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        fetchDataFromDatabase()

    }
    private fun fetchDataFromDatabase() {
        isLoading.value = true

        reference.child(FirebaseAuth.getInstance().currentUser!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<MyClubModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(MyClubModel::class.java)
                    yourData?.let { data.add(it) }
                }
                dataList.value = data
                isLoading.value = false

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}