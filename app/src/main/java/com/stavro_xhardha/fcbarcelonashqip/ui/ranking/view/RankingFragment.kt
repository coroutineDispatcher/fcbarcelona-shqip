package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view

import android.app.Application
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.adapters.StandingsAdapter
import com.stavro_xhardha.fcbarcelonashqip.model.ranking.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.ranking.TableItem
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.BaseFragment
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor.RankingMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.presenter.RankingsPresenter
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

class RankingFragment : BaseFragment(), RankingMVPView {

    @Inject
    lateinit var rankingsPresenter: RankingsPresenter<RankingMVPView, RankingMVPInteractor>

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var standingsAdapter: StandingsAdapter

    @Inject
    lateinit var context: Application

    companion object {
        internal var TAG = "RankingTag"

        fun newInstance() =
                RankingFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rankingsPresenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun setUp() {
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        table_rv.layoutManager = linearLayoutManager
        table_rv.itemAnimator = DefaultItemAnimator()
        table_rv.adapter = standingsAdapter
        rankingsPresenter.onViewPrepared()
    }

    override fun onRankingResponse(standing: Standing) {
        var standingList = ArrayList<TableItem>()
        standing.standingsList.forEach {
            it.table?.forEach {
                standingList.add(it)
            }
        }
        standingsAdapter.setItemsList(standingList)
    }

    override fun showConnectionError() {
        hideProgress()
        Snackbar.make(this.view!!, R.string.can_not_get_data, Snackbar.LENGTH_LONG).show()
    }

}