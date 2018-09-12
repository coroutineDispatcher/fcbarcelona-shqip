package com.stavro_xhardha.fcbarcelonashqip.ui.news.view

import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface NewsMVPView : MVPView{
    fun displayTopicsList(topicsList: List<Topic>)
    fun showConnectionError()
}