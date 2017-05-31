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

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.adapter.CommonTypeChoiceAdapter;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.RoomDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomTypeFragment extends BaseFragment implements View.OnClickListener{
    private static final String ARG_ROOMTYPE = "param1";
    private String mRoonType;

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

    @BindView(R.id.tvBed_Fee)
    TextView tvBedFee;

    @BindView(R.id.tvBed_Width)
    TextView tvBedWidth;

    @BindView(R.id.tvOther_info)
    TextView tvOtherInfo;

    @BindView(R.id.tvRoom_Size)
    TextView tvRoomSize;

    @BindView(R.id.tvRoom_Type)
    TextView tvRoomType;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;
    RoomDetail roomDetail;
    String info;
    List<String> infoList = new ArrayList<>();
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    public HotelRoomTypeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HotelRoomTypeFragment newInstance(String roomType) {
        HotelRoomTypeFragment fragment = new HotelRoomTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROOMTYPE, roomType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRoonType = getArguments().getString(ARG_ROOMTYPE);
        }

        roomDetail = new RoomDetail();
        roomDetail.setRoomType("Double Room");
        roomDetail.setBedFee("30,000 yan");
        roomDetail.setRoomSize("11m²");
        roomDetail.setBedWidth("140 cm");

        String string = "\n* Prices vary due to season, events etc.\n\n" +
                "* The above rates include service charge and consumption tax.\n\n" +
                "※ Non smoking floor is also available.\n\n" +
                "※ If the accommodation fee is more than 10000 yen (tax not included)," +
                "guests will be charged separately according to the E regulation.\n";

        roomDetail.setOtherInfo(string);

        String imageString = "https://s-media-cache-ak0.pinimg.com/736x/b7/48/98/b74898796fd9073ebd85ca9a6ce9d8b7.jpg";
        List<String> stringList = new ArrayList<>();
        stringList.add(imageString);
        stringList.add(imageString);
        roomDetail.setRoomImageLarge(stringList);
        roomDetail.setRoomImageSmall(stringList);
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

        tvBedFee.setText(roomDetail.getBedFee());
        tvBedWidth.setText(roomDetail.getBedWidth());
        tvOtherInfo.setText(roomDetail.getOtherInfo());
        tvRoomSize.setText(roomDetail.getRoomSize());
        tvRoomType.setText(roomDetail.getRoomType());

        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), roomDetail.getRoomImageSmall());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchooseroomtype.setLayoutManager(layoutManager);
        rvchooseroomtype.setHasFixedSize(true);
        rvchooseroomtype.setAdapter(commonTypeChoiceAdapter);
        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                if(roomDetail.getRoomImageLarge().get(position) != null && !roomDetail.getRoomImageLarge().get(position).equals("")) {
                    Picasso.with(getContext())
                            .load(roomDetail.getRoomImageLarge().get(position))
                            .error(R.drawable.bedroom)
                            .into(ivDetailImageContainer);
                }
            }
        });
        
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
