package com.example.mvvmsimplified.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsimplified.di.modules.SampleData
import com.example.mvvmsimplified.repositories.OrderBeerRepo
import com.example.mvvmsimplified.storage.BeerInfo
import javax.inject.Inject

class OrderBeerOnlineViewModel  @Inject constructor() : ViewModel(){
    @Inject
    lateinit var orderBeerRepo : OrderBeerRepo

    @Inject
    lateinit var sampleData: SampleData

    fun initBeerInfo() {
        orderBeerRepo.getBeerInfoApi()
        orderBeerRepo.demoMapOperator()
    }

    fun getBeerLiveData() : LiveData<List<BeerInfo>> { //Resource<>
        return orderBeerRepo.getBeerLiveData()
    }

    fun dispose() {
        orderBeerRepo.disposeElements()
    }

}