package com.example.mvvmsimplified.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    val TAG : String = "MainActivityViewModel"



    fun initSearch(searchViewObservable: Observable<String>) {

        searchViewObservable
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter {
                text -> if (text.isEmpty()) false else true
            }
            .distinctUntilChanged()
            .switchMap { text -> getDataFromNetwork(text)
                .doOnError { throwable -> Log.d("MainActivityViewModel",throwable.toString()) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getListOfStrings())

            }

    private fun getDataFromNetwork(text: String): Observable<List<String>> {
        val list = arrayListOf<String>("RAM", "RAJ", "RAJKUMAR", "RAJA")
        val listR = arrayListOf<String>("RMA", "RJA", "RJKUMAR", "RAJA","RMZ")
        val listS = arrayListOf<String>("K")
        if (text.contains("RA"))
        return Observable.just(list)
        else if (text. contains("R"))
            return Observable.just(listR)
        else return Observable.just(listS)
    }


    fun demoPublishSubject() {
//        val observableItems : PublishSubject<String> = PublishSubject.create()
//        val observableItems : ReplaySubject<String> = ReplaySubject.create()
//        val observableItems : BehaviorSubject<String> = BehaviorSubject.create()
        val observableItems : AsyncSubject<String> = AsyncSubject.create()

        observableItems.subscribe(getFirstObserver())

        observableItems.onNext("VENU")
        observableItems.onNext("RAM")

        observableItems.subscribe(getSecondObserver())
        observableItems.onNext("RAJ")
        observableItems.onComplete()

        //Lambda expression
//        observableItems.subscribe({
//                result -> Log.d(TAG + "Lambda", result)
//
//        })
    }


    fun getListOfStrings() : Observer<List<String>> {
        return object : Observer<List<String>> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: List<String>) {
                Log.d(TAG, "list size is "+t.size.toString())
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }

        }
    }

    fun getFirstObserver() : Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.d(TAG, t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }

        }
    }



    fun getSecondObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.d(TAG+2, "onComplete2")
            }


            override fun onNext(t: String) {
                Log.d(TAG + 2, t)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG+2, "onError")
            }

        }
    }

    fun displayToast() {
        Log.d("MainActivityViewModel", "MainActivityViewModel displayToast")
    }



}