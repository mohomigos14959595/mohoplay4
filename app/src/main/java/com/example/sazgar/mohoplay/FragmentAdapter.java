package com.example.sazgar.mohoplay;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sazgar.mohoplay.Fragments.ClassicFragment;
import com.example.sazgar.mohoplay.Fragments.ExtraActivity;
import com.example.sazgar.mohoplay.Fragments.HiphopFragment;
import com.example.sazgar.mohoplay.Fragments.PlaylistFragment;
import com.example.sazgar.mohoplay.Fragments.RockFragment;

/**
 *
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ExtraActivity();
            case 1:
                return new ClassicFragment();
            case 2:
                return new HiphopFragment();
            case 3:
                return new RockFragment();
            case 4:
                return new PlaylistFragment();


        }

        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
