package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.HotelDetailButtonAdapter;
import com.witts.mdbox.fragments.HotelRoomDetailFragment;
import com.witts.mdbox.fragments.HotelRoomTypeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomDetailActivity extends BasedActivity {
    @BindView(R.id.tlhotelDetail)
    TabLayout tlhotelDetail;
    @BindView(R.id.vphotelDetail)
    ViewPager vphotelDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_hotel_room_detail);
        ButterKnife.bind(this);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vphotelDetail.setAdapter(pagerAdapter);
        vphotelDetail.clearOnPageChangeListeners();
        vphotelDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlhotelDetail));
        tlhotelDetail.setTabsFromPagerAdapter(pagerAdapter);
        tlhotelDetail.post(new Runnable() {
            @Override
            public void run() {
                tlhotelDetail.setupWithViewPager(vphotelDetail);
            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[] { "BedRoom", "Living Room", "Bathroom" ,"Balcony"};
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            HotelRoomTypeFragment fragment;
            switch (position) {
                case 0:
                    fragment = HotelRoomTypeFragment.newInstance("BedRoom");
                    return fragment;
                case 1:
                    fragment = HotelRoomTypeFragment.newInstance("Living Room");
                    return fragment;
                case 2:
                    fragment = HotelRoomTypeFragment.newInstance("Bathroom");
                    return fragment;
                case 3:
                    fragment = HotelRoomTypeFragment.newInstance("Balcony");
                    return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
