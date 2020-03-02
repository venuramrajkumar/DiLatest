package com.example.mvvmsimplified.di.component

import android.content.Context
import com.example.mvvmsimplified.MvvmSimplifiedApp
import com.example.mvvmsimplified.di.builders.ActivityBuilder
import com.example.mvvmsimplified.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class,AndroidSupportInjectionModule::class,ActivityBuilder::class])
interface AppComponent : AndroidInjector<MvvmSimplifiedApp>{

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(app:MvvmSimplifiedApp) : Builder
//        fun build():AppComponent
//    }

    @Component.Factory
    interface Factory {
    fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}