package com.stavro_xhardha.fcbarcelonashqip

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain

import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.OpenNewFragmentEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.StartCountDownEvent

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.SPLASH_FRAGMENT_TAG

class SplashWelcomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_welcome, container, false)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        EventBus.getDefault().post(StartCountDownEvent())
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.SPLASH_FRAGMENT_TAG))
        EventBus.getDefault().post(ControlToolbarVisibilityevent(false))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: StartCountDownEvent) {
        startCountdown()
    }

    private fun startCountdown() {
        val timer = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    EventBus.getDefault().post(OpenNewFragmentEvent(TeamInfoFragment.newInstance()))
                }
            }
        }
        timer.start()
    }

    companion object {

        fun newInstance(): SplashWelcomeFragment {
            return SplashWelcomeFragment()
        }
    }
}