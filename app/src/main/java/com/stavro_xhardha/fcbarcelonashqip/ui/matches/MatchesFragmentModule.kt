package com.stavro_xhardha.fcbarcelonashqip.ui.matches

import android.support.v7.widget.LinearLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.adapters.MatchesAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor.MatchesInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor.MatchesMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.presenter.MatchesMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.presenter.MatchesPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.view.MatchesFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.view.MatchesMVPView
import dagger.Module
import dagger.Provides

@Module
class MatchesFragmentModule {

    @Provides
    internal fun provideMatchesInteractor(matchesInteractor: MatchesInteractor)
            : MatchesMVPInteractor = matchesInteractor

    @Provides
    internal fun provideMatceshPresenter(matchesPresenter: MatchesPresenter<MatchesMVPView,
            MatchesMVPInteractor>): MatchesMVPPresenter<MatchesMVPView, MatchesMVPInteractor> = matchesPresenter

    @Provides
    internal fun provideMatchesAdapter(): MatchesAdapter = MatchesAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: MatchesFragment) = LinearLayoutManager(fragment.activity)
}