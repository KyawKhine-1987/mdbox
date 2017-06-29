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
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Entertainment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentTypeFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_TYPE = "param1";
    private static final String ARG_TITLE = "param2";
    private String title;

    @BindView(R.id.rvchooseentertainmenttype)
    RecyclerView rvchooseentertainmenttype;

    @BindView(R.id.imguparrow)
    ImageView imguparrow;

    @BindView(R.id.imgdownarrow)
    ImageView imgdownarrow;

    @BindView(R.id.llinfocontainer)
    LinearLayout llinfocontainer;

    @BindView(R.id.svinfocontainer)
    ScrollView svinfocontainer;

    @BindView(R.id.tvLabel)
    TextView tvLabel;

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    Entertainment entertainment = new Entertainment();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    public EntertainmentTypeFragment() {
        // Required empty public constructor
    }

    public static EntertainmentTypeFragment newInstance(Entertainment type,String title) {
        EntertainmentTypeFragment fragment = new EntertainmentTypeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TYPE, type);
        args.putString(ARG_TITLE,title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entertainment = new Entertainment();
        if (getArguments() != null) {
            entertainment = getArguments().getParcelable(ARG_TYPE);
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_entertainment_type, container, false);
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

        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), entertainment.getImagePaths());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchooseentertainmenttype.setLayoutManager(layoutManager);
        rvchooseentertainmenttype.setHasFixedSize(true);
        rvchooseentertainmenttype.setAdapter(commonTypeChoiceAdapter);
        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                String imageapi = Constant.IMAGE_UPLOAD_URL+entertainment.getImagePaths().get(position)+"/?accessToken="+accessToken+"&date="+date+"&" +
                        "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
                Glide.with(getContext())
                        .load(imageapi)
                        .placeholder(R.drawable.spinner_of_dots)
                        .error(R.drawable.error_icon)
                        .into(ivDetailImageContainer);
                }
        });

        String imageapi = Constant.IMAGE_UPLOAD_URL+entertainment.getImagePaths().get(0)+"/?accessToken="+accessToken+"&date="+date+"&" +
                "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
        Glide.with(getContext())
                .load(imageapi)
                .placeholder(R.drawable.spinner_of_dots)
                .error(R.drawable.error_icon)
                .into(ivDetailImageContainer);

        StringBuilder entertainmentString=new StringBuilder();
        if(entertainment.getGroupList().size()>0)
        for (int i=0;i<entertainment.getGroupList().size();i++) {
            entertainmentString.append(entertainment.getGroupList().get(i).getAttributeList().get(0).getDisplayName() + " :" +
                    entertainment.getGroupList().get(i).getAttributeList().get(0).getValue() + " " + entertainment.getGroupList().get(i).getAttributeList().get(0).getUnit()+"\n");
        }
        tvLabel.setText(title);
        tvDetail.setText(entertainmentString);

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
