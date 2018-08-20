package com.stavro_xhardha.fcbarcelonashqip.ui.splash

import com.stavro_xhardha.fcbarcelonashqip.ui.splash.view.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class SplashFragmentProvider {

    @ContributesAndroidInjector(modules = [(SplashFragmentModule::class)])
    internal abstract fun provideSplashFragment(): SplashFragment
}