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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentTypeFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_TYPE = "param1";
    private String mType;

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

    @BindView(R.id.tvEntertain_Name)
    TextView tvEntertainName;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.tvOther_Info)
    TextView tvOtherInfo;

    @BindView(R.id.tvRental1)
    TextView tvRental1;

    @BindView(R.id.tvRental2)
    TextView tvRental2;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    List<String> imageUrlList = new ArrayList<>();

    public EntertainmentTypeFragment() {
        // Required empty public constructor
    }

    public static EntertainmentTypeFragment newInstance(String type) {
        EntertainmentTypeFragment fragment = new EntertainmentTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_TYPE);
        }

        imageUrlList.add("www.google.com");
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

        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), imageUrlList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchooseentertainmenttype.setLayoutManager(layoutManager);
        rvchooseentertainmenttype.setHasFixedSize(true);
        rvchooseentertainmenttype.setAdapter(commonTypeChoiceAdapter);
        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
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
