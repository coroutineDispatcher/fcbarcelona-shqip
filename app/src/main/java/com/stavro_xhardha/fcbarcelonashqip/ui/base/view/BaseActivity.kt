package com.stavro_xhardha.fcbarcelonashqip.ui.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.stavro_xhardha.fcbarcelonashqip.brain.Utilities
import dagger.android.AndroidInjection

/**
 * Created by jyotidubey on 04/01/18.
 */
abstract class BaseActivity : AppCompatActivity(), MVPView, BaseFragment.CallBack {

    private var materialProgressDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun hideProgress() {
        materialProgressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        materialProgressDialog = Utilities.showLoadingMaterialDialog(this)
    }

    private fun performDI() = AndroidInjection.inject(this)

}