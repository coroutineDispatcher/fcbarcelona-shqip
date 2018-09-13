package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor.YoutubeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui.YoutubeMVPView

interface YoutubeMVPPresenter<V : YoutubeMVPView, I : YoutubeMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}