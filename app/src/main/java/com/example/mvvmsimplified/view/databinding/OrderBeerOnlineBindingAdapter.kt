package com.example.mvvmsimplified.view.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsimplified.storage.BeerInfo

import com.example.mvvmsimplified.view.adapters.BeerInfoListRecyclerAdapter

class OrderBeerOnlineBindingAdapter {

    companion object {
        @BindingAdapter("BeerList")
        @JvmStatic
        fun setBeerList(recyclerView: RecyclerView, beerList: List<BeerInfo>) {
//            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context,2) as RecyclerView.LayoutManager?

            var adapter = BeerInfoListRecyclerAdapter(recyclerView.context, beerList)
            recyclerView.adapter = adapter
        }
    }


}