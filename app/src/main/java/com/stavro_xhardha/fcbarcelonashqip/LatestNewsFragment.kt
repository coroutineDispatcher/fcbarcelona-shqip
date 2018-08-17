package com.stavro_xhardha.fcbarcelonashqip

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

import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.adapters.TopicsAdapter
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent
import com.stavro_xhardha.fcbarcelonashqip.model.Topic

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

class LatestNewsFragment : Fragment() {
    private var newsRefresher: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var topcsArrayList = ArrayList<Topic>()
    private var topicsAdapter: TopicsAdapter? = null
    internal val manageer: RecyclerView.LayoutManager = LinearLayoutManager(activity)
    private var expandedNewsFragment: ExpandedNewsFragment? = null
    private var materialDialog: MaterialDialog? = null
    private val newsBodyResponseString: String? = null

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
        Brain.setNewsId(topic.id.toString())
        Brain.setImageEndpoint(topic.photoBase.toString())
        closeCustomFragment()
        val wrapInScrollView = true
        materialDialog = MaterialDialog.Builder(activity!!)
                .title(topic.title!!)
                .customView(R.layout.custom_dialog_news, wrapInScrollView)
                .cancelable(false)
                .positiveText(R.string.close_dialog)
                .onPositive { dialog, which ->
                    dialog.dismiss()
                    closeCustomFragment()
                }
                .show()
        val view = materialDialog!!.customView
        if (view != null) {
            expandedNewsFragment = activity!!.supportFragmentManager.findFragmentById(R.id.custom_dialog_fragment) as ExpandedNewsFragment
        }
    }

    private fun closeCustomFragment() {
        if (expandedNewsFragment != null) {
            activity!!.supportFragmentManager.beginTransaction().remove(expandedNewsFragment).commit()
        }
    }

    private fun updateViews(topic: Topic) {
        if (activity != null) {
            if (Brain.isNetworkAvailable(activity)) {
                UpdateViewTask().execute(Brain.VIEWS_URL + topic.id.toString())
            } else {
                EventBus.getDefault().post(CheckNetworkEvent())
            }
        }
    }

    private fun getNewsData() {
        if (activity != null) {
            if (Brain.isNetworkAvailable(activity)) {
                GetNewsContentTask().execute(Brain.NEWS_URL)
            } else {
                EventBus.getDefault().post(CheckNetworkEvent())
            }
        }
    }

    internal inner class GetNewsContentTask : AsyncTask<String, String, String>() {

        private var topicResponse: ArrayList<Topic>? = null
        var code: Int = 0

        override fun onPreExecute() {
            super.onPreExecute()
            if (activity != null) {
                newsRefresher!!.isRefreshing = true
            }
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
                    val responseType = object : TypeToken<ArrayList<Topic>>() {

                    }.type

                    topicResponse = gson.fromJson<ArrayList<Topic>>(reader, responseType)
                }
                code = response.code()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
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
                    topicsAdapter!!.setItemsList(topcsArrayList)
                }
            } else {
                Toast.makeText(activity, "Nuk mund të merren të dhënat", Toast.LENGTH_LONG).show()
            }
        }
    }

    internal inner class UpdateViewTask : AsyncTask<String, String, String>() {
        var responseFlag = 0

        override fun doInBackground(vararg strings: String): String? {
            val client = OkHttpClient()

            val request = Request.Builder()
                    .url(strings[0])
                    .build()

            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    responseFlag = 1
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            if (responseFlag != 1) {
                Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance(): LatestNewsFragment {
            return LatestNewsFragment()
        }
    }
}