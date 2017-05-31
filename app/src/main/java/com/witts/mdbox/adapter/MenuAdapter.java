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
    private int pos = 0;

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
            Picasso.with(context).load(menuContent.getMenuImgUrl()).error(R.drawable.foodanddrink).into(holder.ivmenu);
        }
        normalBackground(position,holder.imgbackground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, menuContent);
                animScale = AnimationUtils.loadAnimation(context, R.anim.scale_up);
                animatedBackground(position,holder.imgbackground);
                holder.flcontainer.startAnimation(animScale);

            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    animScale = AnimationUtils.loadAnimation(context, R.anim.scale_up);
                    animatedBackground(position,holder.imgbackground);
                    holder.flcontainer.startAnimation(animScale);
                } else
                {
                    holder.flcontainer.clearAnimation();
                    normalBackground(position,holder.imgbackground);
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

    public void normalBackground(int position,View v){
        ImageView imgView = (ImageView)v;
        pos = position%6;
        switch (pos){
            case 0:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgone));
                break;
            case 1:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgtwo));
                break;
            case 2:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgthree));
                break;
            case 3:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgfour));
                break;
            case 4:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgfive));
                break;
            case 5:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgsix));
                break;
            default:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.bgone));
                break;

        }
    }

    public void animatedBackground(int position,View v){
        ImageView imgView = (ImageView)v;
        pos = position%6;
        switch (pos) {
            case 0:imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_one));
                break;
            case 1:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_two));
                break;
            case 2:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_three));
                break;
            case 3:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_four));
                break;
            case 4:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_five));
                break;
            case 5:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_six));
                break;
            default:
                imgView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_background_one));
                break;
        }
    }

    }
