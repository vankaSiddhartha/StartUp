package com.vanka.skillstar.events

import android.app.usage.UsageEvents.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.EventModel
import com.vanka.skillstar.model.WhatchVideoModel

class ViewModelEventFragment:ViewModel() {
    private val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("Events")
    val dataList = MutableLiveData<List<EventModel>>()
    val isLoading = MutableLiveData<Boolean>()


init {

    fetchDataFromDatabase()
}
    fun fetchDataFromDatabase() {
        isLoading.value = true

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<EventModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(EventModel::class.java)
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