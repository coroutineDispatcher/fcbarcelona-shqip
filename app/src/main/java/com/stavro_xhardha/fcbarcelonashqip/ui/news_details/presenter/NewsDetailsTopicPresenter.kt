package com.stavro_xhardha.fcbarcelonashqip.ui.news_details.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news_details.interactor.NewsTopicDetailsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news_details.view.NewsTopicDetailsMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsDetailsTopicPresenter<V : NewsTopicDetailsMVPView, I : NewsTopicDetailsMVPInteractor>
@Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable),
        NewsTopicDetailsMVPPresenter<V, I> {

    override fun onViewPrepared() {
        interactor.let {
            it?.getNewsDetails()?.compose(schedulerProvider.ioToMainObservableScheduler())
                    ?.subscribe {
                        getView()?.onNewsBodyReady(it.body)
                    }
        }
        val imageEndpoint = Brain.imageEndpoint
        getView()?.loadImageToView(imageEndpoint)
    }

}