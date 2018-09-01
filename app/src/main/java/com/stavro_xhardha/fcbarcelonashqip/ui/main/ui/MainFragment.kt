package com.stavro_xhardha.fcbarcelonashqip.ui.main.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment

class MainFragment : BaseFragment() {

    companion object {

        internal var TAG = "MainFragment"

        fun newInstance(): MainFragment =
                MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun setUp() {

    }
}