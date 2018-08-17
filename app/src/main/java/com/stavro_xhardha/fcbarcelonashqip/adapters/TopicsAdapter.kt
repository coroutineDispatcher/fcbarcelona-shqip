package com.stavro_xhardha.fcbarcelonashqip.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import com.stavro_xhardha.fcbarcelonashqip.R
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent
import com.stavro_xhardha.fcbarcelonashqip.model.Topic

import org.greenrobot.eventbus.EventBus

import java.util.ArrayList

class TopicsAdapter(private var latestNewsList: ArrayList<Topic>?) : RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder>() {

    private var mContext: Context? = null

    inner class TopicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var row: CardView
        internal var title: TextView
        internal var date: TextView
        internal var description: TextView
        internal var photoBase: ImageView
        internal var viewsNumber: TextView
        internal var author: TextView

        init {
            row = itemView.findViewById(R.id.news_row)
            title = itemView.findViewById(R.id.news_title)
            date = itemView.findViewById(R.id.news_date)
            description = itemView.findViewById(R.id.news_description)
            photoBase = itemView.findViewById(R.id.news_photo_base)
            viewsNumber = itemView.findViewById(R.id.news_views)
            author = itemView.findViewById(R.id.news_writer)

            mContext = itemView.context
        }
    }

    fun setItemsList(list: ArrayList<Topic>) {
        this.latestNewsList = list
        notifyDataSetChanged()
    }

    fun add(topic: Topic) {
        latestNewsList!!.add(topic)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_news, parent, false)
        return TopicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        val newsTopic = latestNewsList!![position]

        val imageUrl = Brain.NEWS_IMAGE_URL
        val imageEndpoint = newsTopic.photoBase

        holder.author.text = newsTopic.author
        holder.viewsNumber.text = newsTopic.views
        holder.description.text = newsTopic.description
        holder.date.text = newsTopic.date
        holder.title.text = newsTopic.title


        if (newsTopic.views!!.isEmpty()) {
            holder.viewsNumber.text = "0"
        }

        if (!newsTopic.photoBase!!.isEmpty() || newsTopic.photoBase != null) {
            Picasso.get()
                    .load(imageUrl + imageEndpoint!!)
                    .into(holder.photoBase)
        }

        holder.row.setOnClickListener { EventBus.getDefault().post(ExpandNewsSelectedTopicEvent(newsTopic)) }
    }

    override fun getItemCount(): Int {
        return if (latestNewsList != null && latestNewsList!!.size != 0) {
            latestNewsList!!.size
        } else 0
    }
}