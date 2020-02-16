package com.example.mvvmsimplified.view.base

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.mvvmsimplified.view.ProgressDialog
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    private var progressDialog: Dialog? = null

    abstract fun getViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
    }

    fun showSpinner() {

        if (null == progressDialog)
            progressDialog = ProgressDialog.progressDialog(this)
        progressDialog!!.show()
    }

    fun dismissSpinner() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

}