package com.example.mvvmsimplified

import android.app.Application
import com.example.mvvmsimplified.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MvvmSimplifiedApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return  DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

    }


}