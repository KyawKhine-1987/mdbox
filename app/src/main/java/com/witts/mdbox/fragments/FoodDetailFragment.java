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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.adapter.CommonTypeChoiceAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Food;
import com.witts.mdbox.model.FoodAttribute;
import com.witts.mdbox.model.FoodCategory;
import com.witts.mdbox.model.FoodDetail;
import com.witts.mdbox.model.FoodDetailandImage;
import com.witts.mdbox.model.FoodMenu;
import com.witts.mdbox.model.FoodMenuWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.FoodCategoryListService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FoodDetailFragment extends BaseFragment implements View.OnClickListener{
    private static final String ARG_FOODTYPE = "param1";
    private int mFoodType;

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

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.tvlabel)
    TextView tvlabel;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;

    List<FoodDetail> foodDetailList = new ArrayList<>();
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    List<String> imageList = new ArrayList<>();
    List<FoodDetailandImage> foodDetailandImageList = new ArrayList<>();

    List<Food> foodList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    LinearLayoutManager layoutManager;

    public FoodDetailFragment() {
        // Required empty public constructor
    }

    public static FoodDetailFragment newInstance(int foodType) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FOODTYPE, foodType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodType = getArguments().getInt(ARG_FOODTYPE);
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

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
        callWebService();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);

        imguparrow.setOnClickListener(this);
        imgdownarrow.setOnClickListener(this);
    }

    private void callWebService() {
        showProgressDialog();
        final FoodCategoryListService foodCategoryListService = ServiceFactory.getService(FoodCategoryListService.class);
        foodCategoryListService.foodMenuList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo,mFoodType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<FoodMenuWrapper>>() {
                    @Override
                    public void onCompleted() {
                        if (foodList.size() > 0) {
                            prepareandbindData(foodList);
                            commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), imageList);
                            rvchoosefoodtype.setLayoutManager(layoutManager);
                            rvchoosefoodtype.setHasFixedSize(true);
                            rvchoosefoodtype.setAdapter(commonTypeChoiceAdapter);
                            commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemClick(int position, Object data) {
                                    if (imageList.get(position) != null && !imageList.get(position).equals("")) {
                                        String imageapi = Constant.IMAGE_UPLOAD_URL + imageList.get(position) + "/?accessToken=" + accessToken + "&date=" + date + "&" +
                                                "time=" + time + "&timezone=" + timezone + "&channel=" + channel + "&clientVersion=" + clientVersion + "&versionNo=" + versionNo + "&name=image";
                                        Glide.with(getContext())
                                                .load(imageapi)
                                                .placeholder(R.drawable.spinner_of_dots)
                                                .error(R.drawable.spinner_of_dots)
                                                .into(ivDetailImageContainer);

                                        StringBuilder foodBuilder = new StringBuilder();
                                        for (int i = 1; i < foodDetailandImageList.get(position).getFoodDetailList().size(); i++) {
                                            foodBuilder.append(foodDetailandImageList.get(position).getFoodDetailList().get(i).getDisplayName() + " :" +
                                                    foodDetailandImageList.get(position).getFoodDetailList().get(i).getValue() + " " + foodDetailandImageList.get(position).getFoodDetailList().get(i).getUnit() + "\n");
                                        }
                                        tvlabel.setText(foodDetailandImageList.get(position).getFoodDetailList().get(0).getDisplayName());
                                        tvDetail.setText(foodBuilder);
                                    }
                                }
                            });
                            String imageapi = imageList.get(0) + "/?accessToken=" + accessToken + "&date=" + date + "&" +
                                    "time=" + time + "&timezone=" + timezone + "&channel=" + channel + "&clientVersion=" + clientVersion + "&versionNo=" + versionNo + "&name=image";
                            Glide.with(getContext())
                                    .load(imageapi)
                                    .placeholder(R.drawable.spinner_of_dots)
                                    .error(R.drawable.spinner_of_dots)
                                    .into(ivDetailImageContainer);

                            StringBuilder foodBuilder = new StringBuilder();
                            for (int i = 1; i < foodDetailandImageList.get(0).getFoodDetailList().size(); i++) {
                                foodBuilder.append(foodDetailandImageList.get(0).getFoodDetailList().get(i).getDisplayName() + " :" +
                                        foodDetailandImageList.get(0).getFoodDetailList().get(i).getValue() + " " + foodDetailandImageList.get(0).getFoodDetailList().get(i).getUnit() + "\n");
                            }
                            tvlabel.setText(foodDetailandImageList.get(0).getFoodDetailList().get(0).getDisplayName());
                            tvDetail.setText(foodBuilder);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert(e.getMessage());
                    }

                    @Override
                    public void onNext(final WebServiceResult<FoodMenuWrapper> foodMenuWrapperWebServiceResult) {
                        if (foodMenuWrapperWebServiceResult != null) {
                            for(int i=0;i<foodMenuWrapperWebServiceResult.getResponse().getFoodCategoryList().size();i++) {
                                FoodMenu foodMenu = foodMenuWrapperWebServiceResult.getResponse().getFoodCategoryList().get(i);
                                foodList = new ArrayList<Food>();
                                if (foodMenu.getFoodList()!= null && foodMenu.getFoodList().size() > 0) {
                                    for (int j = 0; j < foodMenu.getFoodList().size(); j++) {
                                        Food food = new Food();
                                        food = foodMenu.getFoodList().get(j);
                                        foodList.add(food);
                                    }
                                }
                            }
                        }
                        dismissProgressDialog();
                    }
                });
    }

    private void prepareandbindData(List<Food> foodList) {
        imageList = new ArrayList<>();
        foodDetailandImageList = new ArrayList<>();
        for(int i=0;i<foodList.size();i++)
        {
            foodDetailList =new ArrayList<>();
            for(int j=0;j<foodList.get(i).getAttributeList().size();j++) {
                FoodDetail foodDetail = new FoodDetail();
                foodDetail.setDisplayName(foodList.get(i).getAttributeList().get(j).getDisplayName());
                foodDetail.setUnit(foodList.get(i).getAttributeList().get(j).getUnit());
                foodDetail.setValue(foodList.get(i).getAttributeList().get(j).getValue());
                foodDetailList.add(foodDetail);
            }
            FoodDetailandImage souvenirImageandDetail = new FoodDetailandImage();
            souvenirImageandDetail.setImageUrl(foodList.get(i).getImagePath());
            souvenirImageandDetail.setFoodDetailList(foodDetailList);
            foodDetailandImageList.add(souvenirImageandDetail);
            imageList.add(foodList.get(i).getImagePath());
        }
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
