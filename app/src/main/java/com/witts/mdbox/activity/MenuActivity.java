package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.MenuAdapter;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.MenuContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuActivity extends BasedActivity implements ItemClickListener<MenuContent> {

    @BindView(R.id.rvchoosemenu)
    RecyclerView rvChooseMenu;
    @BindView(R.id.tvreceptionnews)
    TextView tvreception;
    MenuAdapter menuAdapter;
    private Animation animScale;
    public static final String TAG = "MenuActivity";
    List<MenuContent> menuContentList = new ArrayList<>();
    MenuContent menuContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Hotel Bedroom");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Swimming Pool");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Food and Drink");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Hotel Service");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Noodle");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Room Service");
        menuContentList.add(menuContent);
        menuContent = new MenuContent();
        menuContent.setMenuTitle("Map");
        menuContentList.add(menuContent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        tvreception.setSelected(true);
        tvreception.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textmove));
        menuAdapter = new MenuAdapter(getApplicationContext(), menuContentList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        rvChooseMenu.setLayoutManager(gridLayoutManager);
        rvChooseMenu.setHasFixedSize(true);
        rvChooseMenu.setAdapter(menuAdapter);
        menuAdapter.setItemClickListener(MenuActivity.this);


//        List<Hotel> lstHotel = new ArrayList<>();
//
//        Hotel hotel = new Hotel();
//        hotel.setName("Hotel 1");
//        hotel.setSomehting();
//
//        lstHotel.add(hotel);

    }

    @Override
    public void onItemClick(int position, MenuContent data) {
        if(position==0) {
            animScale = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            goToBedroomDetail();
        }
        if(position==5) {
            animScale = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            goToContactReception();
        }
    }

    private void goToBedroomDetail() {
        Intent intent = new Intent(MenuActivity.this, HotelRoomDetailActivity.class);
        startActivity(intent);
    }

    private void goToContactReception() {
        Intent intent = new Intent(MenuActivity.this, ContactReceptionActivity.class);
        startActivity(intent);
    }
}
