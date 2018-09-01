package com.stavro_xhardha.fcbarcelonashqip.ui.topic_details

import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.topic_details.view.NewsTopicDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsTopicDetailsProvider {
    @ContributesAndroidInjector(modules = [NewsTopicDetailsFragmentModule::class])
    internal abstract fun provideNewsTopicDetailsFactory(): NewsTopicDetailsFragment
}