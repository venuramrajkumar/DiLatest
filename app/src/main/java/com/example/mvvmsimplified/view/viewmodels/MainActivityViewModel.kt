package com.example.mvvmsimplified.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {

    val TAG : String = "MainActivityViewModel"

    fun displayToast() {
        Log.d("MainActivityViewModel", "MainActivityViewModel displayToast")
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
}