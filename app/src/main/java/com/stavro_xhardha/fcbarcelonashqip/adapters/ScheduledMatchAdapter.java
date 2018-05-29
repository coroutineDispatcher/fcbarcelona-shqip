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
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 23/04/2018.
 */

public class ScheduledMatchAdapter extends RecyclerView.Adapter<ScheduledMatchAdapter.MatchResultViewHolder> {
    private ArrayList<MatchDetails> detailsList;
    private int mExpandedPosition = -1;
    ArrayList<String> homeResultsArrayList = new ArrayList<>();
    ArrayList<String> awayResultArrayList = new ArrayList<>();


    class MatchResultViewHolder extends RecyclerView.ViewHolder {
        LinearLayout row;
        TextView homeTeam;
        TextView awayTeam;
        TextView homeResult;
        TextView awayResult;
        TextView dateAndTime;
        LinearLayout hiddenRow;

        MatchResultViewHolder(View itemView) {
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

    public ScheduledMatchAdapter(ArrayList<MatchDetails> list) {
        this.detailsList = list;
    }

    public void setItemList(ArrayList<MatchDetails> list) {
        this.detailsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduledMatchAdapter.MatchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_result, parent, false);
        return new MatchResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduledMatchAdapter.MatchResultViewHolder holder, final int position) {
        final MatchDetails details = detailsList.get(position);
        String date = details.getDate().substring(0, 10);
        String time = details.getDate().substring(11, 16);

        holder.dateAndTime.setText(String.valueOf(date + "    " + time));
        holder.homeTeam.setText(details.getHomeTeamNanme());
        holder.awayTeam.setText(details.getAwayTeamName());
        holder.homeResult.setText(String.valueOf(details.getResult().getGoalsHometeam()));
        holder.awayResult.setText(String.valueOf(details.getResult().getGoalsAwayTeam()));

        setFadeAnimation(holder.row);


        if (details.getResult().getGoalsAwayTeam() == null || details.getResult().getGoalsHometeam() == null) {
            holder.homeResult.setText("?");
            holder.awayResult.setText("?");
        } else {
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

        if (countNumberOfScheduledMatches(details) == 0){
            EventBus.getDefault().post(new ConfirmEmptyMatchScheduleSetEvent(true));
        }
    }

    private int countNumberOfScheduledMatches(MatchDetails details) {
        int count = 0;
        for(int i=0; i < detailsList.size(); i++){
            if (details.getResult().getGoalsAwayTeam() == null || details.getResult().getGoalsHometeam() == null){
                count++;
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        if (detailsList != null && detailsList.size() != 0) {
            return detailsList.size();
        }
        return 0;
    }

    public void add(MatchDetails details) {
        detailsList.add(details);
        notifyDataSetChanged();
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 10.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }
}
