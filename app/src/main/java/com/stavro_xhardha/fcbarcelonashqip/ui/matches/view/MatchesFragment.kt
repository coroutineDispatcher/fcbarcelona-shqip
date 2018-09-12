package com.stavro_xhardha.fcbarcelonashqip.ui.matches.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.adapters.MatchesAdapter
import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchesItem
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor.MatchesMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.matches.presenter.MatchesPresenter
import kotlinx.android.synthetic.main.fragment_scheduled_matches.*
import java.util.ArrayList
import javax.inject.Inject

class MatchesFragment : BaseFragment(), MatchesMVPView {

    companion object {
        internal val TAG = "MatchesFragment"

        fun newInstance(): MatchesFragment = MatchesFragment()
    }

    @Inject
    lateinit var matchesPresenter: MatchesPresenter<MatchesMVPView, MatchesMVPInteractor>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var matchesAdapter: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        matchesPresenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_scheduled_matches, container, false)
    }

    override fun setUp() {
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        schaduled_match_rv.layoutManager = linearLayoutManager
        schaduled_match_rv.itemAnimator = DefaultItemAnimator()
        schaduled_match_rv.adapter = matchesAdapter
        matchesPresenter.onViewPrepared()
    }

    override fun onMatchResponse(matches: MatchResponse) {
        matchesAdapter.setItemList(matches.matches as ArrayList<MatchesItem>)
    }
}
