package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.TextView

import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails

import org.greenrobot.eventbus.EventBus

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

class ScheduledMatchAdapter(private var detailsList: ArrayList<MatchDetails>?) : RecyclerView.Adapter<ScheduledMatchAdapter.MatchResultViewHolder>() {
    private var mExpandedPosition = -1
    internal var homeResultsArrayList = ArrayList<String>()
    internal var awayResultArrayList = ArrayList<String>()


    internal inner class MatchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row: LinearLayout
        var homeTeam: TextView
        var awayTeam: TextView
        var homeResult: TextView
        var awayResult: TextView
        var dateAndTime: TextView
        var hiddenRow: LinearLayout

        init {
            row = itemView.findViewById(R.id.match_container)
            homeResult = itemView.findViewById(R.id.home_result)
            homeTeam = itemView.findViewById(R.id.team_a)
            awayTeam = itemView.findViewById(R.id.team_b)
            awayResult = itemView.findViewById(R.id.away_result)
            dateAndTime = itemView.findViewById(R.id.time)
            hiddenRow = itemView.findViewById(R.id.lyt_expand)

            val mContex = itemView.context
        }
    }

    fun setItemList(list: ArrayList<MatchDetails>) {
        this.detailsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledMatchAdapter.MatchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_result, parent, false)
        return MatchResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduledMatchAdapter.MatchResultViewHolder, position: Int) {
        val details = detailsList!![position]
        val date = details.date.substring(0, 10)
        val time = details.date.substring(11, 16)

        holder.dateAndTime.text = "$date    $time"
        holder.homeTeam.text = details.homeTeamNanme
        holder.awayTeam.text = details.awayTeamName
        holder.homeResult.text = details.result!!.goalsHometeam.toString()
        holder.awayResult.text = details.result!!.goalsAwayTeam.toString()

        setFadeAnimation(holder.row)


        if (details.result!!.goalsAwayTeam == null || details.result!!.goalsHometeam == null) {
            holder.homeResult.text = "?"
            holder.awayResult.text = "?"
        } else {
            holder.row.visibility = View.GONE
        }

        val isExpanded = position == mExpandedPosition
        holder.hiddenRow.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded


        holder.row.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()
        }

        if (countNumberOfScheduledMatches(details) == 0) {
            EventBus.getDefault().post(ConfirmEmptyMatchScheduleSetEvent(true))
        }
    }

    private fun countNumberOfScheduledMatches(details: MatchDetails): Int {
        var count = 0
        for (i in detailsList!!.indices) {
            if (details.result!!.goalsAwayTeam == null || details.result!!.goalsHometeam == null) {
                count++
            }
        }
        return count
    }

    override fun getItemCount(): Int {
        return if (detailsList != null && detailsList!!.size != 0) {
            detailsList!!.size
        } else 0
    }

    fun add(details: MatchDetails) {
        detailsList!!.add(details)
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(1.0f, 10.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }
}
