package com.witts.mdbox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
    private String[] welcomeArr={"Welcome To Sakura","Welcome To Sakura","Welcome To Sakura","Welcome To Sakura"};
    private String[] languageArr={"English","English","English","English"};
    private int[] imgArr={R.drawable.flag_of_united_states,R.drawable.flag_of_united_states,R.drawable.flag_of_united_states,R.drawable.flag_of_united_states};
    private Animation animScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void goToMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
        //finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        currentApiVersion = Build.VERSION.SDK_INT;
        mDemandLayoutManager = new LinearLayoutManager(this);
        recyclerchooselanguage.setLayoutManager(mDemandLayoutManager);
        recyclerchooselanguage.setHasFixedSize(true);
        menuAdapter = new MainActivityAdapter(this,welcomeArr,languageArr,imgArr,new MainActivityAdapter.MainActivityAdapterOnClickHandler(){

            @Override
            public void onClick(int position, MainActivityAdapter.MainActivityAdapterViewHolder vh) {
                animScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                vh.llcontainer.startAnimation(animScale);
                vh.llcontainer.bringToFront();
                goToMenuActivity();
            }
        });


        recyclerchooselanguage.setAdapter(menuAdapter);
        recyclerchooselanguage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
    }
}
