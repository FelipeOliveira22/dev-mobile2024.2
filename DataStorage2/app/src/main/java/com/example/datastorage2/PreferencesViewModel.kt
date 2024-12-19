package com.example.datastorage2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow

class PreferencesViewModel(context: Context) : ViewModel(){
    private val key = "user_input"

    val readValue: Flow<String> = readPreference(context, key)

    suspend fun saveValue(value: String, context: Context) {
        savePreference(context, key, value)
    }
}

class PreferenceViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PreferencesViewModel(context) as T
    }
}