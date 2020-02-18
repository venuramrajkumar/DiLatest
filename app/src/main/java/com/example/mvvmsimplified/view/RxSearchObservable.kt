package com.example.mvvmsimplified.view


import android.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class RxSearchObservable {

    companion object{

        fun getSearchViewObservable(searchView : SearchView): Observable<String>  {

            val publishSubject : PublishSubject<String> = PublishSubject.create()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    publishSubject.onComplete()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    publishSubject.onNext(newText!!)
                    return true
                }
            })

            return publishSubject
        }
    }
}