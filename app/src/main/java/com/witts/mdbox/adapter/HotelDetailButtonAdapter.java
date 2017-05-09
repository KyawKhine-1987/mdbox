package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.witts.mdbox.R;

/**
 * Created by wm02 on 4/11/2017.
 */

public class HotelDetailButtonAdapter extends RecyclerView.Adapter<HotelDetailButtonAdapter.hotelDetailButtonAdapterViewHolder>{

    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private HotelDetailReqAdapterOnClickHandler mClickHandler;
    private hotelDetailButtonAdapterViewHolder mViewHolder;
    public HotelDetailButtonAdapter() {}
    public HotelDetailButtonAdapter(Context context, HotelDetailReqAdapterOnClickHandler dh) {
        mContext = context;
        mClickHandler = dh;
    }

    @Override
    public hotelDetailButtonAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_hotel_room_button_adapter, viewGroup, false);
        mViewHolder = new hotelDetailButtonAdapterViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(hotelDetailButtonAdapterViewHolder holder, int position) {
        loadButton(holder,position);
    }

    private void loadButton(hotelDetailButtonAdapterViewHolder holder, int position) {
        if(position==0){
            holder.buttondesc.setText("Bedroom");
            holder.buttondesc.setChecked(true);
            if(holder.buttondesc.isChecked()){

            }
        }
        else if(position==1){
            holder.buttondesc.setText("Living Room");
        }
        else if(position==2){
            holder.buttondesc.setText("Bathroom");
        }
        else if(position==3){
            holder.buttondesc.setText("Balcony");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {return 4;}

    public static class hotelDetailButtonAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatCheckBox buttondesc;
        public ViewHolderClickListener onDemandListener;

        public hotelDetailButtonAdapterViewHolder(View itemView) {
            super(itemView);
            buttondesc=(AppCompatCheckBox)itemView.findViewById(R.id.buttondesc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDemandListener.onClick(v);
        }

        public static interface ViewHolderClickListener {
            public void onClick(View v);
        }
    }

    public static interface HotelDetailReqAdapterOnClickHandler {
        void onClick(String bookingId, hotelDetailButtonAdapterViewHolder vh);
    }
}
