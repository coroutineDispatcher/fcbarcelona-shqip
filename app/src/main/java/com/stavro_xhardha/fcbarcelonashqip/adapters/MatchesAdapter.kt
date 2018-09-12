package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.TextView

import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent
import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchesItem

import org.greenrobot.eventbus.EventBus

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

class MatchesAdapter(private var detailsList: ArrayList<MatchesItem>?) : RecyclerView.Adapter<MatchesAdapter.MatchResultViewHolder>() {
    private var mExpandedPosition = -1


    inner class MatchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    fun setItemList(list: ArrayList<MatchesItem>) {
        this.detailsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesAdapter.MatchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_result, parent, false)
        return MatchResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchesAdapter.MatchResultViewHolder, position: Int) {
        val details = detailsList!![position]
        val date = details.utcDate?.substring(0, 10)
        val time = details.utcDate?.substring(11, 16)

        holder.dateAndTime.text = "$date    $time"
        holder.homeTeam.text = details.homeTeam!!.name
        holder.awayTeam.text = details.awayTeam!!.name
        holder.homeResult.text = details.score?.fullTime!!.homeTeam.toString()
        holder.awayResult.text = details.score?.fullTime!!.awayTeam.toString()

        setFadeAnimation(holder.row)


        if (details.score?.fullTime!!.awayTeam == null || details.score?.fullTime!!.homeTeam == null) {
            holder.homeResult.text = "?"
            holder.awayResult.text = "?"
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

    private fun countNumberOfScheduledMatches(details: MatchesItem): Int {
        var count = 0
        for (i in detailsList!!.indices) {
            if (details.score?.fullTime!!.awayTeam == null || details.score?.fullTime!!.homeTeam == null) {
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

    fun add(details: MatchesItem) {
        detailsList!!.add(details)
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(1.0f, 10.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }
}