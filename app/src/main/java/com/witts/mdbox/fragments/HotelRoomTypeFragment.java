package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.RoomTypeChoiceAdapter;

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
    ScrollView svinfocontainer;
    private Handler mHandler = new Handler();
    RoomTypeChoiceAdapter roomChoiceAdapter;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rvchooseroomtype.setLayoutManager(layoutManager);
        rvchooseroomtype.setHasFixedSize(true);
        roomChoiceAdapter = new RoomTypeChoiceAdapter(getContext(),new RoomTypeChoiceAdapter.roomTypeChoiceAdapterOnClickHandler(){
            @Override
            public void onClick(String bookingId, RoomTypeChoiceAdapter.roomTypeChoiceAdapterViewHolder vh) {

            }
        });
        rvchooseroomtype.setAdapter(roomChoiceAdapter);
        imguparrow.setOnClickListener(this);
        imgdownarrow.setOnClickListener(this);
        //mDetecor = new GestureDetector(getActivity(), (GestureDetector.OnGestureListener) getActivity());
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

//    private void perfromPageScroll() {
//        int direction = getCurrentBtnDirection();
//        svinfo.pageScroll(direction);
//    }
//
//    private void stopContinuousScroll() {
//        mStartTime = 0;
//        mHandler.removeCallbacks(mScrollRunnable);
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        mCurBtn = v;
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            stopContinuousScroll();
//        }
//        return true;
//    }
//
//    private int getCurrentBtnDirection() {
//        if (mCurBtn == imguparrow) {
//            return View.FOCUS_UP;
//        } else if (mCurBtn == imgdownarrow) {
//            return View.FOCUS_DOWN;
//        } else
//            return 0;
//    }
}
