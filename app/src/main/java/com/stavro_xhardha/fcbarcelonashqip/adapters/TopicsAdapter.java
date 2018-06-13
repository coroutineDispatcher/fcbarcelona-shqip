package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain;
import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent;
import com.stavro_xhardha.fcbarcelonashqip.model.Topic;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {

    private Context mContext;
    private ArrayList<Topic> latestNewsList;

    public class TopicsViewHolder extends RecyclerView.ViewHolder {
        CardView row;
        TextView title;
        TextView date;
        TextView description;
        ImageView photoBase;
        TextView viewsNumber;
        TextView author;

        public TopicsViewHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.news_row);
            title = itemView.findViewById(R.id.news_title);
            date = itemView.findViewById(R.id.news_date);
            description = itemView.findViewById(R.id.news_description);
            photoBase = itemView.findViewById(R.id.news_photo_base);
            viewsNumber = itemView.findViewById(R.id.news_views);
            author = itemView.findViewById(R.id.news_writer);

            mContext = itemView.getContext();
        }
    }

    public TopicsAdapter(ArrayList<Topic> list) {
        this.latestNewsList = list;
    }

    public void setItemsList(ArrayList<Topic> list) {
        this.latestNewsList = list;
        notifyDataSetChanged();
    }

    public void add(Topic topic) {
        latestNewsList.add(topic);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_news, parent, false);
        return new TopicsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsViewHolder holder, int position) {
        final Topic newsTopic = latestNewsList.get(position);

        String imageUrl = Brain.NEWS_IMAGE_URL;
        String imageEndpoint = newsTopic.getPhotoBase();

        holder.author.setText(newsTopic.getAuthor());
        holder.viewsNumber.setText(newsTopic.getViews());
        holder.description.setText(newsTopic.getDescription());
        holder.date.setText(newsTopic.getDate());
        holder.title.setText(newsTopic.getTitle());


        if (newsTopic.getViews().isEmpty()) {
            holder.viewsNumber.setText("0");
        }

        if (!newsTopic.getPhotoBase().isEmpty() || newsTopic.getPhotoBase() != null) {
            Picasso.get()
                    .load(imageUrl + imageEndpoint)
                    .into(holder.photoBase);
        }

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ExpandNewsSelectedTopicEvent(newsTopic));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (latestNewsList != null && latestNewsList.size() != 0) {
            return latestNewsList.size();
        }
        return 0;
    }
}