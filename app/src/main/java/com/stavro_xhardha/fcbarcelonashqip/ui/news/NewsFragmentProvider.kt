package com.stavro_xhardha.fcbarcelonashqip.ui.news

import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsFragmentProvider {
    @ContributesAndroidInjector(modules = [NewsFragmentModule::class])
    internal abstract fun provideNewsFragmentFactory(): NewsFragment
}