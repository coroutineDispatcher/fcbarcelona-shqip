package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 24/04/2018.
 */

public class HistoryMatchAdapter extends RecyclerView.Adapter<HistoryMatchAdapter.HistoryMatchViewholder> {
    private ArrayList<MatchDetails> historyList;

    public class HistoryMatchViewholder extends RecyclerView.ViewHolder {
        LinearLayout row;
        TextView homeTeam;
        TextView awayTeam;
        TextView homeResult;
        TextView awayResult;
        TextView dateAndTime;
        LinearLayout hiddenRow;

        HistoryMatchViewholder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.match_container);
            homeResult = itemView.findViewById(R.id.home_result);
            homeTeam = itemView.findViewById(R.id.team_a);
            awayTeam = itemView.findViewById(R.id.team_b);
            awayResult = itemView.findViewById(R.id.away_result);
            dateAndTime = itemView.findViewById(R.id.time);
            hiddenRow = itemView.findViewById(R.id.lyt_expand);


            Context mContex = itemView.getContext();

        }
    }

    public HistoryMatchAdapter(ArrayList<MatchDetails> list) {
        this.historyList = list;
    }

    public void setItemList(ArrayList<MatchDetails> list) {
        this.historyList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryMatchAdapter.HistoryMatchViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_result, parent, false);
        return new HistoryMatchViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryMatchAdapter.HistoryMatchViewholder holder, int position) {
        final MatchDetails details = historyList.get(position);

        String date = details.getDate().substring(0, 10);
        String time = details.getDate().substring(11, 16);

        holder.dateAndTime.setText(String.valueOf(date + "    " + time));
        holder.homeTeam.setText(details.getHomeTeamNanme());
        holder.awayTeam.setText(details.getAwayTeamName());
        holder.homeResult.setText(String.valueOf(details.getResult().getGoalsHometeam()));
        holder.awayResult.setText(String.valueOf(details.getResult().getGoalsAwayTeam()));

        setFadeAnimation(holder.row);

        if (details.getResult().getGoalsAwayTeam() == null || details.getResult().getGoalsHometeam() == null) {
            holder.row.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (historyList != null && historyList.size() != 0) {
            return historyList.size();
        }
        return 0;
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }
}
