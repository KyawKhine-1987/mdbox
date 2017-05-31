package com.witts.mdbox.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.witts.mdbox.R;
import com.witts.mdbox.fragments.ContactReceptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactReceptionActivity extends AppCompatActivity {

    @BindView(R.id.tlreceptioncontacttype)
    TabLayout tlreceptioncontacttype;
    @BindView(R.id.vprecptioncontacttype)
    ViewPager vprecptioncontacttype;
    @BindView(R.id.ivback)
    ImageView ivback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_reception);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vprecptioncontacttype.setAdapter(pagerAdapter);
        vprecptioncontacttype.clearOnPageChangeListeners();
        vprecptioncontacttype.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlreceptioncontacttype));
        tlreceptioncontacttype.setTabsFromPagerAdapter(pagerAdapter);
        tlreceptioncontacttype.post(new Runnable() {
            @Override
            public void run() {
                tlreceptioncontacttype.setupWithViewPager(vprecptioncontacttype);
            }
        });


        ivback.setOnClickListener(new View.OnClickListener() {
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
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[] { "Room Service", "Hotel Restaurant", "Other Service"};
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            ContactReceptionFragment fragment;
            switch (position) {
                case 0:
                    fragment = ContactReceptionFragment.newInstance("Room Service");
                    return fragment;
                case 1:
                    fragment = ContactReceptionFragment.newInstance("Hotel Restaurant");
                    return fragment;
                case 2:
                    fragment = ContactReceptionFragment.newInstance("Other Service");
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
