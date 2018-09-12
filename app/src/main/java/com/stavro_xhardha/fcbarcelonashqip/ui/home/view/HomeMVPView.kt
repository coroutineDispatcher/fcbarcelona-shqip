package com.stavro_xhardha.fcbarcelonashqip.ui.home.view

import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface HomeMVPView : MVPView {
    fun lockDrawer(): Unit?
    fun unlockDrawer(): Unit?
    fun openNewsFragment()
    fun openExpandedNewsFragmentAndUpdateView()
    fun openTableFragment()
    fun openMatchesFragment()
    fun openTeamFragment()
    fun showUpdateViewErrorSnackBar()
}
