package com.example.proyecto1mobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _changeActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    val changeActivity: LiveData<Boolean> = _changeActivity
    private var _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError


    fun sendForm(username:String, password:String){
        if (password=="Admin"){
            _changeActivity.value = true
        }else{
            _showError.value = true
        }
    }
}