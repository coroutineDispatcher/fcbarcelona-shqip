package com.stavro_xhardha.fcbarcelonashqip.ui.topic_details.view

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.topic_details.interactor.NewsTopicDetailsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.topic_details.presenter.NewsDetailsTopicPresenter
import kotlinx.android.synthetic.main.fragment_expanded_news.*
import javax.inject.Inject


class NewsTopicDetailsFragment : BaseFragment(), NewsTopicDetailsMVPView {

    companion object {
        internal var TAG = "NewsTopicDetails"

        fun newInstance(): NewsTopicDetailsFragment =
                NewsTopicDetailsFragment()
    }

    @Inject
    lateinit var newsTopicDetailsPresenter: NewsDetailsTopicPresenter<NewsTopicDetailsMVPView, NewsTopicDetailsMVPInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        newsTopicDetailsPresenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_expanded_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onNewsBodyReady(newsBody: String?) {
        news_body.text = newsBody
    }

    override fun setUp() {
        newsTopicDetailsPresenter.onViewPrepared()
    }

    override fun loadImageToView(imageEndPoint: String?) {
        Picasso.get()
                .load(Brain.NEWS_IMAGE_URL + imageEndPoint!!)
                .into(imageViewCollapsing)
    }
}