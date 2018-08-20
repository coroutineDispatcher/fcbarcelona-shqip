package com.stavro_xhardha.fcbarcelonashqip.ui.splash.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R

class SplashFragment : Fragment() {

    companion object {
        fun newInstance(): SplashFragment =
                SplashFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_welcome, container, false)
    }


}
