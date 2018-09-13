package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info

import android.support.v7.widget.LinearLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.adapters.YoutubeVideoAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor.YoutubeInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor.YoutubeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.presenter.YoutubeMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.presenter.YoutubePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui.YoutubeFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui.YoutubeMVPView
import dagger.Module
import dagger.Provides

@Module
class YoutubeModule {

    @Provides
    internal fun provideNewsTopicInteractor(interactor: YoutubeInteractor): YoutubeMVPInteractor = interactor

    @Provides
    internal fun provideNewsDetailsPresenter(presenter: YoutubePresenter<YoutubeMVPView, YoutubeInteractor>)
            : YoutubeMVPPresenter<YoutubeMVPView, YoutubeInteractor> = presenter

    @Provides
    internal fun provideRankingsAdapter(): YoutubeVideoAdapter = YoutubeVideoAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: YoutubeFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}