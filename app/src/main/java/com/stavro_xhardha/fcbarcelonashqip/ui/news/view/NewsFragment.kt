package com.stavro_xhardha.fcbarcelonashqip.ui.news.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment

class NewsFragment : BaseFragment() {

    companion object {
        internal var TAG = "LatestNews"

        fun newInstance(): NewsFragment =
                NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_latest_news, container, false)
    }

    override fun setUp() {
    }

}
