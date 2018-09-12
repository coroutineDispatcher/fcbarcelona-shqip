package com.stavro_xhardha.fcbarcelonashqip.ui.team

import com.stavro_xhardha.fcbarcelonashqip.ui.team.view.TeamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TeamFragmentProvider {
    @ContributesAndroidInjector(modules = [(TeamFragmentModule::class)])
        internal abstract fun provideTeamFragmentFactory() : TeamFragment
}