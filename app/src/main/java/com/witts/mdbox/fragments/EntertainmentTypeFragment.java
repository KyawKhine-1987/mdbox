package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.witts.mdbox.R;

public class EntertainmentTypeFragment extends Fragment {
    private static final String ARG_TYPE = "param1";

    private String mType;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment_type, container, false);
    }
}
