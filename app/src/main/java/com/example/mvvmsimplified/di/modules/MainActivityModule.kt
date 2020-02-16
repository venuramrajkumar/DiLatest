package com.example.mvvmsimplified.di.modules

import com.example.mvvmsimplified.view.base.ViewModelFactory
import com.example.mvvmsimplified.view.viewmodels.MainActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun getStrings() : List<String> {
        return ArrayList<String>()
    }

    @Provides
    fun  provideViewModelFactory(viewModel: MainActivityViewModel) : ViewModelFactory<MainActivityViewModel> {
        return ViewModelFactory(viewModel)
    }

}