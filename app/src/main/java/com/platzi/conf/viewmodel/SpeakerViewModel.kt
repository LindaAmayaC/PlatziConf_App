package com.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel : ViewModel(){

    val firestoreService = FirestoreService() //instanciamos la clase firestore para poder usar sus funciones
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData() //fusionamos viewmodel con livedata
    var isLoading= MutableLiveData<Boolean>() // nos permite actualizar la ui de carga

    fun refresh(){
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase() {
        firestoreService.getSpeaker(object: Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinish()
            }

            override fun onFailed(exception: Exception) {
                processFinish()
            }
        })
    }

    private fun processFinish() {
        isLoading.value=true
    }
}