package com.stavro_xhardha.fcbarcelonashqip.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R

class MainFragment : Fragment() {
    companion object {
        internal var TAG = "MainFragment"

        fun newInstance(): MainFragment =
                MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }
}
