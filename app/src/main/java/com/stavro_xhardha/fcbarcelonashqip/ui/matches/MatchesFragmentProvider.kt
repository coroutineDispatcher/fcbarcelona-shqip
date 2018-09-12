package com.stavro_xhardha.fcbarcelonashqip.ui.matches

import com.stavro_xhardha.fcbarcelonashqip.ui.matches.view.MatchesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MatchesFragmentProvider {
    @ContributesAndroidInjector(modules = [(MatchesFragmentModule::class)])
    internal abstract fun provideMatchesFragmentFactory(): MatchesFragment
}