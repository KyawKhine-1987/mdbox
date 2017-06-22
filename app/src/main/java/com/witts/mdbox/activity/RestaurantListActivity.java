package com.witts.mdbox.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.RestaurantListAdapter;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Menu;
import com.witts.mdbox.model.MenuContent;
import com.witts.mdbox.model.Restaurant;
import com.witts.mdbox.model.RestaurantWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.RestaurantService;
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

public class RestaurantListActivity extends BasedActivity implements ItemClickListener{
    @BindView(R.id.ivback)
    ImageView ivback;

    @BindView(R.id.rvChooseRestaurant)
    RecyclerView rvChooseRestaurant;

    RestaurantListAdapter restaurantListAdapter;
    private Animation animScale;
    List<MenuContent> menuContentList = new ArrayList<>();
    MenuContent menuContent;
    List<Restaurant> restaurantList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_resturant_list);
        ButterKnife.bind(this);
        callWebService();

        linearLayoutManager = new LinearLayoutManager(this);
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
                } else {
                    ivback.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.backarrow));
                }
            }
        });
    }

    private void callWebService() {
        showProgressDialog();
        final RestaurantService restaurantService = ServiceFactory.getService(RestaurantService.class);
        restaurantService.foodCategoryList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<RestaurantWrapper>>() {
                    @Override
                    public void onCompleted() {
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        rvChooseRestaurant.setLayoutManager(linearLayoutManager);
                        rvChooseRestaurant.setHasFixedSize(true);
                        RecyclerViewMargin decoration = new RecyclerViewMargin(25, menuContentList.size());
                        rvChooseRestaurant.addItemDecoration(decoration);
                        restaurantListAdapter = new RestaurantListAdapter(getApplicationContext(), menuContentList);
                        rvChooseRestaurant.setAdapter(restaurantListAdapter);
                        restaurantListAdapter.setItemClickListener(RestaurantListActivity.this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert(PropertiesUtil.getProperty("e0001",LanguageActivity.languageCode+"_message.properties",getApplicationContext()));
                    }

                    @Override
                    public void onNext(final WebServiceResult<RestaurantWrapper> restaurantWrapperWebServiceResult) {
                        if (restaurantWrapperWebServiceResult != null) {
                            restaurantList = new ArrayList<>();
                            menuContentList= new ArrayList<MenuContent>();
                            restaurantList = restaurantWrapperWebServiceResult.getResponse().getRestaurantList();
                            for(int i=0;i<restaurantList.size();i++)
                            {
                                menuContent = new MenuContent();
                                menuContent.setMenuTitle(restaurantList.get(i).getAttributeList().get(0).getName());
                                menuContent.setMenuImgUrl(restaurantList.get(i).getImagePath());
                                menuContent.setRestaurantId(restaurantList.get(i).getRestaurantId());
                                menuContentList.add(menuContent);
                            }
                        }
                        dismissProgressDialog();
                    }
                });
    }

    @Override
    public void onItemClick(int position, Object data) {
        MenuContent content = (MenuContent) data;
        Intent intent = new Intent(RestaurantListActivity.this, FoodGuideActivity.class);
        intent.putExtra("RESTAURANTID",content.getRestaurantId());
        startActivity(intent);
    }

    public class RecyclerViewMargin extends RecyclerView.ItemDecoration {
        private final int columns;
        private int margin;

        /**
         * constructor
         *
         * @param margin  desirable margin size in px between the views in the recyclerView
         * @param columns number of columns of the RecyclerView
         */
        public RecyclerViewMargin(@IntRange(from = 0) int margin, @IntRange(from = 0) int columns) {
            this.margin = margin;
            this.columns = columns;

        }

        /**
         * Set different margins for the items inside the recyclerView: no top margin for the first row
         * and no left margin for the first column.
         */
        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildLayoutPosition(view);
            //set right margin to all
            outRect.right = margin;
            //set bottom margin to all
            outRect.bottom = margin;
            //we only add top margin to the first row
            if (position < columns) {
                outRect.top = margin;
            }
            //add left margin only to the first column
            if (position % columns == 0) {
                outRect.left = margin;
            }
        }
    }
}
