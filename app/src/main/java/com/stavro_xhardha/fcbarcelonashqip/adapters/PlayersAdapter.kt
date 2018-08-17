package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.model.Player

import java.util.ArrayList

import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class PlayersAdapter(private var playerList: ArrayList<Player>?) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {
    private var mContex: Context? = null

    inner class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: LinearLayout
        var playerName: TextView
        var position: TextView
        var shirtNumber: TextView
        var birthday: TextView
        var nationality: TextView
        var contract: TextView
        var playerImage: CircleImageView

        init {
            container = itemView.findViewById(R.id.player_container)
            playerName = itemView.findViewById(R.id.player_name)
            position = itemView.findViewById(R.id.player_position)
            shirtNumber = itemView.findViewById(R.id.shirt_number)
            birthday = itemView.findViewById(R.id.birthday)
            nationality = itemView.findViewById(R.id.nationality)
            contract = itemView.findViewById(R.id.contract)
            playerImage = itemView.findViewById(R.id.player_image)

            mContex = itemView.context
        }
    }

    fun setItemList(list: ArrayList<Player>) {
        this.playerList = list
        notifyDataSetChanged()
    }

    fun add(player: Player) {
        playerList!!.add(player)
        notifyDataSetChanged()
    }

    fun removeTopElement() {
        playerList!!.removeAt(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_player, parent, false)
        return PlayersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = playerList!![position]

        if (player.playerName == null) {
            holder.playerName.text = ""
        } else if (player.contractUntil == null) {
            holder.contract.text = ""
        } else if (player.nationality == null) {
            holder.nationality.text = ""
        } else if (player.dateOfBirth == null) {
            holder.birthday.text = ""
        } else if (player.shirtNumber == null) {
            holder.shirtNumber.text = ""
        } else if (player.position == null) {
            holder.position.text = ""
        } else {
            holder.playerName.text = player.playerName
            holder.contract.text = player.contractUntil
            holder.nationality.text = player.nationality
            holder.birthday.text = player.dateOfBirth
            holder.shirtNumber.text = player.shirtNumber.toString()
            holder.position.text = player.position

            addPlayersToImage(player, holder)
        }
    }


    private fun addPlayersToImage(player: Player, holder: PlayersViewHolder) {
        if (player.playerName!!.equals(Brain.MESSI, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_MESSI)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.INIESTA, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_INIESTA)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.CUTINHO, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_CUTINHO)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.DEMBELE, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_DEMBELE)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.TER_STEGEN, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_TER_STEGEN)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.CILLESEN, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_CILLESEN)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.PIQUE, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_PIQUE)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.UMTITI, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_UMTITI)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.JORDI_ALBA, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_JORDI)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.DIGNE, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_DIGNE)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.ROBERTO, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_ROBERTO)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.VIDAL, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_VIDAL)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.BUSQUETS, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_BUSQUETS)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.RAKITIC, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_RAKITIC)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.ANDRE_GOMES, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_GOMEZ)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.DENIS_SUAREZ, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_DENIS_SUAREZ)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.LUIS_SUAREZ, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_SUAREZ)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.ALCACER, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_ALCACER)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.SAMPER, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_SAMPER)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.VERMALEN, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_VERMAELEN)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.SEMEDO, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_SEMEDO)
                    .into(holder.playerImage)
        } else if (player.playerName!!.equals(Brain.MINA, ignoreCase = true)) {
            Picasso.get()
                    .load(Brain.URL_MINA)
                    .into(holder.playerImage)
        } else {
            Picasso.get()
                    .load(Brain.URL_DEFAULT)
                    .into(holder.playerImage)
        }
    }

    override fun getItemCount(): Int {
        if (playerList != null && playerList!!.size != 0) {
            return playerList!!.size
        } else return 0
    }
}
