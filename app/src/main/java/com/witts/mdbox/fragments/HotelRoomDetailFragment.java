package com.witts.mdbox.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.HotelDetailButtonAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomDetailFragment extends BaseFragment {
    @BindView(R.id.hotelDetail_tabLayout)
    TabLayout hotelDetail_tabLayout;
    @BindView(R.id.hotelDetail_viewpager)
    ViewPager hotelDetail_viewpager;
    LinearLayoutManager mHotelDetailLayoutManager;
    HotelDetailButtonAdapter mHotelDetailAdapter;
    public static final String TAG = "HotelRoomDetailFragment";
    public HotelRoomDetailFragment() {
        // Required empty public constructor
    }

    public static HotelRoomDetailFragment newInstance() {
        HotelRoomDetailFragment fragment = new HotelRoomDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View decorView = getActivity().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(
                new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int i) {
                        int height = decorView.getHeight();
                        Log.i(TAG, "Current height: " + height);
                    }
                });
        toggleHideyBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hotel_room__detail, container, false);
        ButterKnife.bind(this, rootView);
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), getActivity());
        hotelDetail_viewpager.setAdapter(pagerAdapter);
        hotelDetail_viewpager.clearOnPageChangeListeners();
        hotelDetail_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(hotelDetail_tabLayout));
        hotelDetail_tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        hotelDetail_tabLayout.post(new Runnable() {
            @Override
            public void run() {
                hotelDetail_tabLayout.setupWithViewPager(hotelDetail_viewpager);
            }
        });
        return rootView;
    }
    public class PagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[] { "BedRoom", "Living Room", "Bathroom" ,"Balcony"};
        private Context context;
        public PagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHotelDetailLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
//        recyclerchoosemenu.setLayoutManager(mHotelDetailLayoutManager);
//        recyclerchoosemenu.setHasFixedSize(true);
//        mHotelDetailAdapter = new HotelDetailButtonAdapter(getContext(), new HotelDetailButtonAdapter.HotelDetailReqAdapterOnClickHandler() {
//            @Override
//            public void onClick(String bookingId, HotelDetailButtonAdapter.hotelDetailButtonAdapterViewHolder vh) {
//
//            }
//        });
//        recyclerchoosemenu.setAdapter(mHotelDetailAdapter);
    }

    public void toggleHideyBar() {
        int uiOptions = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getActivity().getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
