package com.vanka.skillstar.clubs.clubsInsideFragment.videoCource

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.clubs.clubsInsideFragment.ShareTheMyClubData
import com.vanka.skillstar.model.CourseDataModel


class ViewModelVideoCource:ViewModel() {
    private val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("Videos")
    val dataList = MutableLiveData<List<CourseDataModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        fetchDataFromDatabase()

    }
    private fun fetchDataFromDatabase() {

        isLoading.value = true

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<CourseDataModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(CourseDataModel::class.java)
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