package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor.RankingMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view.RankingMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class RankingsPresenter<V : RankingMVPView, I : RankingMVPInteractor>
@Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable),
        RankingMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            compositeDisposable.add(it!!.getLaLigaRanking()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ standing ->
                        getView()?.onRankingResponse(standing)
                    }, { err ->
                        getView()?.let {
                            it.showConnectionError()
                        }
                    }))
        }
    }

}