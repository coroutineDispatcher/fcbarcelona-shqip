package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.model.Player;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    private ArrayList<Player> playerList;

     class PlayersViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView playerName;
        TextView position;
        TextView shirtNumber;
        TextView birthday;
        TextView nationality;
        TextView contract;
        CircleImageView playerImage;

        PlayersViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.player_container);
            playerName = itemView.findViewById(R.id.player_name);
            position = itemView.findViewById(R.id.player_position);
            shirtNumber = itemView.findViewById(R.id.shirt_number);
            birthday = itemView.findViewById(R.id.birthday);
            nationality = itemView.findViewById(R.id.nationality);
            contract = itemView.findViewById(R.id.contract);
            playerImage = itemView.findViewById(R.id.player_image);

            Context mContex = itemView.getContext();
        }
    }

    public PlayersAdapter(ArrayList<Player> list) {
        this.playerList = list;
    }

    public void setItemList(ArrayList<Player> list) {
        this.playerList = list;
        notifyDataSetChanged();
    }

    public void add(Player player) {
        playerList.add(player);
        notifyDataSetChanged();
    }

    public void removeTopElement() {
        playerList.remove(0);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_player, parent, false);
        return new PlayersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        final Player player = playerList.get(position);

        holder.playerName.setText(player.getPlayerName());
        holder.contract.setText(player.getContractUntil());
        holder.nationality.setText(player.getNationality());
        holder.birthday.setText(player.getDateOfBirth());
        holder.shirtNumber.setText(String.valueOf(player.getShirtNumber()));
        holder.position.setText(player.getPosition());

        addPlayersToImage(player, holder);


    }

    private void addPlayersToImage(Player player, PlayersViewHolder holder) {
        if (player.getPlayerName().equalsIgnoreCase(Brain.MESSI)) {
            Picasso.get()
                    .load(Brain.URL_MESSI)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.INIESTA)) {
            Picasso.get()
                    .load(Brain.URL_INIESTA)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.CUTINHO)) {
            Picasso.get()
                    .load(Brain.URL_CUTINHO)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.DEMBELE)) {
            Picasso.get()
                    .load(Brain.URL_DEMBELE)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.TER_STEGEN)) {
            Picasso.get()
                    .load(Brain.URL_TER_STEGEN)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.CILLESEN)) {
            Picasso.get()
                    .load(Brain.URL_CILLESEN)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.PIQUE)) {
            Picasso.get()
                    .load(Brain.URL_PIQUE)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.UMTITI)) {
            Picasso.get()
                    .load(Brain.URL_UMTITI)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.JORDI_ALBA)) {
            Picasso.get()
                    .load(Brain.URL_JORDI)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.DIGNE)) {
            Picasso.get()
                    .load(Brain.URL_DIGNE)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.ROBERTO)) {
            Picasso.get()
                    .load(Brain.URL_ROBERTO)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.VIDAL)) {
            Picasso.get()
                    .load(Brain.URL_VIDAL)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.BUSQUETS)) {
            Picasso.get()
                    .load(Brain.URL_BUSQUETS)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.RAKITIC)) {
            Picasso.get()
                    .load(Brain.URL_RAKITIC)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.ANDRE_GOMES)) {
            Picasso.get()
                    .load(Brain.URL_GOMEZ)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.DENIS_SUAREZ)) {
            Picasso.get()
                    .load(Brain.URL_DENIS_SUAREZ)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.LUIS_SUAREZ)) {
            Picasso.get()
                    .load(Brain.URL_SUAREZ)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.ALCACER)) {
            Picasso.get()
                    .load(Brain.URL_ALCACER)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.SAMPER)) {
            Picasso.get()
                    .load(Brain.URL_SAMPER)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.VERMALEN)) {
            Picasso.get()
                    .load(Brain.URL_VERMAELEN)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.SEMEDO)) {
            Picasso.get()
                    .load(Brain.URL_SEMEDO)
                    .into(holder.playerImage);
        } else if (player.getPlayerName().equalsIgnoreCase(Brain.MINA)) {
            Picasso.get()
                    .load(Brain.URL_MINA)
                    .into(holder.playerImage);
        }
    }

    @Override
    public int getItemCount() {
        if (playerList != null && playerList.size() != 0) {
            return playerList.size();
        }
        return 0;
    }
}
