package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchHistorySetEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails

import org.greenrobot.eventbus.EventBus
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.TimeZone

/**
 * Created by stavro_xhardha on 24/04/2018.
 */

class HistoryMatchAdapter(private var historyList: ArrayList<MatchDetails>?) : RecyclerView.Adapter<HistoryMatchAdapter.HistoryMatchViewholder>() {
    private var mExpandedPosition = -1

    inner class HistoryMatchViewholder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var row: LinearLayout
        internal var homeTeam: TextView
        internal var awayTeam: TextView
        internal var homeResult: TextView
        internal var awayResult: TextView
        internal var dateAndTime: TextView
        internal var hiddenRow: LinearLayout

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
        this.historyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryMatchAdapter.HistoryMatchViewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_history_result, parent, false)
        return HistoryMatchViewholder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryMatchAdapter.HistoryMatchViewholder, position: Int) {
        val details = historyList!![position]

        val timeStamp = details.date
        val dateTime = DateTime(timeStamp, DateTimeZone.UTC)
        val newDate = dateTime.withZone(DateTimeZone.forID("Europe/Paris"))

        val date = newDate.toString().substring(0, 10)
        val time = newDate.toString().substring(11, 16)

        holder.dateAndTime.text = "$date    $time"
        holder.homeTeam.text = details.homeTeamNanme
        holder.awayTeam.text = details.awayTeamName
        holder.homeResult.text = details.result!!.goalsHometeam.toString()
        holder.awayResult.text = details.result!!.goalsAwayTeam.toString()


        if (details.result!!.goalsAwayTeam == null || details.result!!.goalsHometeam == null) {
            holder.row.visibility = View.GONE
        }

        val isExpanded = position == mExpandedPosition
        holder.hiddenRow.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded

        holder.row.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()
        }

        if (countNumberOfHistoryMatches(details) == 0) {
            EventBus.getDefault().post(ConfirmEmptyMatchHistorySetEvent(true))
        }
    }

    override fun getItemCount(): Int {
        return if (historyList != null && historyList!!.size != 0) {
            historyList!!.size
        } else 0
    }

    private fun countNumberOfHistoryMatches(details: MatchDetails): Int {
        var count = 0
        for (i in historyList!!.indices) {
            if (details.result!!.goalsAwayTeam != null || details.result!!.goalsHometeam != null) {
                count++
            }
        }
        return count
    }
}
