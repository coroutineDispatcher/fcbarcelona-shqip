package com.stavro_xhardha.fcbarcelonashqip


import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

class MatchFragmentTabAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ScheduledMatchesFragment()
        } else {
            MatchHistoryFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext.getString(R.string.scheduled_match)
            1 -> return mContext.getString(R.string.history_match)
            else -> return null
        }
    }
}
