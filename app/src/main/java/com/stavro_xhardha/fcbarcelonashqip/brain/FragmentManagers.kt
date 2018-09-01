package com.stavro_xhardha.fcbarcelonashqip.brain

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.stavro_xhardha.fcbarcelonashqip.R

internal fun FragmentManager.addFragment(containerViewId: Int,
                                         fragment: Fragment,
                                         tag: String,
                                         slideIn: Int = R.anim.fade_in,
                                         slideOut: Int = R.anim.fade_out) {
    this.beginTransaction().disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .replace(containerViewId, fragment, tag)
            .commit()
}

internal fun FragmentManager.removeFragment(tag: String,
                                            slideIn: Int = R.anim.fade_in,
                                            slideOut: Int = R.anim.fade_out) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .remove(this.findFragmentByTag(tag))
            .commitNow()
}