package com.stavro_xhardha.fcbarcelonashqip

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.MenuItem

import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.OpenNewFragmentEvent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import com.crashlytics.android.Crashlytics
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent

import io.fabric.sdk.android.Fabric

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mCurrentFragmentTag: String? = null
    private var navigationView: NavigationView? = null
    private var drawer: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Fabric.with(this, Crashlytics())
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle!!)
        toggle!!.syncState()

        navigationView = findViewById(R.id.nav_view)

        val menu = navigationView!!.menu

        val tools = menu.findItem(R.id.tools)
        val s = SpannableString(tools.title)
        s.setSpan(TextAppearanceSpan(this, R.style.menu_item_color), 0, s.length(), 0)
        tools.title = s
        navigationView!!.setNavigationItemSelectedListener(this)
        openFragment(SplashWelcomeFragment.newInstance())
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            if (mCurrentFragmentTag!!.equals(Brain.INSTANCE.getTABLE_FRAGMENT_TAG(), ignoreCase = true)
                    || mCurrentFragmentTag!!.equals(Brain.INSTANCE.getTEAM_FRAGMENT_TAG(), ignoreCase = true)
                    || mCurrentFragmentTag!!.equals(Brain.INSTANCE.getSCHEDULED_MATCHES_FRAGMENT_TAG(), ignoreCase = true)
                    || mCurrentFragmentTag!!.equals(Brain.INSTANCE.getHISTORY_MATCH_FRAGMENT_TAG(), ignoreCase = true)
                    || mCurrentFragmentTag!!.equals(Brain.INSTANCE.getLATEST_NEWS_FRAGMENT_TAG(), ignoreCase = true)
                    || mCurrentFragmentTag!!.equals(Brain.INSTANCE.getWHATS_NEW_ON_CLUB_FRAGMENT_TAG(), ignoreCase = true)) {

                openFragment(TeamInfoFragment.newInstance())

            } else {
                super.onBackPressed()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_home) {
            openFragment(TeamYoutubeChannelFragment.newInstance())
        } else if (id == R.id.nav_table) {
            openFragment(TableFragment.newInstance())
        } else if (id == R.id.nav_match) {
            openFragment(MatchFragment.newInstance())
        } else if (id == R.id.nav_team) {
            openFragment(TeamFragment.newInstance())
        } else if (id == R.id.nav_team_info) {
            openFragment(TeamInfoFragment.newInstance())
            unselectMenuItems()
        } else if (id == R.id.nav_news) {
            openFragment(LatestNewsFragment.newInstance())
            unselectMenuItems()
        } else if (id == R.id.nav_exit) {
            finish()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun unselectMenuItems() {
        for (i in 0..4)
            navigationView!!.menu.getItem(i).isChecked = false
    }

    private fun openFragment(mFragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, mFragment)
                .commit()
    }

    private fun openNewFragmentWithAnimation(mFragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, mFragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: CheckNetworkEvent?) {
        if (event != null) {
            openNetworkErrorDialog()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: SetFragmentTitleEvent?) {
        if (event != null) {
            this.title = event.fragmentTitle
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ControlToolbarVisibilityevent?) {
        if (event != null) {
            if (supportActionBar != null) {
                if (event.isShowToolbar) {
                    this.supportActionBar!!.show()
                } else {
                    this.supportActionBar!!.hide()
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: SetFragmenTagEvent?) {
        if (event != null) {
            mCurrentFragmentTag = event.fragmentTag
        }
        if (mCurrentFragmentTag!!.equals(Brain.INSTANCE.getSPLASH_FRAGMENT_TAG(), ignoreCase = true)) {
            toggle!!.isDrawerIndicatorEnabled = false
            drawer!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            toggle!!.isDrawerIndicatorEnabled = true
            drawer!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: OpenNewFragmentEvent?) {
        if (event != null) {
            openNewFragmentWithAnimation(event.fragment)
        }
    }

    private fun openNetworkErrorDialog() {
        if (!Brain.INSTANCE.isNetworkAvailable(this)) {
            MaterialDialog.Builder(this)
                    .title(R.string.error_network_title)
                    .content(R.string.check_internet_connection)
                    .positiveText(R.string.agree)
                    .onPositive { dialog, which ->
                        dialog.dismiss()
                        startActivityForResult(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0)
                    }
                    .negativeText(R.string.dissagree)
                    .onNegative { dialog, which ->
                        EventBus.getDefault().post(RefreshDataEvent())
                        dialog.dismiss()
                    }
                    .cancelable(false)
                    .show()
        }
    }

}
