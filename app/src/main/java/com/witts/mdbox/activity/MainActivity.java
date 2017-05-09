package com.witts.mdbox.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.witts.mdbox.R;
import com.witts.mdbox.adapter.MainActivityAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BasedActivity {
    private int currentApiVersion;
    @BindView(R.id.rvchooselanguage)
    RecyclerView recyclerchooselanguage;
    LinearLayoutManager mDemandLayoutManager;
    MainActivityAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        currentApiVersion = Build.VERSION.SDK_INT;
        mDemandLayoutManager = new LinearLayoutManager(this);
        recyclerchooselanguage.setLayoutManager(mDemandLayoutManager);
        recyclerchooselanguage.setHasFixedSize(true);
        menuAdapter = new MainActivityAdapter(this,new MainActivityAdapter.MainActivityAdapterOnClickHandler(){
            @Override
            public void onClick(String bookingId, MainActivityAdapter.MainActivityAdapterViewHolder vh) {
            }
        });

        recyclerchooselanguage.setAdapter(menuAdapter);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }
}
