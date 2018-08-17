package com.stavro_xhardha.fcbarcelonashqip

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.common.reflect.TypeToken
import com.google.gson.Gson

import com.google.gson.GsonBuilder
import com.stavro_xhardha.fcbarcelonashqip.adapters.YoutubeVideoAdapter
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent
import com.stavro_xhardha.fcbarcelonashqip.model.YouTubeResponse
import com.stavro_xhardha.fcbarcelonashqip.model.YoutubeVideo

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type
import java.util.ArrayList

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.WHATS_NEW_ON_CLUB_FRAGMENT_TAG
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_API_KEY
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_BASE_URL
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_DETAILS
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_PAGE_TOKEN
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.YOUTUBE_URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class TeamYoutubeChannelFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private val videosList = ArrayList<YoutubeVideo>()
    private var videoAdapter: YoutubeVideoAdapter? = null
    private var nextPage: String? = ""
    internal val manageer: RecyclerView.LayoutManager = LinearLayoutManager(activity)
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.barca_channel)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents(view)
        afterInitialize()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.WHATS_NEW_ON_CLUB_FRAGMENT_TAG))
        EventBus.getDefault().post(CheckNetworkEvent())
        getApiData()
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
            getApiData()
        }
    }

    private fun initializeComponents(view: View) {
        recyclerView = view.findViewById(R.id.youtube_rv)
        swipeRefreshLayout = view.findViewById(R.id.news_refresher)
    }

    private fun afterInitialize() {
        prepareRecyclerview()
        getApiData()
        swipeRefreshLayout!!.setOnRefreshListener { getApiData() }
    }

    private fun prepareRecyclerview() {
        recyclerView!!.layoutManager = manageer
        videoAdapter = YoutubeVideoAdapter(videosList)
        recyclerView!!.adapter = videoAdapter
    }

    private fun getApiData() {
        if (Brain.isNetworkAvailable(context as HomeActivity)) {
            makeYoutubeApiCall(Brain.YOUTUBE_URL + Brain.YOUTUBE_API_KEY)
            // GetYoutubeContentTask().execute(Brain.YOUTUBE_URL + Brain.YOUTUBE_API_KEY)
        } else {
            EventBus.getDefault().post(CheckNetworkEvent())
        }
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView!!.canScrollVertically(1)) {
                    if (Brain.isNetworkAvailable(context as HomeActivity)) {
                        makeYoutubeApiCall(Brain.YOUTUBE_BASE_URL + Brain.YOUTUBE_PAGE_TOKEN + nextPage + "&" + Brain.YOUTUBE_DETAILS + Brain.YOUTUBE_API_KEY)
                        //      GetYoutubeContentTask().execute(Brain.YOUTUBE_BASE_URL + Brain.YOUTUBE_PAGE_TOKEN + nextPage + "&" + Brain.YOUTUBE_DETAILS + Brain.YOUTUBE_API_KEY)
                    } else {
                        EventBus.getDefault().post(CheckNetworkEvent())
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun makeYoutubeApiCall(youtubeParameter: String) {
        doAsync {
            var mYoutubeResponse: YouTubeResponse<*>? = null
            var code: Int = 0
            if (activity != null) {
                swipeRefreshLayout!!.isRefreshing = true
            }
            try {

                val client = OkHttpClient()
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                val request = Request.Builder()
                        .url(youtubeParameter)
                        .build()

                var mInputStream: InputStream? = null
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    mInputStream = response.body()!!.byteStream()
                }
                if (mInputStream != null) {
                    val reader = InputStreamReader(mInputStream)
                    val responseType = object : TypeToken<YouTubeResponse<YoutubeVideo>>() {

                    }.type

                    mYoutubeResponse = gson.fromJson<YouTubeResponse<YoutubeVideo>>(reader, responseType)
                }
                code = response.code()
            } catch (e: IOException) {
                e.printStackTrace()
                mYoutubeResponse = null
            }
            uiThread {
                swipeRefreshLayout!!.isRefreshing = false
                if (mYoutubeResponse != null) {
                    if (code == 200) {
                        videoAdapter!!.addArray((mYoutubeResponse!!.items as ArrayList<YoutubeVideo>?)!!)
                        nextPage = mYoutubeResponse!!.nextPageToken
                    } else {
                        Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {


        fun newInstance(): TeamYoutubeChannelFragment {
            return TeamYoutubeChannelFragment()
        }
    }
}
