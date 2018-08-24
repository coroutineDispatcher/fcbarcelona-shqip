package com.stavro_xhardha.fcbarcelonashqip.ui.news

import android.support.v7.widget.LinearLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.adapters.TopicsAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor.NewsInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor.NewsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news.presenter.NewsMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news.presenter.NewsPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsMVPView
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class NewsFragmentModule {
    @Provides
    internal fun provideNewsInteractor(interactor: NewsInteractor): NewsMVPInteractor = interactor

    @Provides
    internal fun provideBlogPresenter(presenter: NewsPresenter<NewsMVPView, NewsMVPInteractor>)
            : NewsMVPPresenter<NewsMVPView, NewsMVPInteractor> = presenter

    @Provides
    internal fun provideBlogAdapter(): TopicsAdapter = TopicsAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: NewsFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}