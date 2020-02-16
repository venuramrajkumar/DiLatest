package com.example.mvvmsimplified.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsimplified.R
import com.example.mvvmsimplified.databinding.ActivityMainBinding
import com.example.mvvmsimplified.databinding.ContentMainBinding
import com.example.mvvmsimplified.rest.Title
import com.example.mvvmsimplified.view.base.BaseActivity
import com.example.mvvmsimplified.view.base.ViewModelFactory
import com.example.mvvmsimplified.view.viewmodels.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var mainActivityViewModel : MainActivityViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainActivityViewModel>

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val title = Title("ORDER BEER","ROOM DATA BASE","CALL DOCTOR")
        mBinding.contentMain.buttontitle = title

        supportActionBar?.setTitle("RAJKUMAR")
        setSupportActionBar(toolbar)

        mainActivityViewModel.demoPublishSubject()

        mainActivityViewModel.displayToast()
        orderBeer.setOnClickListener{ startActivity(Intent(this,OrderBeerOnlineActivity::class.java)) }


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun getViewModel(): MainActivityViewModel {
        mainActivityViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainActivityViewModel::class.java)
        return mainActivityViewModel
    }
}
