package com.stavro_xhardha.fcbarcelonashqip

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody

import org.greenrobot.eventbus.EventBus

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ExpandedNewsFragment : Fragment() {

    private var newsBody: TextView? = null
    private var newsImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_expanded_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsBody = view.findViewById(R.id.news_body)
        newsImage = view.findViewById(R.id.imageViewCollapsing)
        if (Brain.INSTANCE.isNetworkAvailable(activity)) {
            Picasso.get()
                    .load(Brain.INSTANCE.getNEWS_IMAGE_URL() + Brain.getImageEndpoint())
                    .into(newsImage)
            GetExpandedNewsTask().execute(Brain.INSTANCE.getNEWS_BODY() + Brain.getNewsId())
        } else {
            EventBus.getDefault().post(CheckNetworkEvent())
        }
    }

    internal inner class GetExpandedNewsTask : AsyncTask<String, String, String>() {
        private var newsBodyResponce: NewsBody? = null

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg strings: String): String? {
            try {
                val client = OkHttpClient()
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                val request = Request.Builder()
                        .url(strings[0])
                        .build()
                var mInputStream: InputStream? = null
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    mInputStream = response.body()!!.byteStream()
                }
                if (mInputStream != null) {
                    val reader = InputStreamReader(mInputStream)
                    val responseType = object : TypeToken<NewsBody>() {

                    }.type

                    newsBodyResponce = gson.fromJson<NewsBody>(reader, responseType)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            if (newsBodyResponce != null) {
                val newsBodyResponseString = newsBodyResponce!!.body
                newsBody!!.text = newsBodyResponseString
            } else {
                Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance(): ExpandedNewsFragment {
            return ExpandedNewsFragment()
        }
    }

}
