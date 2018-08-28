package com.stavro_xhardha.fcbarcelonashqip.ui.news.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor.NewsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsPresenter<V : NewsMVPView, I : NewsMVPInteractor>
@Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable),
        NewsMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            it?.getTopicsList()?.compose(schedulerProvider.ioToMainObservableScheduler())?.subscribe{ topic ->
                getView()?.displayTopicsList(topic)
            }
        }
    }
}