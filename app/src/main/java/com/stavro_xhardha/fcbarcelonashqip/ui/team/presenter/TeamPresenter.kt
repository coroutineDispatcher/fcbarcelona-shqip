package com.stavro_xhardha.fcbarcelonashqip.ui.team.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor.TeamMvpInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.team.view.TeamMvpView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TeamPresenter<V : TeamMvpView, I : TeamMvpInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable)
        , TeamMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            it?.getPlayersList()?.compose(schedulerProvider.ioToMainObservableScheduler())
                    ?.subscribe {
                        getView()?.addPlayersToList(it.squad)
                    }
        }
    }
}