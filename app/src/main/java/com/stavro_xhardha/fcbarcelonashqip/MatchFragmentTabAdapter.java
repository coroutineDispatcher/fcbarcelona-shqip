package com.stavro_xhardha.fcbarcelonashqip;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by stavro_xhardha on 22/04/2018.
 */

public class MatchFragmentTabAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public MatchFragmentTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ScheduledMatchesFragment();
        } else {
            return new MatchHistoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.scheduled_match);
            case 1:
                return mContext.getString(R.string.history_match);
            default:
                return null;
        }
    }
}
