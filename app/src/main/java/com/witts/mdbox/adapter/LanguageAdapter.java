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

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.WelcomeMessage;

import java.util.List;

/**
 * Created by wm02 on 4/5/2017.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{
    private Context context;
    private Animation animScale;
    private List<WelcomeMessage> welcomeMessageList;

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

        holder.tvWelcome.setText(welcomeMessage.getWelcomeMessage());
        if(welcomeMessage.getLanguageFlagUrl() != null && !welcomeMessage.getLanguageFlagUrl().equals("")) {
            Picasso.with(context).load(welcomeMessage.getLanguageFlagUrl()).into(holder.ivFlag);
        }
        holder.tvLanguage.setText(welcomeMessage.getDisplayLanguageName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, welcomeMessage);
                animScale = AnimationUtils.loadAnimation(context, R.anim.scale_up);
                holder.llcontainer.startAnimation(animScale);
                holder.llcontainer.bringToFront();
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
