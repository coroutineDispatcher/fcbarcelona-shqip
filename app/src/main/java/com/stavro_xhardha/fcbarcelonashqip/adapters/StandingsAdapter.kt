package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.model.Standing

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class StandingsAdapter(private var standingsList: ArrayList<Standing>?) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {

    inner class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row: LinearLayout
        var rank: TextView
        var teamName: TextView
        var match: TextView
        var goal: TextView
        var points: TextView
        var time: TextView? = null

        init {
            row = itemView.findViewById(R.id.single_item_row)
            rank = itemView.findViewById(R.id.single_item_number)
            teamName = itemView.findViewById(R.id.single_item_name)
            match = itemView.findViewById(R.id.single_item_match)
            goal = itemView.findViewById(R.id.single_item_goals)
            points = itemView.findViewById(R.id.single_item_points)

            val mContex = itemView.context
        }
    }

    fun setItemsList(list: ArrayList<Standing>) {
        this.standingsList = list
        notifyDataSetChanged()
    }

    fun add(standing: Standing) {
        standingsList!!.add(standing)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsAdapter.StandingsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_standings, parent, false)
        return StandingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StandingsAdapter.StandingsViewHolder, position: Int) {
        val standing = standingsList!![position]

        holder.points.text = standing.points.toString()
        holder.rank.text = standing.position.toString()
        holder.teamName.text = standing.teamName
        holder.match.text = standing.matchesPlayed.toString()
        holder.goal.text = standing.goalDifference.toString()

    }

    override fun getItemCount(): Int {
        return if (standingsList != null && standingsList!!.size != 0) {
            standingsList!!.size
        } else 0
    }
}
