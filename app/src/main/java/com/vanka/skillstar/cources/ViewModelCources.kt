package com.vanka.skillstar.cources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.ClubModel
import com.vanka.skillstar.model.WhatchVideoModel

class ViewModelCources:ViewModel() {
    private val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("VideoList")
    val dataList = MutableLiveData<List<WhatchVideoModel>>()
    val isLoading = MutableLiveData<Boolean>()



       fun fetchDataFromDatabase(key:String) {
        isLoading.value = true

        reference.child(key).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<WhatchVideoModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(WhatchVideoModel::class.java)
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