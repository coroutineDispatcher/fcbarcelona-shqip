package com.stavro_xhardha.fcbarcelonashqip

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent

import org.greenrobot.eventbus.EventBus


class MatchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.title_match)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        val adapter = MatchFragmentTabAdapter(context!!, childFragmentManager)
        viewPager.adapter = adapter
        val tabLayout = view.findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object {

        fun newInstance(): MatchFragment {
            return MatchFragment()
        }
    }
}
