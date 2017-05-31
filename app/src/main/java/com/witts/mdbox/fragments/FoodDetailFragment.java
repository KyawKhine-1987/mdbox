package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.adapter.CommonTypeChoiceAdapter;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.FoodDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodDetailFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_FOODTYPE = "param1";
    private String mFoodType;

    @BindView(R.id.rvchoosefoodtype)
    RecyclerView rvchoosefoodtype;

    @BindView(R.id.imguparrow)
    ImageView imguparrow;

    @BindView(R.id.imgdownarrow)
    ImageView imgdownarrow;

    @BindView(R.id.llinfocontainer)
    LinearLayout llinfocontainer;

    @BindView(R.id.svinfocontainer)
    ScrollView svinfocontainer;

    @BindView(R.id.tvFood_Name)
    TextView tvFoodName;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.tvFood_Material)
    TextView tvFoodMaterial;

    @BindView(R.id.tvOther_Info)
    TextView tvOtherInfo;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;

    FoodDetail foodDetail;
    List<FoodDetail> foodDetailList = new ArrayList<>();
    List<String> imageUrlList = new ArrayList<>();
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    public FoodDetailFragment() {
        // Required empty public constructor
    }

    public static FoodDetailFragment newInstance(String foodType) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOODTYPE, foodType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodType = getArguments().getString(ARG_FOODTYPE);
        }

        foodDetail = new FoodDetail();
        foodDetail.setJapaneseName("Sukiyaki");
        foodDetail.setFoodName("Hot Pot");
        foodDetail.setFoodPrice("2190 yen(tax included)");
        foodDetail.setFoodMaterial("Beef, Japanese green onions, Shiitake, Shirataki baked tofu,\n" +
                "Haruna chrysanthemum");
        foodDetail.setOtherInfo("① Initially put meat \n" +
                "② Put vegetables \n" +
                "③ Tangle with eggs \n" +
                "④ Put the melting egg and bake it for a while");
        foodDetail.setFoodImageLargeUrl("www.image.com");
        foodDetail.setFoodImageSmallUrl("www.image.com");

        foodDetailList.add(foodDetail);

        for(int i = 0;i < foodDetailList.size();i++)
        {
            imageUrlList.add(foodDetailList.get(i).getFoodImageSmallUrl());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvFoodMaterial.setText(foodDetail.getFoodMaterial());
        tvFoodName.setText(foodDetail.getFoodName());
        tvOtherInfo.setText(foodDetail.getOtherInfo());
        tvPrice.setText(foodDetail.getFoodPrice());

        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), imageUrlList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchoosefoodtype.setLayoutManager(layoutManager);
        rvchoosefoodtype.setHasFixedSize(true);
        rvchoosefoodtype.setAdapter(commonTypeChoiceAdapter);
        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                if(foodDetailList.get(position).getFoodImageLargeUrl() != null && !foodDetailList.get(position).getFoodImageLargeUrl().equals("")) {
                    Picasso.with(getContext())
                            .load(foodDetailList.get(position).getFoodImageLargeUrl())
                            .error(R.drawable.cuisine)
                            .into(ivDetailImageContainer);
                    tvFoodMaterial.setText(foodDetail.getFoodMaterial());
                    tvFoodName.setText(foodDetail.getFoodName());
                    tvOtherInfo.setText(foodDetail.getOtherInfo());
                    tvPrice.setText(foodDetail.getFoodPrice());
                }
            }
        });

        imguparrow.setOnClickListener(this);
        imgdownarrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imguparrow:
                svinfocontainer.post(new Runnable() {
                    @Override
                    public void run() {
                        svinfocontainer.fullScroll(View.FOCUS_UP);
                    }
                });
                break;
            case R.id.imgdownarrow:
                svinfocontainer.post(new Runnable() {
                    @Override
                    public void run() {
                        svinfocontainer.fullScroll(View.FOCUS_DOWN);
                    }
                });
                break;
        }
    }
}
