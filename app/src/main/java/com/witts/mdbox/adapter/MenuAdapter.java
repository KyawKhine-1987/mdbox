package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.MenuContent;

import java.util.List;

/**
 * Created by wm02 on 4/6/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    public static Context context;
    private List<MenuContent> menuContentList;
    private ItemClickListener<MenuContent> itemClickListener;
    private Animation animScale;

    public MenuAdapter(Context context,List<MenuContent> menuContentList) {
        this.context = context;
        this.menuContentList = menuContentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final MenuContent menuContent = menuContentList.get(position);

        holder.tvlabel.setText(menuContent.getMenuTitle());
        if(menuContent.getMenuImgUrl() != null && !menuContent.getMenuImgUrl().equals("")) {
            Picasso.with(context).load(menuContent.getMenuImgUrl()).into(holder.ivmenu);
        }
        if(menuContent.getMenuBackgroundUrl() != null && !menuContent.getMenuBackgroundUrl().equals("")) {
            Picasso.with(context).load(menuContent.getMenuBackgroundUrl()).into(holder.imgbackground);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, menuContent);
                animScale = AnimationUtils.loadAnimation(context, R.anim.scale_up);
                //holder.flcontainer.startAnimation(animScale);
                holder.flcontainer.bringToFront();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return menuContentList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgbackground;
        public ImageView ivmenu;
        public TextView tvlabel;
        public FrameLayout flcontainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmenu = (ImageView) itemView.findViewById(R.id.ivmenu);
            tvlabel = (TextView) itemView.findViewById(R.id.tvlabel);
            imgbackground = (ImageView) itemView.findViewById(R.id.imgbackground);
            flcontainer = (FrameLayout) itemView.findViewById(R.id.flcontainer);
        }
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    }
