package com.stavro_xhardha.fcbarcelonashqip.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.stavro_xhardha.fcbarcelonashqip.brain.Utilities
import dagger.android.support.AndroidSupportInjection


/**
 * Created by jyotidubey on 12/01/18.
 */
abstract class BaseFragment : Fragment(), MVPView {

    private var parentActivity: BaseActivity? = null
    private var progressDialog: MaterialDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = Utilities.showLoadingMaterialDialog(this!!.context!!)
    }

    fun getBaseActivity() = parentActivity

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun setUp()
}