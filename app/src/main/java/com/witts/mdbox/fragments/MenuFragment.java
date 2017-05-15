package com.witts.mdbox.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.activity.HotelRoomDetailActivity;
import com.witts.mdbox.adapter.MenuFragmentAdapter;
import com.witts.mdbox.util.GridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuFragment extends BaseFragment {

    @BindView(R.id.rvchoosemenu)
    RecyclerView recyclerchoosemenu;
    @BindView(R.id.tvreceptionnews)
    TextView tvreception;
    MenuFragmentAdapter menuAdapter;
    private Animation animScale;
    public static final String TAG = "MenuFragment";
    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        final View decorView = getActivity().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(
                new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int i) {
                        int height = decorView.getHeight();
                        Log.i(TAG, "Current height: " + height);
                    }
                });
        toggleHideyBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvreception.setSelected(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        tvreception.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.textmove));
        recyclerchoosemenu.setLayoutManager(gridLayoutManager);
        recyclerchoosemenu.setHasFixedSize(true);
        menuAdapter = new MenuFragmentAdapter(getContext(),new MenuFragmentAdapter.onMenuFragmentAdapterOnClickHandler(){
            @Override
            public void onClick(int position, MenuFragmentAdapter.MenuFragmentAdapterViewHolder v) {
                if(position==0) {
                    animScale = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
                    v.llcontainer.startAnimation(animScale);
                    v.llcontainer.bringToFront();
                    v.lnrbackground.setBackgroundResource(R.drawable.menu_background_one);
                    goToBedroomDetail();
                }
            }
        });
        recyclerchoosemenu.setAdapter(menuAdapter);

    }

    private void goToBedroomDetail() {
        Activity activity = (Activity) getActivity();
        Intent intent = new Intent(getContext(), HotelRoomDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().getApplicationContext().startActivity(intent);
        //activity.finish();
    }

    public void toggleHideyBar() {
        int uiOptions = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getActivity().getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

}
