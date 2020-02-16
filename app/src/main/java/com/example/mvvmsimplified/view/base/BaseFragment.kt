package com.example.mvvmsimplified.view.base

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    private lateinit var viewModel: ViewModel

    abstract fun getViewModel() : ViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getViewModel()
    }




}