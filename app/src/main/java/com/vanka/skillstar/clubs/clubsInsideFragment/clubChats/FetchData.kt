package com.vanka.skillstar.clubs.clubsInsideFragment.clubChats

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.PostModel

object FetchData {
    val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("ClubsChat")
    val dataList = MutableLiveData<List<PostModel>>()
    val isLoading = MutableLiveData<Boolean>()
    fun fetchDataFromDatabase(childKey:String) {

        isLoading.value = true

        reference.child(childKey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<PostModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(PostModel::class.java)
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