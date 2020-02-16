package com.example.mvvmsimplified.rest

import com.example.mvvmsimplified.storage.BeerInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface IGetBeerInfoAPI {

    @GET("beercraft")
    fun getBeerInfo() : Observable<List<BeerInfo>>

}