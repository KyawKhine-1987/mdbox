package com.witts.mdbox.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.witts.mdbox.R;
import com.witts.mdbox.fragments.HotelRoomTypeFragment;
import com.witts.mdbox.model.RoomType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomDetailActivity extends BasedActivity {
    @BindView(R.id.tlhotelDetail)
    TabLayout tlhotelDetail;

    @BindView(R.id.vphotelDetail)
    ViewPager vphotelDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    RoomType roomType;
    List<RoomType> roomTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomType = new RoomType();
        roomType.setRoomType("BedRoom");
        roomTypeList.add(roomType);

        roomType = new RoomType();
        roomType.setRoomType("Living Room");
        roomTypeList.add(roomType);

        roomType = new RoomType();
        roomType.setRoomType("Bathroom");
        roomTypeList.add(roomType);

        roomType = new RoomType();
        roomType.setRoomType("Balcony");
        roomTypeList.add(roomType);
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

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            HotelRoomTypeFragment fragment;

            for(int i=0;i<roomTypeList.size();i++) {
                if (position == i)
                {
                    fragment = HotelRoomTypeFragment.newInstance(roomTypeList.get(i).getRoomType());
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return roomTypeList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return roomTypeList.get(position).getRoomType();
        }
    }
}
