package com.stavro_xhardha.fcbarcelonashqip.di.builder

import com.stavro_xhardha.fcbarcelonashqip.HomeActivity
import com.stavro_xhardha.fcbarcelonashqip.ui.splash.SplashFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashFragmentProvider::class)])
    abstract fun bindHomeActivity(): HomeActivity
}