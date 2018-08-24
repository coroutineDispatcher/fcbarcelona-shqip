package com.stavro_xhardha.fcbarcelonashqip.di.builder

import com.stavro_xhardha.fcbarcelonashqip.ui.home.HomeActivityModule
import com.stavro_xhardha.fcbarcelonashqip.ui.home.view.HomeActivity
import com.stavro_xhardha.fcbarcelonashqip.ui.news.NewsFragmentProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.splash.SplashFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class), (SplashFragmentProvider::class), (NewsFragmentProvider::class)])
    abstract fun bindHomeActivity(): HomeActivity
}