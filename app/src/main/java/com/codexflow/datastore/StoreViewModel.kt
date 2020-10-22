package com.codexflow.datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StoreViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val repository = DataStoreRepository(getApplication())

    val Name = repository.userNameFlow.asLiveData()
    val Age = repository.userAgeFlow.asLiveData()

    fun saveText(newValue: String)  {
        viewModelScope.launch(Dispatchers.IO) {
            repository.storeUserInfoName(newValue)
        }
    }

    fun saveNumber(newValue: Int)  {
        viewModelScope.launch(Dispatchers.IO) {
            repository.storeUserInfoAge(newValue)
        }
    }
}