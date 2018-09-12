package com.stavro_xhardha.fcbarcelonashqip.ui.team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.adapters.PlayersAdapter
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor.TeamMvpInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.team.presenter.TeamPresenter
import kotlinx.android.synthetic.main.fragment_team.*
import javax.inject.Inject
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.stavro_xhardha.fcbarcelonashqip.model.team.SquadItem


class TeamFragment : BaseFragment(), TeamMvpView {

    companion object {
        internal val TAG = "TeamFragment"

        fun newInstance(): TeamFragment = TeamFragment()
    }

    @Inject
    lateinit var teamPresenter: TeamPresenter<TeamMvpView, TeamMvpInteractor>
    @Inject
    lateinit var teamAdapter: PlayersAdapter
    @Inject
    lateinit var layoutManager: CarouselLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        teamPresenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun setUp() {
        rv_players.layoutManager = layoutManager
        rv_players.adapter = teamAdapter
        rv_players.setHasFixedSize(true)
        teamPresenter.onViewPrepared()
        rv_players.addOnScrollListener(CenterScrollListener())
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
    }

    override fun addPlayersToList(squad: ArrayList<SquadItem?>?) {
        teamAdapter.setItemList(squad as java.util.ArrayList<SquadItem>)
    }

}