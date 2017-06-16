package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.MenuAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Menu;
import com.witts.mdbox.model.MenuContent;
import com.witts.mdbox.model.MenuListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.MainMenuEnquiryService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MenuActivity extends BasedActivity implements ItemClickListener<MenuContent> {

    @BindView(R.id.rvchoosemenu)
    RecyclerView rvChooseMenu;

    @BindView(R.id.tvreceptionnews)
    TextView tvreception;

    MenuAdapter menuAdapter;
    private Animation animScale;
    List<MenuContent> menuContentList = new ArrayList<>();
    MenuContent menuContent;
    List<Menu> menuList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Hotel Bedroom");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Swimming Pool");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Food and Drink");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Hotel Service");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Noodle");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Room Service");
//        menuContentList.add(menuContent);
//
//        menuContent = new MenuContent();
//        menuContent.setMenuTitle("Map");
//        menuContentList.add(menuContent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        callWebService();

        tvreception.setSelected(true);
        tvreception.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textmove));

//        List<Hotel> lstHotel = new ArrayList<>();
//
//        Hotel hotel = new Hotel();
//        hotel.setName("Hotel 1");
//        hotel.setSomehting();
//
//        lstHotel.add(hotel);

    }

    private void callWebService() {
        showProgressDialog();
        final MainMenuEnquiryService mainMenuEnquiryService = ServiceFactory.getService(MainMenuEnquiryService.class);
        mainMenuEnquiryService.mainMenuEnquiry(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<MenuListWrapper>>() {
                    @Override
                    public void onCompleted() {
                        dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        Toast.makeText(getBaseContext(),"Fail..",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(final WebServiceResult<MenuListWrapper> menuListWrapperWebServiceResult) {
                        if (menuListWrapperWebServiceResult != null) {
                            menuList = new ArrayList<Menu>();
                            menuContentList = new ArrayList<MenuContent>();
                            menuList = menuListWrapperWebServiceResult.getResponse().getMenuList();
                            for(int i = 0;i< menuList.size();i++){
                                menuContent = new MenuContent();
                                menuContent.setMenuTitle(menuList.get(i).getMenuAttributes().get(0).getMenuName());
                                menuContent.setMenuImgUrl(menuList.get(i).getLogoPath());
                                menuContentList.add(menuContent);
                            }

                            menuAdapter = new MenuAdapter(getApplicationContext(), menuContentList);
                            rvChooseMenu.setLayoutManager(gridLayoutManager);
                            rvChooseMenu.setHasFixedSize(true);
                            rvChooseMenu.setAdapter(menuAdapter);
                            menuAdapter.setItemClickListener(MenuActivity.this);
                            Toast.makeText(getBaseContext(), "Success..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position, MenuContent data) {
        if(position==0) {
            goToBedroomDetail();
        }
        if(position==1) {
            goToEntertainmentDetail();
        }
        if(position==2) {
            goToRestaurantList();
        }
        if(position==3) {
            goToFoodDetail();
        }
        if(position==4) {
            goToPlaceDetail();
        }
        if(position==5) {
            goToContactReception();
        }
        if(position==6) {
            goToBedroomDetail();
        }
    }

    private void goToBedroomDetail() {
        Intent intent = new Intent(MenuActivity.this, HotelRoomDetailActivity.class);
        startActivity(intent);
    }

    private void goToEntertainmentDetail() {
        Intent intent = new Intent(MenuActivity.this, EntertainmentActivity.class);
        startActivity(intent);
    }

    private void goToFoodDetail() {
        Intent intent = new Intent(MenuActivity.this, FoodGuideActivity.class);
        startActivity(intent);
    }

    private void goToContactReception() {
        Intent intent = new Intent(MenuActivity.this, ContactReceptionActivity.class);
        startActivity(intent);
    }

    private void goToRestaurantList() {
        Intent intent = new Intent(MenuActivity.this, RestaurantListActivity.class);
        startActivity(intent);
    }

    private void goToPlaceDetail(){
        Intent intent = new Intent(MenuActivity.this, SouvenirGuideActivity.class);
        startActivity(intent);
    }
}
