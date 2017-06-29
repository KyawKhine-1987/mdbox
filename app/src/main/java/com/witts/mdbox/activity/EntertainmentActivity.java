package com.witts.mdbox.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.common.StatusBar;
import com.witts.mdbox.fragments.EntertainmentTypeFragment;
import com.witts.mdbox.model.Entertainment;
import com.witts.mdbox.model.EntertainmentAttribute;
import com.witts.mdbox.model.EntertainmentGroup;
import com.witts.mdbox.model.EntertainmentListWrapper;
import com.witts.mdbox.model.EntertainmentType;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.EntertainmentService;
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

public class EntertainmentActivity extends BasedActivity {
    @BindView(R.id.tlentertainmentDetail)
    TabLayout tlentertainmentDetail;

    @BindView(R.id.vpentertainmentDetail)
    ViewPager vpentertainmentDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    @BindView(R.id.tvDateTime)
    TextView tvDateTime;

    @BindView(R.id.ivWiFi)
    ImageView ivWiFi;

    Thread t;

    EntertainmentType entertainmentType;
    List<EntertainmentType> entertainmentTypeList = new ArrayList<>();
    List<EntertainmentAttribute> entertainmentAttributeList = new ArrayList<>();
    List<String> imageUrlList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    public List<String> categoryTabTitle = new ArrayList<>();
    public List<Entertainment> entertainmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
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

        callWebService();

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

    private void callWebService() {
        showProgressDialog();
        final EntertainmentService entertainmentService = ServiceFactory.getService(EntertainmentService.class);
        entertainmentService.roomTypeList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<EntertainmentListWrapper>>() {
                    @Override
                    public void onCompleted() {
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
                        dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showAlert(PropertiesUtil.getProperty("e0001",LanguageActivity.languageCode+"_message.properties",getApplicationContext()));
                    }

                    @Override
                    public void onNext(final WebServiceResult<EntertainmentListWrapper> entertainmentListWrapperWebServiceResult) {
                        if (entertainmentListWrapperWebServiceResult != null) {
                            categoryTabTitle = new ArrayList<>();
                            entertainmentList = new ArrayList<Entertainment>();
                            if(entertainmentListWrapperWebServiceResult.getResponse().getEntertainmentList().size()>0)
                            for(int i =0;i<entertainmentListWrapperWebServiceResult.getResponse().getEntertainmentList().size();i++) {
                                Entertainment entertainment = new Entertainment();
                                entertainment = entertainmentListWrapperWebServiceResult.getResponse().getEntertainmentList().get(i);
                                entertainmentAttributeList = new ArrayList<EntertainmentAttribute>();
                                if(entertainment.getGroupList().size()>0)
                                for(int j=0;j<entertainment.getGroupList().size();j++)
                                {
                                    if(entertainment.getGroupList().get(j).getAttributeList().get(0).getAttributeName().equalsIgnoreCase("title"))
                                        categoryTabTitle.add(entertainment.getGroupList().get(j).getAttributeList().get(0).getDisplayName());
                                    else
                                        entertainmentAttributeList.add(entertainment.getGroupList().get(j).getAttributeList().get(0));
                                }
                                Entertainment e = new Entertainment();
                                EntertainmentGroup entertainmentGroup = new EntertainmentGroup();
                                entertainmentGroup.setAttributeList(entertainmentAttributeList);
                                List<EntertainmentGroup> entertainmentGroupList = new ArrayList<EntertainmentGroup>();
                                entertainmentGroupList.add(entertainmentGroup);
                                e.setImagePaths(entertainment.getImagePaths());
                                e.setGroupList(entertainmentGroupList);
                                entertainmentList.add(e);
                            }
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

            for(int i=0;i<categoryTabTitle.size();i++) {
                if (position == i)
                {
                    fragment = EntertainmentTypeFragment.newInstance(entertainmentList.get(i),categoryTabTitle.get(i));
                    return fragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return categoryTabTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categoryTabTitle.get(position);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(t.isAlive())
            t.interrupt();
    }
}
