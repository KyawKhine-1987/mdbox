package com.witts.mdbox.activity;

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
import com.witts.mdbox.model.Menu;
import com.witts.mdbox.model.MenuContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListActivity extends BasedActivity {
    @BindView(R.id.ivback)
    ImageView ivback;

    @BindView(R.id.rvChooseRestaurant)
    RecyclerView rvChooseRestaurant;

    RestaurantListAdapter restaurantListAdapter;
    private Animation animScale;
    List<MenuContent> menuContentList = new ArrayList<>();
    MenuContent menuContent;
    List<Menu> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuContent = new MenuContent();
        menuContent.setMenuTitle("Salad");
        menuContentList.add(menuContent);

        menuContent = new MenuContent();
        menuContent.setMenuTitle("Sandwich");
        menuContentList.add(menuContent);

        menuContent = new MenuContent();
        menuContent.setMenuTitle("Pizza");
        menuContentList.add(menuContent);

        menuContent = new MenuContent();
        menuContent.setMenuTitle("Cupcake");
        menuContentList.add(menuContent);

        menuContent = new MenuContent();
        menuContent.setMenuTitle("Noodle Noodle Noodle Noodle");
        menuContentList.add(menuContent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_resturant_list);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvChooseRestaurant.setLayoutManager(linearLayoutManager);
        rvChooseRestaurant.setHasFixedSize(true);
        RecyclerViewMargin decoration = new RecyclerViewMargin(25, menuContentList.size());
        rvChooseRestaurant.addItemDecoration(decoration);
        restaurantListAdapter = new RestaurantListAdapter(getApplicationContext(), menuContentList);
        rvChooseRestaurant.setAdapter(restaurantListAdapter);

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
