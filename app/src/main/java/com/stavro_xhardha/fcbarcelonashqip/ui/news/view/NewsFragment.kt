package com.stavro_xhardha.fcbarcelonashqip.ui.news.view


import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.adapters.TopicsAdapter
import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor.NewsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news.presenter.NewsPresenter
import kotlinx.android.synthetic.main.fragment_latest_news.*
import java.util.ArrayList
import javax.inject.Inject

class NewsFragment : BaseFragment(), NewsMVPView {

    companion object {
        internal var TAG = "LatestNews"

        fun newInstance(): NewsFragment =
                NewsFragment()
    }

    @Inject
    internal lateinit var topicsAdapter: TopicsAdapter
    @Inject
    internal lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: NewsPresenter<NewsMVPView , NewsMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_latest_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun setUp() {
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        latest_news_rv.layoutManager = linearLayoutManager
        latest_news_rv.itemAnimator = DefaultItemAnimator()
        latest_news_rv.adapter = topicsAdapter
        presenter.onViewPrepared()
    }

    override fun displayTopicsList(topicsList: List<Topic>) {
        topicsAdapter.setItemsList(topicsList as ArrayList<Topic>)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}