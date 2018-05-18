package com.stavro_xhardha.fcbarcelonashqip.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stavro_xhardha.fcbarcelonashqip.R;
import com.stavro_xhardha.fcbarcelonashqip.model.YoutubeVideo;

import java.util.ArrayList;

import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.TAG;
import static com.stavro_xhardha.fcbarcelonashqip.brain.Brain.YOUTUBE_WATCH_URL;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    private Context mContext;
    private ArrayList<YoutubeVideo> youtubeVideoArrayList;

    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        CardView row;
        ImageView youtubeThumbnail;
        TextView title;
        Button share;
        Button watchVideo;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.single_item_row);
            youtubeThumbnail = itemView.findViewById(R.id.youtube_video_id);
            title = itemView.findViewById(R.id.youtube_title);
            share = itemView.findViewById(R.id.single_item_share);
            watchVideo = itemView.findViewById(R.id.single_item_watch);

            mContext = itemView.getContext();
        }
    }

    public YoutubeVideoAdapter(ArrayList<YoutubeVideo> list) {
        this.youtubeVideoArrayList = list;
    }

    public void setItemList(ArrayList<YoutubeVideo> list) {
        this.youtubeVideoArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_video, parent, false);
        return new YoutubeVideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final YoutubeVideoViewHolder holder, int position) {
        final YoutubeVideo videoObject = youtubeVideoArrayList.get(position);
        String videoThumbnail = videoObject.getSnippet().getThumbnails().getMediumVideoObject().getUrl();

        final String baseSearchUrl = YOUTUBE_WATCH_URL;

        holder.title.setText(videoObject.getSnippet().getTitle());

        setFadeAnimation(holder.row);

        Picasso.get()
                .load(videoThumbnail)
                .into(holder.youtubeThumbnail);

        holder.watchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseSearchUrl + videoObject.getId().getVideoId()));
                mContext.startActivity(browserIntent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(mContext.getResources().getString(R.string.share_type));
                String shareBody = holder.title.getText().toString();
                String shareSubject = Uri.parse(baseSearchUrl + videoObject.getId().getVideoId()).toString();
                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                intent.putExtra(Intent.EXTRA_TEXT, shareSubject);
                mContext.startActivity(Intent.createChooser(intent, mContext.getResources().getString(R.string.action_share)));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (youtubeVideoArrayList != null && youtubeVideoArrayList.size() != 0) {
            return youtubeVideoArrayList.size();
        }
        return 0;
    }

    public void add(YoutubeVideo video) {
        youtubeVideoArrayList.add(video);
        notifyDataSetChanged();
    }

    public void addArray(ArrayList<YoutubeVideo> arrayList) {
        youtubeVideoArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 10.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

}
