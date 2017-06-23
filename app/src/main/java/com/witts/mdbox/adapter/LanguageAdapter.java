package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.WelcomeMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wm02 on 4/5/2017.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{
    private Context context;
    private Animation animScale;
    private List<WelcomeMessage> welcomeMessageList;
    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private ItemClickListener<WelcomeMessage> itemClickListener;

    public LanguageAdapter(Context context, List<WelcomeMessage> welcomeMessageList) {
        this.context = context;
        this.welcomeMessageList = welcomeMessageList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvWelcome;
        public ImageView ivFlag;
        public TextView tvLanguage;
        public LinearLayout llcontainer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvWelcome = (TextView) itemView.findViewById(R.id.tvWelcome);
            ivFlag = (ImageView) itemView.findViewById(R.id.ivFlag);
            tvLanguage = (TextView) itemView.findViewById(R.id.tvLanguage);
            llcontainer = (LinearLayout) itemView.findViewById(R.id.llcontainer);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.language_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WelcomeMessage welcomeMessage = welcomeMessageList.get(position);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
        holder.tvWelcome.setText(welcomeMessage.getWelcomeMessage());
        if(welcomeMessage.getLanguageFlagUrl() != null && !welcomeMessage.getLanguageFlagUrl().equals("")) {
            String imageapi =welcomeMessage.getLanguageFlagUrl()+"/?accessToken="+accessToken+"&date="+date+"&" +
                    "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
            Glide.with(context)
                    .load(imageapi)
                    .placeholder(R.drawable.spinner_of_dots)
                    .error(R.drawable.error_icon)
                    .into(holder.ivFlag);
        }
        holder.tvLanguage.setText(welcomeMessage.getDisplayLanguageName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.llcontainer.clearAnimation();
                holder.llcontainer.setBackground(context.getResources().getDrawable(R.drawable.appcompact_mainactivity_background));
                itemClickListener.onItemClick(position, welcomeMessage);
            }
        });

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    animScale = AnimationUtils.loadAnimation(context, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                } else
                {
                    holder.llcontainer.clearAnimation();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return welcomeMessageList.size();
    }
}
