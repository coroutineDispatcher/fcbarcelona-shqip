package com.stavro_xhardha.fcbarcelonashqip

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
import android.widget.Toast

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stavro_xhardha.fcbarcelonashqip.adapters.StandingsAdapter
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent
import com.stavro_xhardha.fcbarcelonashqip.model.StandingsResponse
import com.stavro_xhardha.fcbarcelonashqip.model.Standing

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

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.TABLE_FRAGMENT_TAG


class TableFragment : Fragment() {
    private var standings: ArrayList<Standing>? = ArrayList()
    private var adapter: StandingsAdapter? = null
    private var rvTable: RecyclerView? = null
    private var tableRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.title_ranking)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents(view)
        afterInitialize()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(INSTANCE.getTABLE_FRAGMENT_TAG()))
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
        rvTable = view.findViewById(R.id.table_rv)
        tableRefresh = view.findViewById(R.id.table_refresh)
    }

    private fun afterInitialize() {
        prepareRecyclerview()
        getApiData()
        tableRefresh!!.setOnRefreshListener { getApiData() }
    }

    private fun prepareRecyclerview() {
        val manageer = LinearLayoutManager(activity)
        rvTable!!.layoutManager = manageer
        adapter = StandingsAdapter(standings)
        rvTable!!.adapter = adapter
    }

    private fun getApiData() {
        if (Brain.INSTANCE.isNetworkAvailable(activity)) {
            GetRankingDataTask().execute(Brain.INSTANCE.getTABLE_URL())
        } else {
            EventBus.getDefault().post(CheckNetworkEvent())
        }
    }


    internal inner class GetRankingDataTask : AsyncTask<String, String, String>() {

        private var mStandingsResponse: StandingsResponse<*>? = null
        var code: Int = 0

        override fun onPreExecute() {
            super.onPreExecute()
            if (activity != null) {
                tableRefresh!!.isRefreshing = true
            }
        }

        override fun doInBackground(vararg strings: String): String? {
            try {

                val client = OkHttpClient()
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                val request = Request.Builder()
                        .addHeader(Brain.INSTANCE.getHEADER_RESPONSE_CONTROL(), Brain.INSTANCE.getRESPONSE_HEADER_VALUE())
                        .addHeader(Brain.INSTANCE.getAUTHORIZATION(), Brain.INSTANCE.getTOKEN())
                        .url(strings[0])
                        .build()

                var mInputStream: InputStream? = null
                val response = client.newCall(request).execute()
                if (response != null) {
                    if (response.isSuccessful) {
                        mInputStream = response.body()!!.byteStream()
                    }
                    if (mInputStream != null) {
                        val reader = InputStreamReader(mInputStream)
                        val responseType = object : TypeToken<StandingsResponse<Standing>>() {

                        }.type

                        mStandingsResponse = gson.fromJson<StandingsResponse>(reader, responseType)
                    }
                    code = response.code()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                mStandingsResponse = null
            }

            return null
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            tableRefresh!!.isRefreshing = false
            if (mStandingsResponse != null) {
                if (code == 200) {
                    standings = mStandingsResponse!!.standing
                    adapter!!.setItemsList(standings!!)
                } else {
                    Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
                }
            } else
                Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {

        fun newInstance(): TableFragment {
            return TableFragment()
        }
    }
}
