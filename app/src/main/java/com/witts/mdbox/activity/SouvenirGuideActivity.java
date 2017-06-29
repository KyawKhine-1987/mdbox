package com.witts.mdbox.activity;

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
import com.witts.mdbox.fragments.SouvenirDetailFragment;
import com.witts.mdbox.model.AttributeSC;
import com.witts.mdbox.model.FoodDetail;
import com.witts.mdbox.model.FoodType;
import com.witts.mdbox.model.SouvenirCategoryListWrapper;
import com.witts.mdbox.model.SouvenirCategorySC;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.SouvenirService;
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

public class SouvenirGuideActivity extends BasedActivity {

    @BindView(R.id.tlplaceCategory)
    TabLayout tlplaceCategory;

    @BindView(R.id.vpplaceDetail)
    ViewPager vpplaceDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    @BindView(R.id.tvDateTime)
    TextView tvDateTime;

    @BindView(R.id.ivWiFi)
    ImageView ivWiFi;

    Thread t;

    List<SouvenirCategorySC> souvenirCategorySCList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    List<String> souvenirTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_guide);
        ButterKnife.bind(this);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

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

        callWebservice();
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

    private void callWebservice() {

        final SouvenirService souvenirService = ServiceFactory.getService(SouvenirService.class);
        souvenirService.souvenirCategoryList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<SouvenirCategoryListWrapper>>() {
                    @Override
                    public void onCompleted() {
                        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
                        vpplaceDetail.setAdapter(pagerAdapter);
                        vpplaceDetail.clearOnPageChangeListeners();
                        vpplaceDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlplaceCategory));
                        tlplaceCategory.setTabsFromPagerAdapter(pagerAdapter);
                        tlplaceCategory.post(new Runnable() {
                            @Override
                            public void run() {
                                tlplaceCategory.setupWithViewPager(vpplaceDetail);
                                tlplaceCategory.shouldDelayChildPressedState();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert(PropertiesUtil.getProperty("e0001",LanguageActivity.languageCode+"_message.properties",getApplicationContext()));
                    }

                    @Override
                    public void onNext(final WebServiceResult<SouvenirCategoryListWrapper> souvenirCategoryListWrapperWebServiceResult) {

                        if (souvenirCategoryListWrapperWebServiceResult != null) {
                            souvenirCategorySCList = new ArrayList<SouvenirCategorySC>();
                            souvenirCategorySCList = souvenirCategoryListWrapperWebServiceResult.getResponse().getSouvenirCategoryList();
                            prepareDataForTab(souvenirCategorySCList);
                        }

                        dismissProgressDialog();
                    }
                });
    }

    private void prepareDataForTab(List<SouvenirCategorySC> souvenirCategorySCList) {
        for(int i=0;i<souvenirCategorySCList.size();i++) {
                for(int j = 0;j<souvenirCategorySCList.get(i).getAttributeList().size();j++) {
                    if (souvenirCategorySCList.get(i).getAttributeList().get(j).getLanguageCode().equals(LanguageActivity.languageCode))
                        souvenirTitleList.add(souvenirCategorySCList.get(i).getAttributeList().get(j).getDisplayName());
            }
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            SouvenirDetailFragment fragment;

            for(int i=0;i<souvenirCategorySCList.size();i++) {
                if (position == i)
                {
                    fragment = SouvenirDetailFragment.newInstance(souvenirCategorySCList.get(i).getSouvenirCategoryId());
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return souvenirTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return souvenirTitleList.get(position);
            }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(t.isAlive())
            t.interrupt();
    }
}
