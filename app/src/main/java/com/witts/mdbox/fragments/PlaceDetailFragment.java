package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.witts.mdbox.R;

public class PlaceDetailFragment extends Fragment {

    private static final String ARG_TABPLACE = "param1";

    private String mTABPLACE;

    public PlaceDetailFragment() {
        // Required empty public constructor
    }


    public static PlaceDetailFragment newInstance(String tabtitle) {
        PlaceDetailFragment fragment = new PlaceDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TABPLACE, tabtitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTABPLACE = getArguments().getString(ARG_TABPLACE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_detail, container, false);
    }
}
