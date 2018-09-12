package com.stavro_xhardha.fcbarcelonashqip.ui.team

import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.adapters.PlayersAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor.TeamInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor.TeamMvpInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.team.presenter.TeamMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.team.presenter.TeamPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.team.view.TeamMvpView
import dagger.Module
import dagger.Provides

@Module
class TeamFragmentModule {
    @Provides
    internal fun provideTeamInteractor(teamInteractor: TeamInteractor): TeamMvpInteractor = teamInteractor

    @Provides
    internal fun provideTeamPresenter(teamPresenter: TeamPresenter<TeamMvpView, TeamMvpInteractor>):
            TeamMVPPresenter<TeamMvpView, TeamMvpInteractor> = teamPresenter

    @Provides
    internal fun provideTeamAdapter(): PlayersAdapter = PlayersAdapter(ArrayList())

    @Provides
    internal fun provideLayoutManager(): CarouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
}