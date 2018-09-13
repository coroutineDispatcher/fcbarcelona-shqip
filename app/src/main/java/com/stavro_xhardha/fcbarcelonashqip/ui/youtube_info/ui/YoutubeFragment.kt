package com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.adapters.YoutubeVideoAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.interactor.YoutubeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.youtube_info.presenter.YoutubePresenter
import kotlinx.android.synthetic.main.fragment_latest_news.*
import javax.inject.Inject

class YoutubeFragment : Fragment(), YoutubeMVPView {

    companion object {

        internal val TAG = "YoutubeFragment"

        fun newInstance() = YoutubeFragment()
    }

    @Inject
    lateinit var presenter: YoutubePresenter<YoutubeMVPView, YoutubeMVPInteractor>

    @Inject
    lateinit var youtubeAdapter: YoutubeVideoAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        latest_news_rv.layoutManager = linearLayoutManager
        latest_news_rv.itemAnimator = DefaultItemAnimator()
        latest_news_rv.adapter = youtubeAdapter
        presenter.onViewPrepared()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

}