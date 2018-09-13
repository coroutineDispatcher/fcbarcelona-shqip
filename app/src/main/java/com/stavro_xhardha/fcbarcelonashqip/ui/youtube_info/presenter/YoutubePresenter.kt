package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor.YoutubeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui.YoutubeMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class YoutubePresenter<V : YoutubeMVPView, I : YoutubeMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable)
        , YoutubeMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            //todo start this
        }
    }
}