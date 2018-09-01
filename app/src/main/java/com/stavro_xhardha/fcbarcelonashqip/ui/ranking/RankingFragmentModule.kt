package com.stavro_xhardha.fcbarcelonashqip.ui.ranking

import android.app.Application
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.adapters.StandingsAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor.RankingInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor.RankingMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.presenter.RankingMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.presenter.RankingsPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view.RankingFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view.RankingMVPView
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class RankingFragmentModule {

    @Provides
    internal fun provideNewsTopicInteractor(interactor: RankingInteractor): RankingMVPInteractor = interactor

    @Provides
    internal fun provideNewsDetailsPresenter(presenter: RankingsPresenter<RankingMVPView, RankingMVPInteractor>)
            : RankingMVPPresenter<RankingMVPView, RankingMVPInteractor> = presenter

    @Provides
    internal fun provideRankingsAdapter(): StandingsAdapter = StandingsAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: RankingFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}