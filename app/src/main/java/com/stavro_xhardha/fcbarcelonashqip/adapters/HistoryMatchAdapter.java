package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchHistorySetEvent;
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by stavro_xhardha on 24/04/2018.
 */

public class HistoryMatchAdapter extends RecyclerView.Adapter<HistoryMatchAdapter.HistoryMatchViewholder> {
    private ArrayList<MatchDetails> historyList;
    private int mExpandedPosition = -1;

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_history_result, parent, false);
        return new HistoryMatchViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryMatchAdapter.HistoryMatchViewholder holder, final int position) {
        final MatchDetails details = historyList.get(position);

        String timeStamp = details.getDate();
        DateTime dateTime = new DateTime(timeStamp, DateTimeZone.UTC);
        DateTime newDate = dateTime.withZone(DateTimeZone.forID("Europe/Paris"));

        String date = newDate.toString().substring(0, 10);
        String time = newDate.toString().substring(11, 16);

        holder.dateAndTime.setText(date + "    " + time);
        holder.homeTeam.setText(details.getHomeTeamNanme());
        holder.awayTeam.setText(details.getAwayTeamName());
        holder.homeResult.setText(String.valueOf(details.getResult().getGoalsHometeam()));
        holder.awayResult.setText(String.valueOf(details.getResult().getGoalsAwayTeam()));


        if (details.getResult().getGoalsAwayTeam() == null || details.getResult().getGoalsHometeam() == null) {
            holder.row.setVisibility(View.GONE);
        }

        final boolean isExpanded = position == mExpandedPosition;
        holder.hiddenRow.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyDataSetChanged();
            }
        });

        if (countNumberOfHistoryMatches(details) == 0) {
            EventBus.getDefault().post(new ConfirmEmptyMatchHistorySetEvent(true));
        }
    }

    @Override
    public int getItemCount() {
        if (historyList != null && historyList.size() != 0) {
            return historyList.size();
        }
        return 0;
    }

    private int countNumberOfHistoryMatches(MatchDetails details) {
        int count = 0;
        for (int i = 0; i < historyList.size(); i++) {
            if (details.getResult().getGoalsAwayTeam() != null || details.getResult().getGoalsHometeam() != null) {
                count++;
            }
        }
        return count;
    }
}
