package com.example.mvvmsimplified.di.builders

import com.example.mvvmsimplified.view.MainActivity
import com.example.mvvmsimplified.di.modules.MainActivityModule
import com.example.mvvmsimplified.di.modules.NetworkModule
import com.example.mvvmsimplified.di.modules.OrderBeerOnlineActivityModule
import com.example.mvvmsimplified.di.modules.RetrofitModule
import com.example.mvvmsimplified.view.OrderBeerOnlineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module (includes = [RetrofitModule::class, NetworkModule::class])
abstract class ActivityBuilder {

    @ContributesAndroidInjector (modules = [MainActivityModule::class])
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [OrderBeerOnlineActivityModule::class])
    abstract fun bindOrderBeerOnlineActivity(): OrderBeerOnlineActivity
}