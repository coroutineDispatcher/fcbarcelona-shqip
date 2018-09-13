package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.YouTubeResponse
import com.stavro_xhardha.fcbarcelonashqip.network.AppApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class YoutubeInteractor @Inject internal constructor(apiHelper: AppApiHelper) : BaseInteractor(apiHelper = apiHelper),
        YoutubeMVPInteractor {
}