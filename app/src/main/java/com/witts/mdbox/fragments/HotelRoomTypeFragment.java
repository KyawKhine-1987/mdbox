package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
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
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.RoomDetail;
import com.witts.mdbox.model.RoomType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomTypeFragment extends BaseFragment implements View.OnClickListener{
    private static final String ARG_ROOMTYPE = "param1";
    private static final String ARG_ROOMTITLE = "param2";
    private RoomType roomType;
    private String title;

    @BindView(R.id.rvchooseroomtype)
    RecyclerView rvchooseroomtype;

    @BindView(R.id.imguparrow)
    ImageView imguparrow;

    @BindView(R.id.imgdownarrow)
    ImageView imgdownarrow;

    @BindView(R.id.llinfocontainer)
    LinearLayout llinfocontainer;

    @BindView(R.id.svinfocontainer)
    NestedScrollView svinfocontainer;

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.tvLabel)
    TextView tvLabel;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    public HotelRoomTypeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HotelRoomTypeFragment newInstance(RoomType roomType,String title) {
        HotelRoomTypeFragment fragment = new HotelRoomTypeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ROOMTYPE, roomType);
        args.putString(ARG_ROOMTITLE,title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomType = getArguments().getParcelable(ARG_ROOMTYPE);
            title = getArguments().getString(ARG_ROOMTITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hotel_room_type, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), roomType.getImages());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchooseroomtype.setLayoutManager(layoutManager);
        rvchooseroomtype.setHasFixedSize(true);
        rvchooseroomtype.setAdapter(commonTypeChoiceAdapter);
        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                String imageapi = Constant.IMAGE_UPLOAD_URL+roomType.getImages().get(position)+"/?accessToken="+accessToken+"&date="+date+"&" +
                        "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
                Glide.with(getContext())
                        .load(imageapi)
                        .placeholder(R.drawable.spinner_of_dots)
                        .error(R.drawable.error_icon)
                        .into(ivDetailImageContainer);
            }
        });

        String imageapi = Constant.IMAGE_UPLOAD_URL+roomType.getImages().get(0)+"/?accessToken="+accessToken+"&date="+date+"&" +
                "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
        Glide.with(getContext())
                .load(imageapi)
                .placeholder(R.drawable.spinner_of_dots)
                .error(R.drawable.error_icon)
                .into(ivDetailImageContainer);

        StringBuilder entertainmentString=new StringBuilder();
        if(roomType.getGroupList().size()>0)
            for (int i=0;i<roomType.getGroupList().size();i++) {
                entertainmentString.append(roomType.getGroupList().get(i).getAttributeList().get(0).getDisplayName() + " :" +
                        roomType.getGroupList().get(i).getAttributeList().get(0).getValue() + " " + roomType.getGroupList().get(i).getAttributeList().get(0).getUnit()+"\n");
            }
        tvLabel.setText(title);
        tvDetail.setText(entertainmentString);

        imguparrow.setOnClickListener(this);
        imgdownarrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
