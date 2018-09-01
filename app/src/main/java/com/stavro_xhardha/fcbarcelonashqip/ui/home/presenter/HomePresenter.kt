package com.stavro_xhardha.fcbarcelonashqip.ui.home.presenter

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.BasePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor.HomeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.home.view.HomeMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable)
    : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), HomeMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }


    override fun onNavNewsItemClick() = getView()?.openNewsFragment()


    override fun onNewsTopicsItemCardClick() = getView()?.openBottomSheetFragment()

    override fun updateCacheData(event: ExpandNewsSelectedTopicEvent) {
        Brain.newsId = event.topic.id.toString()
        Brain.imageEndpoint = event.topic.photoBase!!
    }

    override fun onNavTableItemClick() = getView()?.openTableFragment()

}