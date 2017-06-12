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
import com.witts.mdbox.fragments.FoodDetailFragment;
import com.witts.mdbox.model.FoodDetail;
import com.witts.mdbox.model.FoodType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodGuideActivity extends BasedActivity {

    @BindView(R.id.tlfoodCategory)
    TabLayout tlfoodCategory;

    @BindView(R.id.vpfoodDetail)
    ViewPager vpfoodDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    FoodType foodType;
    FoodDetail foodDetail;
    List<FoodType> foodTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodType = new FoodType();
        foodType.setFoodType("Chinese cuisine");
        foodTypeList.add(foodType);

        foodType = new FoodType();
        foodType.setFoodType("Korean cuisine");
        foodTypeList.add(foodType);

        foodType = new FoodType();
        foodType.setFoodType("Japanese cuisine");
        foodTypeList.add(foodType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_food_guide);
        ButterKnife.bind(this);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpfoodDetail.setAdapter(pagerAdapter);
        vpfoodDetail.clearOnPageChangeListeners();
        vpfoodDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlfoodCategory));
        tlfoodCategory.setTabsFromPagerAdapter(pagerAdapter);
        tlfoodCategory.post(new Runnable() {
            @Override
            public void run() {
                tlfoodCategory.setupWithViewPager(vpfoodDetail);
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
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            FoodDetailFragment fragment;

            for(int i=0;i<foodTypeList.size();i++) {
                if (position == i)
                {
                    fragment = FoodDetailFragment.newInstance(foodTypeList.get(i).getFoodType());
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return foodTypeList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return foodTypeList.get(position).getFoodType();
        }
    }
}
