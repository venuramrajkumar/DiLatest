package com.example.mvvmsimplified.di.modules

import com.example.mvvmsimplified.view.base.ViewModelFactory
import com.example.mvvmsimplified.view.viewmodels.OrderBeerOnlineViewModel
import dagger.Module
import dagger.Provides

@Module
class OrderBeerOnlineActivityModule {

    @Provides
    fun provideViewModelFactory(viewModel: OrderBeerOnlineViewModel) : ViewModelFactory<OrderBeerOnlineViewModel> {
        return ViewModelFactory(viewModel)
    }

}