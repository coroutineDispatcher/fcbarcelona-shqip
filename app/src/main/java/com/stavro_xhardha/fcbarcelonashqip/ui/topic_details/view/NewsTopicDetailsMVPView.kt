package com.stavro_xhardha.fcbarcelonashqip.ui.topic_details.view

import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface NewsTopicDetailsMVPView : MVPView{
    fun onNewsBodyReady(newsBody: String?): Unit?
    fun loadImageToView(imageEndPoint: String?)
}