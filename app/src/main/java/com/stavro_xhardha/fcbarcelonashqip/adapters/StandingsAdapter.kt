package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.model.TableItem

import java.util.ArrayList

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class StandingsAdapter(private var standingsList: ArrayList<TableItem>?) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {
    private var mContext: Context? = null

    inner class StandingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row: LinearLayout = itemView.findViewById(R.id.single_item_row)
        var rank: TextView = itemView.findViewById(R.id.single_item_number)
        var teamName: TextView = itemView.findViewById(R.id.single_item_name)
        var match: TextView = itemView.findViewById(R.id.single_item_match)
        var goal: TextView = itemView.findViewById(R.id.single_item_goals)
        var points: TextView = itemView.findViewById(R.id.single_item_points)

        init {
            mContext = itemView.context
        }
    }

    fun setItemsList(list: ArrayList<TableItem>) {
        this.standingsList = list
        notifyDataSetChanged()
    }

    fun add(standing: TableItem) {
        standingsList!!.add(standing)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsAdapter.StandingsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_standings, parent, false)
        return StandingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StandingsAdapter.StandingsViewHolder, position: Int) {
        val tableItem = standingsList!![position]

        holder.points.text = tableItem.points.toString()
        holder.rank.text = tableItem.position.toString()
        holder.teamName.text = tableItem.team?.name
        holder.match.text = tableItem.playedGames.toString()
        holder.goal.text = tableItem.goalDifference.toString()

    }

    override fun getItemCount(): Int {
        return if (standingsList != null && standingsList!!.size != 0) {
            standingsList!!.size
        } else 0
    }
}
