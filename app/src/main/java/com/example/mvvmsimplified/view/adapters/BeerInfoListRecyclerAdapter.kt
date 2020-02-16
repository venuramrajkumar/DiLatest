package com.example.mvvmsimplified.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsimplified.R
import com.example.mvvmsimplified.databinding.BeerItemBinding
import com.example.mvvmsimplified.storage.BeerInfo

class BeerInfoListRecyclerAdapter(private val context: Context, private var beerInfoList: List<BeerInfo>) :
    RecyclerView.Adapter<BeerInfoListRecyclerAdapter.BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val mBinding : BeerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.beer_item,parent,false)

        mBinding.name.isSelected = true
        mBinding.beerStyle.isSelected = true

        return BeerViewHolder(mBinding.root)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        val beerInfo = beerInfoList[position]
        holder.beerInfoBinding.name.text = beerInfo.name
        holder.beerInfoBinding.beerStyle.text = beerInfo.style
        holder.beerInfoBinding.content.text = "Alcohol Content : " + beerInfo.abv
        holder.beerInfoBinding.beerImage = "https://cdn.homebrewersassociation.org/wp-content/uploads/icon_equipment_bottling%402x.png"


    }

    override fun getItemCount(): Int {
        return beerInfoList.size
    }

    fun setBeerInfoList(beerInfoList: List<BeerInfo>) {
        this.beerInfoList = beerInfoList
    }

    inner class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var beerInfoBinding : BeerItemBinding = DataBindingUtil.bind(view)!!
    }

    companion object {

        private val TAG = BeerInfoListRecyclerAdapter::class.java.simpleName
    }
}