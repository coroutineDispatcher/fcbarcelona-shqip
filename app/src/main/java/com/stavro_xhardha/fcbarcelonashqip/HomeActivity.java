package com.stavro_xhardha.fcbarcelonashqip;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.OpenNewFragmentEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String mCurrentFragmentTag;
    private NavigationView navigationView;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fabric.with(this, new Crashlytics());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();

        MenuItem tools = menu.findItem(R.id.tools);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.menu_item_color), 0, s.length(), 0);
        tools.setTitle(s);
        navigationView.setNavigationItemSelectedListener(this);
        openFragment(SplashWelcomeFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mCurrentFragmentTag.equalsIgnoreCase(Brain.TABLE_FRAGMENT_TAG)
                    || mCurrentFragmentTag.equalsIgnoreCase(Brain.TEAM_FRAGMENT_TAG)
                    || mCurrentFragmentTag.equalsIgnoreCase(Brain.SCHEDULED_MATCHES_FRAGMENT_TAG)
                    || mCurrentFragmentTag.equalsIgnoreCase(Brain.HISTORY_MATCH_FRAGMENT_TAG)
                    || mCurrentFragmentTag.equalsIgnoreCase(Brain.FC_BARCELONA_PAGE_FRAGMENT_TAG)
                    || mCurrentFragmentTag.equalsIgnoreCase(Brain.LATEST_NEWS_FRAGMENT_TAG)) {

                openFragment(TeamInfo.newInstance());

            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            openFragment(TeamYoutubeChannelFragment.newInstance());
        } else if (id == R.id.nav_table) {
            openFragment(TableFragment.newInstance());
        } else if (id == R.id.nav_match) {
            openFragment(MatchFragment.newInstance());
        } else if (id == R.id.nav_team) {
            openFragment(TeamFragment.newInstance());
        } else if (id == R.id.nav_team_info) {
            openFragment(TeamInfo.newInstance());
            unselectMenuItems();
        } else if (id == R.id.nav_news) {
            openFragment(LatestNewsFragment.newInstance());
            unselectMenuItems();
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void unselectMenuItems() {
        for (int i = 0; i < 5; i++)
            navigationView.getMenu().getItem(i).setChecked(false);
    }

    private void openFragment(Fragment mFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_container, mFragment)
                .commit();
    }

    private void openNewFragmentWithAnimation(Fragment mFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_container, mFragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CheckNetworkEvent event) {
        if (event != null) {
            openNetworkErrorDialog();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SetFragmenTagEvent event) {
        if (event != null) {
            mCurrentFragmentTag = event.getFragmentTag();
        }
        if (mCurrentFragmentTag.equalsIgnoreCase(Brain.SPLASH_FRAGMENT_TAG)) {
            toggle.setDrawerIndicatorEnabled(false);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OpenNewFragmentEvent event) {
        if (event != null) {
            openNewFragmentWithAnimation(event.getFragment());
        }
    }

    private void openNetworkErrorDialog() {
        if (!Brain.isNetworkAvailable(this)) {
            new MaterialDialog.Builder(this)
                    .title(R.string.error_network_title)
                    .content(R.string.check_internet_connection)
                    .positiveText(R.string.agree)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
                        }
                    })
                    .negativeText(R.string.dissagree)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            EventBus.getDefault().post(new RefreshDataEvent());
                            dialog.dismiss();
                        }
                    })
                    .cancelable(false)
                    .show();
        }
    }
}
