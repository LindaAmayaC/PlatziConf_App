package com.platzi.conf.viewmodel

import android.telecom.Conference
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel(){
    //comunicar de firestore con la ui
    val firestoreService = FirestoreService() //instanciamos la clase firestore para poder usar sus funciones
    var listSchedule : MutableLiveData<List<com.platzi.conf.model.Conference>> = MutableLiveData() //fusionamos viewmodel con livedata
    var isLoading= MutableLiveData<Boolean>() // nos permite actualizar la ui de carga

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<com.platzi.conf.model.Conference>>{
            override fun onSuccess(result: List<com.platzi.conf.model.Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}