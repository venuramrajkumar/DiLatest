package com.example.mvvmsimplified.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    fun displayToast() {
        Log.d("MainActivityViewModel","MainActivityViewModel displayToast")
    }

}