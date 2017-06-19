package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by Kyaw Khine on 06/08/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private static final String LOG_TAG = LocationAdapter.class.getSimpleName();

    //Declare mContext, String List Object of mLocationList and String ItemClickedListener Object of mItemClickListener.
    private Context mContext;
    private List<String> mLocationList;
    private ItemClickListener<String> mItemClickListener;

    //Constructor
    public LocationAdapter(Context context, List<String> locationList) {
        Log.i(LOG_TAG, "TEST : LocationAdapter() called...");

        this.mContext = context;
        this.mLocationList = locationList;
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "TEST : onCreateViewHolder() called...");

        //Load the LocationName LayoutInflater View from item_location_name.xml.
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location_name, parent, false);

        //Return to the inner Class ViewHolder with View.
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder holder, final int position) {
        Log.i(LOG_TAG, "TEST : onBindViewHolder() called...");

        //Get the locationName data from mLocationList position.
        final String locationName = mLocationList.get(position);

        //Title holder set the LocationName data for locationName().
        holder.LocationName.setText(locationName);

        //Set the OnClickListener in itemView of the RecyclerView.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set the relevant position data in the RecyclerView.
                mItemClickListener.onItemClick(position, locationName);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(LOG_TAG, "TEST : getItemViewType() called...");

        //Return the relevant data position.
        return position;
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "TEST : getItemCount() called...");

        //Return the relevant data of mLocationList size.
        return mLocationList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        Log.i(LOG_TAG, "TEST : setItemClickListener() called...");

        //Set the String ItemClickedListener Object of mItemClickListener.
        this.mItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        //Declare textView of LocationName for TypeCast.
        public TextView LocationName;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.i(LOG_TAG, "TEST : ViewHolder() called...");

            LocationName = (TextView)itemView.findViewById(R.id.tv_LocationName);
        }
    }
}
