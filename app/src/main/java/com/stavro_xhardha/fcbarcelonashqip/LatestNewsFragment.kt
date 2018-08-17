package com.stavro_xhardha.fcbarcelonashqip

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.adapters.TopicsAdapter
import com.stavro_xhardha.fcbarcelonashqip.events.*
import com.stavro_xhardha.fcbarcelonashqip.model.Topic

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayList

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LatestNewsFragment : Fragment() {
    private var newsRefresher: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var topcsArrayList = ArrayList<Topic>()
    private var topicsAdapter: TopicsAdapter? = null
    internal val manageer: RecyclerView.LayoutManager = LinearLayoutManager(activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.barca_news)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.latest_news_id)
        newsRefresher = view.findViewById(R.id.news_refresh)
        recyclerView!!.layoutManager = manageer
        topicsAdapter = TopicsAdapter(topcsArrayList)
        recyclerView!!.adapter = topicsAdapter
        getNewsData()
        newsRefresher!!.setOnRefreshListener { getNewsData() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_latest_news, container, false)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.LATEST_NEWS_FRAGMENT_TAG))
        EventBus.getDefault().post(CheckNetworkEvent())
        getNewsData()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: RefreshDataEvent?) {
        if (event != null) {
            getNewsData()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ExpandNewsSelectedTopicEvent?) {
        if (event != null && activity != null) {
            updateViews(event.topic)
            getNewsData()
            openExpandedNews(event.topic)
        }
    }

    private fun openExpandedNews(topic: Topic) {
        Brain.newsId = topic.id.toString()
        Brain.imageEndpoint = topic.photoBase.toString()
        var expandedNewsFragment: ExpandedNewsFragment = ExpandedNewsFragment.newInstance()
        expandedNewsFragment.show(activity!!.supportFragmentManager, "")
    }

    private fun updateViews(topic: Topic) {
        if (activity != null) {
            if (Brain.isNetworkAvailable(context as HomeActivity)) {
                makeViewUpdateApiCall(Brain.VIEWS_URL + topic.id.toString())
            } else {
                EventBus.getDefault().post(CheckNetworkEvent())
            }
        }
    }

    private fun makeViewUpdateApiCall(updateViewUrl: String) {
        doAsync {
            var responseFlag = 0
            val client = OkHttpClient()

            val request = Request.Builder()
                    .url(updateViewUrl)
                    .build()

            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    responseFlag = 1
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (responseFlag != 1) {
                Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun getNewsData() {
        if (activity != null) {
            if (Brain.isNetworkAvailable(context as HomeActivity)) {
                makeNewsApiCall(Brain.NEWS_URL)
            } else {
                EventBus.getDefault().post(CheckNetworkEvent())
            }
        }
    }

    private fun makeNewsApiCall(newsUrl: String) {
        doAsync {
            var topicResponse: ArrayList<Topic>? = null
            var code = 0
            if (activity != null) {
                newsRefresher!!.isRefreshing = true
            }
            try {
                val client = OkHttpClient()
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                val request = Request.Builder()
                        .url(newsUrl)
                        .build()

                var mInputStream: InputStream? = null
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    mInputStream = response.body()!!.byteStream()
                }
                if (mInputStream != null) {
                    val reader = InputStreamReader(mInputStream)
                    val responseType = object : TypeToken<ArrayList<Topic>>() {

                    }.type

                    topicResponse = gson.fromJson<ArrayList<Topic>>(reader, responseType)
                }
                code = response.code()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (topicResponse != null) {
                newsRefresher!!.isRefreshing = false
                if (code == 200) {
                    topcsArrayList = topicResponse
                    val iterator = topcsArrayList.iterator()
                    while (iterator.hasNext()) {
                        val topic = iterator.next()
                        if (!topic.section!!.equals("Barcelona", ignoreCase = true)) {
                            iterator.remove()
                        }
                    }
                    uiThread {
                        topicsAdapter!!.setItemsList(topcsArrayList)
                    }
                }
            } else {
                Toast.makeText(activity, "Nuk mund të merren të dhënat", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance(): LatestNewsFragment {
            return LatestNewsFragment()
        }
    }
}