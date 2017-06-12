package com.witts.mdbox.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.witts.mdbox.R;
import com.witts.mdbox.fragments.EntertainmentTypeFragment;
import com.witts.mdbox.model.EntertainmentType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentActivity extends BasedActivity {
    @BindView(R.id.tlentertainmentDetail)
    TabLayout tlentertainmentDetail;

    @BindView(R.id.vpentertainmentDetail)
    ViewPager vpentertainmentDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    EntertainmentType entertainmentType;
    List<EntertainmentType> entertainmentTypeList = new ArrayList<>();
    List<String> imageUrlList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entertainmentType = new EntertainmentType();
        entertainmentType.setEntertainmentType("Swimming pool");
        entertainmentTypeList.add(entertainmentType);

        entertainmentType = new EntertainmentType();
        entertainmentType.setEntertainmentType("Gym");
        entertainmentTypeList.add(entertainmentType);

        entertainmentType = new EntertainmentType();
        entertainmentType.setEntertainmentType("Smoking room");
        entertainmentTypeList.add(entertainmentType);

        entertainmentType = new EntertainmentType();
        entertainmentType.setEntertainmentType("Message");
        entertainmentTypeList.add(entertainmentType);

        entertainmentType = new EntertainmentType();
        entertainmentType.setEntertainmentType("Other");
        entertainmentTypeList.add(entertainmentType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_entertainment);
        ButterKnife.bind(this);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpentertainmentDetail.setAdapter(pagerAdapter);
        vpentertainmentDetail.clearOnPageChangeListeners();
        vpentertainmentDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlentertainmentDetail));
        tlentertainmentDetail.setTabsFromPagerAdapter(pagerAdapter);
        tlentertainmentDetail.post(new Runnable() {
            @Override
            public void run() {
                tlentertainmentDetail.setupWithViewPager(vpentertainmentDetail);
            }
        });


        ivback.setOnClickListener(new View .OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ivback.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivback.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.focus_background));
                } else
                {
                    ivback.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.backarrow));
                }
            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            EntertainmentTypeFragment fragment;

            for(int i=0;i<entertainmentTypeList.size();i++) {
                if (position == i)
                {
                    fragment = EntertainmentTypeFragment.newInstance(entertainmentTypeList.get(i).getEntertainmentType());
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return entertainmentTypeList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return entertainmentTypeList.get(position).getEntertainmentType();
        }
    }
}
