package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.common.StatusBar;
import com.witts.mdbox.fragments.FoodDetailFragment;
import com.witts.mdbox.model.AttributeSC;
import com.witts.mdbox.model.FoodCategory;
import com.witts.mdbox.model.FoodCategoryWrapper;
import com.witts.mdbox.model.FoodDetail;
import com.witts.mdbox.model.FoodType;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.FoodCategoryListService;
import com.witts.mdbox.util.PropertiesUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FoodGuideActivity extends BasedActivity {

    @BindView(R.id.tlfoodCategory)
    TabLayout tlfoodCategory;

    @BindView(R.id.vpfoodDetail)
    ViewPager vpfoodDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    @BindView(R.id.tvDateTime)
    TextView tvDateTime;

    @BindView(R.id.ivWiFi)
    ImageView ivWiFi;

    Thread t;

    List<FoodCategory> foodCategoryList = new ArrayList<>();
    List<String> foodTitleList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    private int restaurantID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_guide);
        ButterKnife.bind(this);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

        Intent intent = getIntent();
        restaurantID = intent.getIntExtra("RESTAURANTID",0);

        StatusBar statusBar = new StatusBar(getApplicationContext());
        int wifiStatus = statusBar.getWiFiSignal();
        checkSignal(wifiStatus);
        String date = statusBar.getCommonDateTime(LanguageActivity.languageCode);
        tvDateTime.setText(date);
        updateTimeTextView();
    }

    private void updateTimeTextView() {
        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StatusBar statusBar = new StatusBar(getApplicationContext());
                                int wifiStatus = statusBar.getWiFiSignal();
                                checkSignal(wifiStatus);
                                String date = statusBar.getCommonDateTime(LanguageActivity.languageCode);
                                tvDateTime.setText(date);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    private void checkSignal(int i) {
        if(i == 3){
            ivWiFi.setImageResource(R.drawable.wifi_signal_full);
        }else if (i == 2){
            ivWiFi.setImageResource(R.drawable.wifi_signal_normal);
        }else{
            ivWiFi.setImageResource(R.drawable.wifi_signal_low);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        callWebService();

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

    private void callWebService() {
        showProgressDialog();
        final FoodCategoryListService foodCategoryService = ServiceFactory.getService(FoodCategoryListService.class);
        foodCategoryService.foodCategoryList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo,restaurantID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<FoodCategoryWrapper>>() {
                    @Override
                    public void onCompleted() {
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
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert(PropertiesUtil.getProperty("e0001",LanguageActivity.languageCode+"_message.properties",getApplicationContext()));
                    }

                    @Override
                    public void onNext(final WebServiceResult<FoodCategoryWrapper> foodCategoryWrapperWebServiceResult) {

                        if (foodCategoryWrapperWebServiceResult != null) {
                            foodCategoryList = new ArrayList<FoodCategory>();
                            foodCategoryList = foodCategoryWrapperWebServiceResult.getResponse().getFoodCategoryList();
                            prepareDataForTab(foodCategoryList);
                        }

                        dismissProgressDialog();
                    }
                });
    }

    private void prepareDataForTab(List<FoodCategory> foodCategoryList) {
        for(int i=0;i<foodCategoryList.size();i++) {
            for(int j = 0;j<foodCategoryList.get(i).getAttributeList().size();j++) {
                if (foodCategoryList.get(i).getAttributeList().get(j).getLanguageCode().equals(LanguageActivity.languageCode))
                    foodTitleList.add(foodCategoryList.get(i).getAttributeList().get(j).getDisplayName());
            }
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            FoodDetailFragment fragment;

            for(int i=0;i<foodCategoryList.size();i++) {
                if (position == i)
                {
                    fragment = FoodDetailFragment.newInstance(foodCategoryList.get(i).getFoodCategoryId());
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return foodTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return foodTitleList.get(position);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(t.isAlive())
            t.interrupt();
    }
}
