package com.stavro_xhardha.fcbarcelonashqip.ui.matches.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor.MatchesMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.view.MatchesMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MatchesPresenter<V : MatchesMVPView, I : MatchesMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider,
                             compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor,
        schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable)
        , MatchesMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            it?.getMatchesList()?.compose(schedulerProvider.ioToMainObservableScheduler())
                    ?.subscribe{
                       getView()?.onMatchResponse(it)
                    }
        }
    }
}