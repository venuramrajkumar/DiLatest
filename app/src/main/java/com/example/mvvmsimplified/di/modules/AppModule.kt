package com.example.mvvmsimplified.di.modules

import android.content.Context
import com.example.mvvmsimplified.MvvmSimplifiedApp
import com.example.mvvmsimplified.storage.BeerDao
import com.example.mvvmsimplified.storage.BeerDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun getAppContext(app: MvvmSimplifiedApp) : Context {
        return app
    }



    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): BeerDataBase = BeerDataBase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideBeerDao(beerDataBase: BeerDataBase): BeerDao = beerDataBase.getBeerDao()

}