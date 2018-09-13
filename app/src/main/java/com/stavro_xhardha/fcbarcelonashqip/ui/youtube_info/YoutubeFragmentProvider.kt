package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info

import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui.YoutubeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class YoutubeFragmentProvider {
    @ContributesAndroidInjector(modules = [(YoutubeModule::class)])
            internal abstract fun provideYoutubeFragment(): YoutubeFragment
}