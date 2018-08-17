package com.stavro_xhardha.fcbarcelonashqip

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stavro_xhardha.fcbarcelonashqip.adapters.PlayersAdapter
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.events.CheckNetworkEvent
import com.stavro_xhardha.fcbarcelonashqip.events.ControlToolbarVisibilityevent
import com.stavro_xhardha.fcbarcelonashqip.events.RefreshDataEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmenTagEvent
import com.stavro_xhardha.fcbarcelonashqip.events.SetFragmentTitleEvent
import com.stavro_xhardha.fcbarcelonashqip.model.Player
import com.stavro_xhardha.fcbarcelonashqip.model.PlayerResponse

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
import swipeable.com.layoutmanager.OnItemSwiped
import swipeable.com.layoutmanager.SwipeableLayoutManager
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper

import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.PLAYERS_URL
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain.Companion.TEAM_FRAGMENT_TAG


class TeamFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var playerArrayList: ArrayList<Player>? = ArrayList()
    private var playersAdapter: PlayersAdapter? = null
    private var teamRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            EventBus.getDefault().post(SetFragmentTitleEvent(resources.getString(R.string.title_team)))
        }
        EventBus.getDefault().post(ControlToolbarVisibilityevent(true))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeConponents(view)
        afterInitialize()

    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(SetFragmenTagEvent(Brain.TEAM_FRAGMENT_TAG))
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

    private fun initializeConponents(view: View) {
        recyclerView = view.findViewById(R.id.rv_players)
        teamRefresh = view.findViewById(R.id.team_refresh)
    }

    private fun afterInitialize() {
        prepareRecyclerView()
        getApiData()
        teamRefresh!!.setOnRefreshListener { getApiData() }
    }

    private fun getApiData() {
        if (Brain.isNetworkAvailable(activity)) {
            GetPlayersAsync().execute(Brain.PLAYERS_URL)
        } else {
            EventBus.getDefault().post(CheckNetworkEvent())
        }
    }

    private fun prepareRecyclerView() {
        val callback = SwipeableTouchHelperCallback(object : OnItemSwiped {
            override fun onItemSwiped() {
                playersAdapter!!.removeTopElement()
                EventBus.getDefault().post(CheckNetworkEvent())
            }

            override fun onItemSwipedLeft() {

            }

            override fun onItemSwipedRight() {

            }
        })

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView!!.layoutManager = SwipeableLayoutManager().setAngle(20).setAnimationDuratuion(1000).setMaxShowCount(6).setScaleGap(-10f).setTransYGap(20)
        recyclerView!!.setAdapter(playersAdapter = PlayersAdapter(playerArrayList))
    }

    internal inner class GetPlayersAsync : AsyncTask<String, String, String>() {
        private var mStandingsResponse: PlayerResponse<*>? = null
        var code: Int = 0

        override fun onPreExecute() {
            super.onPreExecute()
            if (activity != null) {
                teamRefresh!!.isRefreshing = true
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
                        val responseType = object : TypeToken<PlayerResponse<Player>>() {

                        }.type

                        mStandingsResponse = gson.fromJson<PlayerResponse>(reader, responseType)
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
            teamRefresh!!.isRefreshing = false
            if (mStandingsResponse != null) {
                if (code == 200) {
                    playerArrayList = mStandingsResponse!!.players
                    playersAdapter!!.setItemList(playerArrayList!!)
                } else {
                    Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(view!!, resources.getString(R.string.can_not_get_data), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance(): TeamFragment {
            return TeamFragment()
        }
    }
}
