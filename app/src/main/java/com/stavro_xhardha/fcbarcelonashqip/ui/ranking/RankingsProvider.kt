package com.stavro_xhardha.fcbarcelonashqip.ui.ranking

import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view.RankingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RankingsProvider {
    @ContributesAndroidInjector(modules = [RankingFragmentModule::class])
    internal abstract fun provideRankingsFactory(): RankingFragment
}