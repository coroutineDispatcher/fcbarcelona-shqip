package com.stavro_xhardha.fcbarcelonashqip

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stavro_xhardha.fcbarcelonashqip.adapters.ScheduledMatchAdapter
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ConfirmEmptyMatchScheduleSetEvent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.model.MatchDetails
import com.stavro_xhardha.fcbarcelonashqip.model.ResultResponse

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

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.MATCH_URL
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.SCHEDULED_MATCHES_FRAGMENT_TAG
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class ScheduledMatchesFragment : Fragment() {
    private var details: ArrayList<MatchDetails>? = ArrayList()
    private var adapter: ScheduledMatchAdapter? = null
    private var rvMatchDetails: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var emptyListContentContainer: LinearLayout? = null

    private var isScheduleSetEmpty: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().post(CheckNetworkEvent())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scheduled_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents(view)
        afterInitialize()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.SCHEDULED_MATCHES_FRAGMENT_TAG))
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ConfirmEmptyMatchScheduleSetEvent?) {
        if (event != null) {
            isScheduleSetEmpty = event.isSetEmpty
            if (isScheduleSetEmpty) {
                emptyListContentContainer!!.visibility = View.VISIBLE
            }
        }
    }

    private fun initializeComponents(view: View) {
        rvMatchDetails = view.findViewById(R.id.schaduled_match_rv)
        swipeRefreshLayout = view.findViewById(R.id.schedule_refresh)
        emptyListContentContainer = view.findViewById(R.id.empty_list_content)
    }

    private fun afterInitialize() {
        prepareRecyclerView()
        getApiData()
        swipeRefreshLayout!!.setOnRefreshListener { getApiData() }
    }

    private fun prepareRecyclerView() {
        val manageer = LinearLayoutManager(activity)
        rvMatchDetails!!.layoutManager = manageer
        rvMatchDetails!!.isFocusable = false
        adapter = ScheduledMatchAdapter(details)
        rvMatchDetails!!.adapter = adapter
    }

    private fun getApiData() {
        if (context != null) {
            if (Brain.isNetworkAvailable(context as HomeActivity)) {
                makeFixturesApiCall(Brain.MATCH_URL)
                //     GetMatchesAsync().execute(Brain.MATCH_URL)
            } else {
                EventBus.getDefault().post(CheckNetworkEvent())
            }
        }
    }

    private fun makeFixturesApiCall(matchURL: String) {
        doAsync {
            var mApiResponse: ResultResponse<*>? = null
            var code: Int = 0

            if (activity != null) {
                swipeRefreshLayout!!.isRefreshing = true
            }

            val client = OkHttpClient()
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()

            val request = Request.Builder()
                    .addHeader(Brain.HEADER_RESPONSE_CONTROL, Brain.RESPONSE_HEADER_VALUE)
                    .addHeader(Brain.AUTHORIZATION, Brain.TOKEN)
                    .url(matchURL)
                    .build()

            var mInputStream: InputStream? = null
            var response: Response? = null
            try {
                response = client.newCall(request).execute()

            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (response != null) {
                if (response.isSuccessful) {
                    mInputStream = response.body()!!.byteStream()
                }

                if (mInputStream != null) {
                    val reader = InputStreamReader(mInputStream)
                    val responseType = object : TypeToken<ResultResponse<MatchDetails>>() {

                    }.type
                    mApiResponse = gson.fromJson<ResultResponse<MatchDetails>>(reader, responseType)
                    code = response.code()
                }
            }
            uiThread {
                swipeRefreshLayout!!.isRefreshing = false
                if (mApiResponse != null) {
                    if (code == 200) {
                        details = mApiResponse!!.fixtures as ArrayList<MatchDetails>
                        if (details!!.size == 0) {
                            emptyListContentContainer!!.visibility = View.VISIBLE
                        } else {
                            adapter!!.setItemList(details!!)
                        }
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

        fun newInstance(): ScheduledMatchesFragment {
            return ScheduledMatchesFragment()
        }
    }
}
