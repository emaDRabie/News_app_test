package com.example.newsy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedCountry = MutableLiveData<String>()
    val selectedCountry: LiveData<String> = _selectedCountry

    fun setCountry(countryCode: String) {
        _selectedCountry.value = countryCode
    }
}