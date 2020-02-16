package com.example.mvvmsimplified.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<V>(private val viewModel: V) : ViewModelProvider.Factory {

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isInstance(viewModel)) {
            viewModel as T
        }
        else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}

