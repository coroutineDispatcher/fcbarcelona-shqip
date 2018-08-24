package com.stavro_xhardha.fcbarcelonashqip.ui.home.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.brain.addFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseActivity
import com.stavro_xhardha.fcbarcelonashqip.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : BaseActivity(), HomeMVPView, NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupDrawer()
        openNewFragment(MainFragment.newInstance(), MainFragment.TAG)
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {

            }
            R.id.nav_home -> {

            }
            R.id.nav_table -> {

            }
            R.id.nav_match -> {

            }
            R.id.nav_team -> {

            }
            R.id.nav_team_info -> {
                openNewFragment(MainFragment.newInstance(), MainFragment.TAG)
            }
            R.id.nav_exit -> {
                finish()
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupDrawer() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun lockDrawer() = drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer() = drawer_layout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    private fun openNewFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.addFragment(R.id.home_container, fragment, tag)
    }


}
