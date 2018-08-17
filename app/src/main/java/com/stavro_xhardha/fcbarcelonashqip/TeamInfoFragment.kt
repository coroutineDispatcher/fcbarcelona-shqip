package com.stavro_xhardha.fcbarcelonashqip

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain

import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent

import org.greenrobot.eventbus.EventBus

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.FC_BARCELONA_PAGE_FRAGMENT_TAG

class TeamInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.barcelona_team_info)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.FC_BARCELONA_PAGE_FRAGMENT_TAG))
    }

    companion object {

        fun newInstance(): TeamInfoFragment {
            return TeamInfoFragment()
        }
    }
}
