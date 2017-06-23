package com.witts.mdbox.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.witts.mdbox.R;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.fragments.HotelRoomTypeFragment;
import com.witts.mdbox.model.RoomAttribute;
import com.witts.mdbox.model.RoomDetail;
import com.witts.mdbox.model.RoomType;
import com.witts.mdbox.model.RoomTypeGroup;
import com.witts.mdbox.model.RoomTypeListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.RoomTypeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HotelRoomDetailActivity extends BasedActivity {
    @BindView(R.id.tlhotelDetail)
    TabLayout tlhotelDetail;

    @BindView(R.id.vphotelDetail)
    ViewPager vphotelDetail;

    @BindView(R.id.ivback)
    ImageView ivback;

    RoomType roomType;
    List<RoomType> roomTypeList = new ArrayList<>();
    List<RoomAttribute> roomAttributeList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    List<String> categoryTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

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
        final RoomTypeService roomTypeService = ServiceFactory.getService(RoomTypeService.class);
        roomTypeService.roomTypeList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<RoomTypeListWrapper>>() {
                    @Override
                    public void onCompleted() {
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

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert(e.getMessage());
                    }

                    @Override
                    public void onNext(final WebServiceResult<RoomTypeListWrapper> roomTypeListWrapperWebServiceResult) {
                        if (roomTypeListWrapperWebServiceResult != null) {
                            categoryTabTitle = new ArrayList<>();
                            roomTypeList = new ArrayList<RoomType>();
                            if(roomTypeListWrapperWebServiceResult.getResponse().getRoomTypeList().size()>0)
                                for(int i =0;i<roomTypeListWrapperWebServiceResult.getResponse().getRoomTypeList().size();i++) {
                                    RoomType roomType = new RoomType();
                                    roomType = roomTypeListWrapperWebServiceResult.getResponse().getRoomTypeList().get(i);
                                    roomAttributeList = new ArrayList<RoomAttribute>();
                                    if(roomType.getGroupList().size()>0)
                                        for(int j=0;j<roomType.getGroupList().size();j++)
                                        {
                                            if(roomType.getGroupList().get(j).getKey().equalsIgnoreCase("title"))
                                                categoryTabTitle.add(roomType.getGroupList().get(j).getAttributeList().get(0).getDisplayName());
                                            else
                                                roomAttributeList.add(roomType.getGroupList().get(j).getAttributeList().get(0));
                                        }
                                    RoomType r = new RoomType();
                                    RoomTypeGroup roomTypeGroup = new RoomTypeGroup();
                                    roomTypeGroup.setAttributeList(roomAttributeList);
                                    List<RoomTypeGroup> roomTypeGroupList = new ArrayList<RoomTypeGroup>();
                                    roomTypeGroupList.add(roomTypeGroup);
                                    r.setImages(roomType.getImages());
                                    r.setGroupList(roomTypeGroupList);
                                    roomTypeList.add(r);
                                }
                        }
                        dismissProgressDialog();
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
                    fragment = HotelRoomTypeFragment.newInstance(roomTypeList.get(i),categoryTabTitle.get(i));
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
}
