package com.stavro_xhardha.fcbarcelonashqip.ui.home.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor.HomeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.home.view.HomeMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> {
}