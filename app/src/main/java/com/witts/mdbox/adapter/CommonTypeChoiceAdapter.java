package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.interfaces.ItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wm02 on 5/4/2017.
 */

public class CommonTypeChoiceAdapter extends RecyclerView.Adapter<CommonTypeChoiceAdapter.ViewHolder>{

    private Context mContext;
    List<String> roomImageList;
    private Animation animScale;
    private ItemClickListener<String> itemClickListener;
    private int focusedItem = 0;
    private String accessToken= Constant.ACCESS_TOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    public CommonTypeChoiceAdapter(Context context, List<String> roomImageList) {
        this.mContext = context;
        this.roomImageList = roomImageList;
        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hotel_room_choice_adapter, parent, false);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
        holder.itemView.setSelected(focusedItem == position);
        final String imageUrl = roomImageList.get(position);
        if(imageUrl != null && !imageUrl.equals("")) {
            String imageapi = imageUrl+"/?accessToken="+accessToken+"&date="+date+"&" +
                    "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
            Glide.with(mContext)
                    .load(imageapi)
                    .placeholder(R.drawable.spinner_of_dots)
                    .error(R.drawable.bedroom_small)
                    .into(holder.ivroomviewchoice);
        }
//        holder.ivroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_small));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position,imageUrl);
                holder.ivroomviewchoice.setBackgroundResource(R.drawable.background);
//                notifyItemChanged(focusedItem);
//                focusedItem = position;
//                notifyItemChanged(focusedItem);
//                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    holder.ivroomviewchoice.setBackgroundResource(R.drawable.background);
                }else{
                    holder.ivroomviewchoice.setBackgroundResource(0);
                }
            }
        });
    }

//    private void loadButton(final roomTypeChoiceAdapterViewHolder holder, int position) {
//        if(position==0){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.handcream));
//        }
//        else if(position==1){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.singleroom_small));
//        }
//        else if(position==2){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_small));
//        }
//        else if(position==3){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_overview_small));
//        }
//        else if(position==4){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bathtub_small));
//        }
//        else if(position==5){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.toilet_small));
//        }
//        else if(position==6){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.washroom_small));
//        }
//        else if(position==7){
//            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.handcream));
//        }
////        holder.llimgchooser.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////            holder.
////            }
////        });
//        }

    @Override
    public int getItemViewType(int position) {
        return position;
        }

    @Override
    public int getItemCount() {return roomImageList.size();}

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivroomviewchoice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivroomviewchoice = (ImageView) itemView.findViewById(R.id.ivroomviewchoice);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    notifyItemChanged(focusedItem);
//                    focusedItem = getLayoutPosition();
//                    ivroomviewchoice.setBackgroundResource(R.drawable.image_choicer_background);
//                    notifyItemChanged(focusedItem);
//                    notifyDataSetChanged();
//                }
//            });
        }
    }
}

