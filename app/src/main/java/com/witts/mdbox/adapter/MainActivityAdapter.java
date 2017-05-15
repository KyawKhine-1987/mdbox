package com.witts.mdbox.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.activity.MenuActivity;

/**
 * Created by wm02 on 4/5/2017.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityAdapterViewHolder>{

    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    public static MainActivityAdapterOnClickHandler mClickHandler;
    private MainActivityAdapterViewHolder mViewHolder;
    private Animation animScale;
    private String[] mWelcomeArr;
    private String[] mLanguageArr;
    private int[] mImgArr;
    public MainActivityAdapter() {}
    public MainActivityAdapter(Context context,String[] welcomeArr,String[] languageArr,int[] imgArr,MainActivityAdapterOnClickHandler dh) {
        mContext = context;
        mClickHandler = dh;
        mWelcomeArr = welcomeArr;
        mLanguageArr = languageArr;
        mImgArr = imgArr;
    }

    @Override
    public void onBindViewHolder(MainActivityAdapterViewHolder holder, int position) {
        loadLanguageMenu(holder,position);
    }

    private void loadLanguageMenu(final MainActivityAdapterViewHolder holder, int position) {
        if (position == 0){
            holder.txtWelcome.setText(mWelcomeArr[position]);
            holder.txtLanguage.setText(mLanguageArr[position]);
            holder.imgFlag.setImageDrawable(mContext.getResources().getDrawable(mImgArr[position]));
//            holder.mainActivityListener=new MainActivityAdapterViewHolder.ViewHolderClickListener() {
//                @Override
//                public void onClick(View v) {
//                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
//                    holder.llcontainer.startAnimation(animScale);
//                    holder.llcontainer.bringToFront();
//                    goToMenuActivity();
//                }
//            };

        }
        else if (position == 1){
            holder.txtWelcome.setText(mWelcomeArr[position]);
            holder.txtLanguage.setText(mLanguageArr[position]);
            holder.imgFlag.setImageDrawable(mContext.getResources().getDrawable(mImgArr[position]));
//            holder.mainActivityListener=new MainActivityAdapterViewHolder.ViewHolderClickListener() {
//                @Override
//                public void onClick(View v) {
//                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
//                    holder.llcontainer.startAnimation(animScale);
//                    holder.llcontainer.bringToFront();
//                    goToMenuActivity();
//                }
//            };

        }
        else if (position == 2){
            holder.txtWelcome.setText(mWelcomeArr[position]);
            holder.txtLanguage.setText(mLanguageArr[position]);
            holder.imgFlag.setImageDrawable(mContext.getResources().getDrawable(mImgArr[position]));
//            holder.mainActivityListener=new MainActivityAdapterViewHolder.ViewHolderClickListener() {
//                @Override
//                public void onClick(View v) {
//                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
//                    holder.llcontainer.startAnimation(animScale);
//                    holder.llcontainer.bringToFront();
//                    goToMenuActivity();
//                }
//            };

        }
        else if (position == 3){
            holder.txtWelcome.setText(mWelcomeArr[position]);
            holder.txtLanguage.setText(mLanguageArr[position]);
            holder.imgFlag.setImageDrawable(mContext.getResources().getDrawable(mImgArr[position]));
//            holder.mainActivityListener=new MainActivityAdapterViewHolder.ViewHolderClickListener() {
//                @Override
//                public void onClick(View v) {
//                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
//                    holder.llcontainer.startAnimation(animScale);
//                    holder.llcontainer.bringToFront();
//                    goToMenuActivity();
//                }
//            };

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public MainActivityAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_main_adapter, viewGroup, false);
        mViewHolder = new MainActivityAdapterViewHolder(view);
        return mViewHolder;
    }

    @Override
    public int getItemCount() {return 4;}

    public static class MainActivityAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtWelcome;
        public TextView txtLanguage;
        public ImageView imgFlag;
        public LinearLayout llcontainer;
        public ViewHolderClickListener mainActivityListener;

        public MainActivityAdapterViewHolder(View itemView) {
            super(itemView);
            txtWelcome=(TextView)itemView.findViewById(R.id.txtWelcome);
            txtLanguage=(TextView)itemView.findViewById(R.id.txtLanguage);
            imgFlag=(ImageView)itemView.findViewById(R.id.imgflag);
            llcontainer=(LinearLayout)itemView.findViewById(R.id.llcontainer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(getItemViewType(),new MainActivityAdapterViewHolder(v));
        }

        public static interface ViewHolderClickListener {
            public void onClick(View v);
        }
    }

    public static interface MainActivityAdapterOnClickHandler {
        void onClick(int position, MainActivityAdapterViewHolder vh);
    }

}
