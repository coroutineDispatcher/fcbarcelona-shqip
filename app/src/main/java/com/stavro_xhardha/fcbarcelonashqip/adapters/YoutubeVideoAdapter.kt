package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.model.YoutubeVideo

import java.util.ArrayList

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_WATCH_URL

class YoutubeVideoAdapter(private var youtubeVideoArrayList: ArrayList<YoutubeVideo>?) : RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder>() {
    private var mContext: Context? = null

    inner class YoutubeVideoViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var row: CardView
        internal var youtubeThumbnail: ImageView
        internal var title: TextView
        internal var share: Button
        internal var watchVideo: Button

        init {
            row = itemView.findViewById(R.id.single_item_row)
            youtubeThumbnail = itemView.findViewById(R.id.youtube_video_id)
            title = itemView.findViewById(R.id.youtube_title)
            share = itemView.findViewById(R.id.single_item_share)
            watchVideo = itemView.findViewById(R.id.single_item_watch)

            mContext = itemView.context
        }
    }

    fun setItemList(list: ArrayList<YoutubeVideo>) {
        this.youtubeVideoArrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_video, parent, false)
        return YoutubeVideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: YoutubeVideoViewHolder, position: Int) {
        val videoObject = youtubeVideoArrayList!![position]
        val videoThumbnail = videoObject.snippet!!.thumbnails!!.mediumVideoObject!!.url

        val baseSearchUrl = YOUTUBE_WATCH_URL

        holder.title.text = videoObject.snippet!!.title

        setFadeAnimation(holder.row)

        Picasso.get()
                .load(videoThumbnail)
                .into(holder.youtubeThumbnail)

        holder.watchVideo.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(baseSearchUrl + videoObject.id!!.videoId!!))
            mContext!!.startActivity(browserIntent)
        }

        holder.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = mContext!!.resources.getString(R.string.share_type)
            val shareBody = holder.title.text.toString()
            val shareSubject = Uri.parse(baseSearchUrl + videoObject.id!!.videoId!!).toString()
            intent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            intent.putExtra(Intent.EXTRA_TEXT, shareSubject)
            mContext!!.startActivity(Intent.createChooser(intent, mContext!!.resources.getString(R.string.action_share)))
        }

    }

    override fun getItemCount(): Int {
        return if (youtubeVideoArrayList != null && youtubeVideoArrayList!!.size != 0) {
            youtubeVideoArrayList!!.size
        } else 0
    }

    fun add(video: YoutubeVideo) {
        youtubeVideoArrayList!!.add(video)
        notifyDataSetChanged()
    }

    fun addArray(arrayList: ArrayList<YoutubeVideo>) {
        youtubeVideoArrayList!!.addAll(arrayList)
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(1.0f, 10.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

}
