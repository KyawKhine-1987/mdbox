package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by wm02 on 5/4/2017.
 */

public class RoomTypeChoiceAdapter extends RecyclerView.Adapter<RoomTypeChoiceAdapter.ViewHolder> implements View.OnKeyListener{

    private Context mContext;
    List<String> roomImageList;
    private ItemClickListener<String> itemClickListener;
    private int focusedItem = 0;
    public RoomTypeChoiceAdapter(Context context, List<String> roomImageList) {
        this.mContext = context;
        this.roomImageList = roomImageList;
        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hotel_room_choice_adapter, parent, false);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        holder.itemView.setSelected(focusedItem == position);
        final String imageUrl = roomImageList.get(position);
        if(imageUrl != null && !imageUrl.equals("")) {
            Picasso.with(mContext)
                    .load(imageUrl)
                    .error(R.drawable.bedroom_small)
                    .into(holder.ivroomviewchoice);
        }
//        holder.ivroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_small));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position,imageUrl);
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivroomviewchoice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivroomviewchoice = (ImageView) itemView.findViewById(R.id.ivroomviewchoice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(focusedItem);
                    focusedItem = getLayoutPosition();
                    ivroomviewchoice.setBackgroundResource(R.drawable.image_choicer_background);
                    notifyItemChanged(focusedItem);
                    notifyDataSetChanged();
                }
            });
        }
    }


}

