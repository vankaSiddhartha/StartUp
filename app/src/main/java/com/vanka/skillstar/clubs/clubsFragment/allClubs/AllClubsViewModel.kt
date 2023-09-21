package com.vanka.skillstar.clubs.clubsFragment.allClubs

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanka.skillstar.model.ClubModel
import com.vanka.skillstar.model.MyClubModel
import com.vanka.skillstar.reaptedCode.Loading.dismissDialogForLoading
import com.vanka.skillstar.reaptedCode.Loading.showAlertDialogForLoading
import java.util.UUID

class AllClubsViewModel:ViewModel() {
    private val database =  Firebase.database("https://skillstar-247a7-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val reference = database.reference.child("clubs")
    val dataList = MutableLiveData<List<ClubModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        fetchDataFromDatabase()

    }
    private fun fetchDataFromDatabase() {
        isLoading.value = true

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = mutableListOf<ClubModel>()
                for (childSnapshot in snapshot.children) {
                    val yourData = childSnapshot.getValue(ClubModel::class.java)
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