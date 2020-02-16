package com.example.mvvmsimplified.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvmsimplified.R
import com.example.mvvmsimplified.databinding.ActivityOrderBeerBinding
import com.example.mvvmsimplified.di.modules.SampleData
import com.example.mvvmsimplified.storage.BeerInfo

import com.example.mvvmsimplified.view.base.BaseActivity
import com.example.mvvmsimplified.view.base.ViewModelFactory
import com.example.mvvmsimplified.view.viewmodels.OrderBeerOnlineViewModel
import javax.inject.Inject

class OrderBeerOnlineActivity : BaseActivity(),SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<OrderBeerOnlineViewModel>

    lateinit var beerOnlineViewModel: OrderBeerOnlineViewModel
    lateinit var beerInfoObserver: Observer<List<BeerInfo>>
    lateinit var binding: ActivityOrderBeerBinding

    @Inject
    lateinit var sampleData: SampleData

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initObserver()
        beerOnlineViewModel.initBeerInfo()
        beerOnlineViewModel.getBeerLiveData().observe(this,beerInfoObserver)
    }

    private fun initObserver() {
        showSpinner()
        beerInfoObserver = Observer {

            dismissSpinner()

            binding = DataBindingUtil.setContentView(this,R.layout.activity_order_beer)
            binding.refreshLayout.setOnRefreshListener { this }
            binding.beerInfo = it!!
            binding.recyclerView.adapter?.notifyDataSetChanged()
            binding.refreshLayout.isRefreshing = false


            /*
            *   when (it!!.mStatus) {
                Resource.Status.SUCCESS -> {
                    dismissSpinner()
                    it.mStatus = Resource.Status.DONE
                }
                Resource.Status.LOADING -> {
                }

                Resource.Status.ERROR -> {
                }
                else -> {
                }
            }
        }
        beerliveData.removeObserver(beerInfoObserver)
        beerliveData.observe(this, beerInfoObserver)
            * */

        }

    }

    override fun getViewModel(): ViewModel {
        beerOnlineViewModel = ViewModelProviders.of(this, viewModelFactory).get(OrderBeerOnlineViewModel::class.java)
        return beerOnlineViewModel
    }


    override fun onRefresh() {
        binding.recyclerView.adapter?.notifyDataSetChanged()

        if(binding.refreshLayout.isRefreshing)
        binding.refreshLayout.isRefreshing = false

    }

    override fun onDestroy() {
        super.onDestroy()
        beerOnlineViewModel.dispose()
    }

}