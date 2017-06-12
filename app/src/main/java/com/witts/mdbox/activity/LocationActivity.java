package com.witts.mdbox.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.witts.mdbox.R;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.fragments.LocationFragment;
import com.witts.mdbox.model.Location;
import com.witts.mdbox.model.LocationAttribute;
import com.witts.mdbox.model.LocationCategory;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.LocationCategoryListService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

//public class LocationActivity extends AppCompatActivity {
public class LocationActivity extends BasedActivity {

    private static final String LOG_TAG = LocationActivity.class.getName();

    @BindView(R.id.tlLocationCategoryList)
    TabLayout tlLocationCategoryList;
    @BindView(R.id.vpLocationCategoryList)
    ViewPager vpLocationCategoryList;
    @BindView(R.id.ivBack)
    ImageView ivBack;

    private String accessToken = Constant.ACCESS_TOKEN;
    private String languageCode = "jp";
    private String date = "";
    private String time = "";
    private String timezone = "UTC";
    private String channel = "WEB";
    private String clientVersion = "1.0";
    private String versionNo = "0001";

    /*List<LocationContent> locationContentList = new ArrayList<>();
    LocationContent locationContent;*/

    List<Location> location = new ArrayList<>();

    List<LocationAttribute> locationAttributesList = new ArrayList<LocationAttribute>();

    public String[] locationCategoryListTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("kkmmss");
        date = dateFormat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourFormat.format(new Date(System.currentTimeMillis() - 21600000));

        callWebService();
    }

    private void bindingData() {
        Log.i(LOG_TAG, "TEST: bindingData() called...");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpLocationCategoryList.setAdapter(pagerAdapter);
        vpLocationCategoryList.clearOnPageChangeListeners();
        vpLocationCategoryList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlLocationCategoryList));
        tlLocationCategoryList.setTabsFromPagerAdapter(pagerAdapter);
        tlLocationCategoryList.post(new Runnable() {
            @Override
            public void run() {
                Log.i(LOG_TAG, "TEST: run() called...");

                tlLocationCategoryList.setupWithViewPager(vpLocationCategoryList);
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "TEST: onResume() called...");

        super.onResume();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "TEST: onClick() called...");

                onBackPressed();
            }
        });

        ivBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(LOG_TAG, "TEST: onFocusChange() called...");

                if (hasFocus) {
                    ivBack.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.focus_background));
                } else {
                    ivBack.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.backarrow));
                }
            }
        });
    }

    private void callWebService() {
        Log.i(LOG_TAG, "TEST: callWebService() called...");

        showProgressDialog();
        final LocationCategoryListService locationCategoryListService = ServiceFactory.getService(LocationCategoryListService.class);
        locationCategoryListService.locationCategoryList(accessToken, languageCode, date, time, timezone, channel, clientVersion, versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<LocationCategory>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(LOG_TAG, "TEST: onCompleted() called...");

                        bindingData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(LOG_TAG, "TEST: onError() called...");

                        dismissProgressDialog();
                        Toast.makeText(getBaseContext(), "Fail..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(WebServiceResult<LocationCategory> locationCategoryWebServiceResult) {
                        Log.i(LOG_TAG, "TEST: onNext() called...");

                        dismissProgressDialog();
                        Toast.makeText(getApplicationContext(),"Success..",Toast.LENGTH_SHORT).show();
                        if (locationCategoryWebServiceResult != null) {
                            location = new ArrayList<Location>();
                            location = locationCategoryWebServiceResult.getResponse().getLocationList();
                            int size = locationCategoryWebServiceResult.getResponse().getLocationList().size();
                            locationCategoryListTabTitle = new String[size];

                            locationAttributesList = new ArrayList<LocationAttribute>();
                            for (int i = 0; i < location.size(); i++) {
                                if (locationCategoryWebServiceResult.getResponse().getLocationList().get(i).getPublishInd().equals("Y")) {
                                    locationAttributesList.add(locationCategoryWebServiceResult.getResponse().getLocationList().get(i).getLocationAttribute().get(0));
                                    for (int j = 0; j < locationAttributesList.size(); j++) {
                                        if (locationAttributesList.get(j).getLanguageCode().equals(Constant.SELECTED_LANGUAGE))
                                            locationCategoryListTabTitle[i] = locationAttributesList.get(j).getName();
                                    }
                                }
                            }

                        }
                    }
                });
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private final String LOG_TAG = PagerAdapter.class.getName();

        final int PAGE_COUNT = locationCategoryListTabTitle.length;
        private String tabTitles[] = new String[]{"周辺重要施設表示", "大阪", "東京", "名古屋", "他の情報も表示"};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            Log.i(LOG_TAG, "TEST: PagerAdapter() called...");
        }

        @Override
        public Fragment getItem(int position) {
            Log.i(LOG_TAG, "TEST: getItem() called...");

            LocationFragment locationFragment;
            for (int i = 0; i < locationCategoryListTabTitle.length; i++) {
                if (i == position) {
                    locationFragment = LocationFragment.newInstance(locationAttributesList.get(i).getLocationCategoryId());
                    return locationFragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            Log.i(LOG_TAG, "TEST: getCount() called...");

            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.i(LOG_TAG, "TEST: getPageTitle() called...");

            return locationCategoryListTabTitle[position];
        }
    }
}