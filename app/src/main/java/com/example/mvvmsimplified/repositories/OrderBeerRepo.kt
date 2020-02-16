package com.example.mvvmsimplified.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsimplified.rest.IGetBeerInfoAPI
import com.example.mvvmsimplified.storage.BeerDao
import com.example.mvvmsimplified.storage.BeerInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DefaultObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.functions.Function

import io.reactivex.schedulers.Schedulers


import javax.inject.Inject

class OrderBeerRepo @Inject constructor() {

    @Inject
    lateinit var iGetBeerInfoAPI : IGetBeerInfoAPI

    private  var beerLiveData : MutableLiveData<List<BeerInfo>> = MutableLiveData()
    private var dataError: MutableLiveData<String> = MutableLiveData()

    private var disposable :CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var beerDao : BeerDao

    private  var observableToStorage = CompositeDisposable()


    fun getBeerInfoApi() {

        disposable.add(iGetBeerInfoAPI.getBeerInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getObserver()))

    }


    fun demoMapOperator() {

        iGetBeerInfoAPI.getBeerInfo().map { listOfBeerInfo -> convertToStringList(listOfBeerInfo)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getStringObserver())

//        iGetBeerInfoAPI.getBeerInfo().map (object : Function<List<BeerInfo>, List<String>> {
//            override fun apply(t: List<BeerInfo>): List<String> {
//                return convertToStringList(t)
//            }
//
//        }).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getStringObserver())

    }

    private fun convertToStringList(listOfBeerInfo: List<BeerInfo>): List<String> {
        val stringList  = arrayListOf<String>()
        System.out.println("Thread name is " + Thread.currentThread().id)

        for (item in listOfBeerInfo) {
            stringList.add(item.name)
        }
        return stringList
    }


    private fun getStringObserver() : DefaultObserver<List<String>> {
        return object : DefaultObserver<List<String>>() {
            override fun onComplete() {
                Log.d("OrderBeerRepo", "onComplete")
            }

            override fun onNext(t: List<String>) {
                Log.d("OrderBeerRepo", t.get(0))
            }

            override fun onError(e: Throwable) {
                Log.d("OrderBeerRepo", "ERRROR")
            }

        }
    }



    fun getBeerInfoFromDb() {
        disposable.add(beerDao.getAllBeers().toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.d("OrderBeerRepo",it.size.toString())
            }
            .subscribeWith(getOfflineObserver()))
    }

    fun getBeerLiveData() : LiveData<List<BeerInfo>> {
        return beerLiveData
    }

    private fun getObserver() : DisposableObserver<List<BeerInfo>> {
        return object :DisposableObserver<List<BeerInfo>>() {
            override fun onComplete() {
                disposable.dispose()
                Log.d("OrderBeerRepo" , "onComplete")
            }

            override fun onNext(beerInfo: List<BeerInfo>) {//Resource<List<BeerInfo>>
                Log.d("OrderBeerRepo",beerInfo.toString())

                beerLiveData.postValue(beerInfo)

                disposable.add(Observable.just(beerInfo).subscribeOn(Schedulers.io()).subscribe {
                    for (item in it) {
                        beerDao.insertBeer(item)
                        Log.d("OrderBeerRepo Thread",Thread.currentThread().toString())
                    }
                })

                //beerLiveData.value = beerInfo
                //beerLiveData.value = (Resource.success(beerInfo.mData))
            }

            override fun onError(e: Throwable) {
                Log.d("OrderBeerRepo" , e.message)
                dataError.postValue(e.message)

                getBeerInfoFromDb()
            }

        }
    }


    private fun getOfflineObserver() : DisposableObserver<List<BeerInfo>> {
        return object :DisposableObserver<List<BeerInfo>>() {
            override fun onComplete() {
                disposable.dispose()
                Log.d("OrderBeerRepo" , "onComplete")
            }

            override fun onNext(beerInfo: List<BeerInfo>) {//Resource<List<BeerInfo>>
                Log.d("OrderBeerRepo",beerInfo.toString())

                beerLiveData.postValue(beerInfo)

                //beerLiveData.value = beerInfo
                //beerLiveData.value = (Resource.success(beerInfo.mData))
            }

            override fun onError(e: Throwable) {
                Log.d("OrderBeerRepo" , e.message)
                dataError.postValue(e.message)

            }

        }
    }


    fun disposeElements(){
        if(null != disposable && !disposable.isDisposed) disposable.dispose()
    }

}