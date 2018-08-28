package com.stavro_xhardha.fcbarcelonashqip.ui.main

import com.stavro_xhardha.fcbarcelonashqip.ui.main.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = [(MainFragmentModule::class)])
    internal abstract fun provideMainFragment(): MainFragment
}