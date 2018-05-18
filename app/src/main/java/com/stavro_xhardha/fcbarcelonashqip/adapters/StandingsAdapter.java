package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.model.Standing;

import java.util.ArrayList;

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {
    private Context mContex;
    private ArrayList<Standing> standingsList;

    class StandingsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout row;
        TextView rank;
        TextView teamName;
        TextView match;
        TextView goal;
        TextView points;
        TextView time;

        public StandingsViewHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.single_item_row);
            rank = itemView.findViewById(R.id.single_item_number);
            teamName = itemView.findViewById(R.id.single_item_name);
            match = itemView.findViewById(R.id.single_item_match);
            goal = itemView.findViewById(R.id.single_item_goals);
            points = itemView.findViewById(R.id.single_item_points);

            mContex = itemView.getContext();
        }
    }

    public StandingsAdapter(ArrayList<Standing> list){
        this.standingsList = list;
    }

    public void setItemsList(ArrayList<Standing> list){
        this.standingsList = list;
        notifyDataSetChanged();
    }

    public void add(Standing standing){
        standingsList.add(standing);
        notifyDataSetChanged();
    }

    @Override
    public StandingsAdapter.StandingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_standings, parent, false);
        return new StandingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StandingsAdapter.StandingsViewHolder holder, int position) {
        final Standing standing = standingsList.get(position);

        holder.points.setText(String.valueOf(standing.getPoints()));
        holder.rank.setText(String.valueOf(standing.getPosition()));
        holder.teamName.setText(standing.getTeamName());
        holder.match.setText(String.valueOf(standing.getMatchesPlayed()));
        holder.goal.setText(String.valueOf(standing.getGoalDifference()));

    }

    @Override
    public int getItemCount() {
        if (standingsList != null && standingsList.size() != 0) {
            return standingsList.size();
        }
        return 0;
    }
}
