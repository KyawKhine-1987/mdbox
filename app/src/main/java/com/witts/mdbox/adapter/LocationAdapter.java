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
import com.witts.mdbox.model.LocationList;

import java.util.List;

/**
 * Created by Kyaw Khine on 06/08/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private static final String LOG_TAG = LocationAdapter.class.getSimpleName();

    private Context mContext;
    private List<String> mLocationList;
    private ItemClickListener<LocationList> itemClickListener;

    public LocationAdapter(Context context, List<String> locationList) {
        Log.i(LOG_TAG, "TEST : LocationAdapter() called...");

        this.mContext = context;
        this.mLocationList = locationList;
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "TEST : onCreateViewHolder() called...");

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder holder, int position) {
        Log.i(LOG_TAG, "TEST : onBindViewHolder() called...");

        String locationName = mLocationList.get(position);

        holder.LocationName.setText(locationName);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "TEST : getItemCount() called...");

        return mLocationList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final String LOG_TAG = ViewHolder.class.getSimpleName();

        public TextView LocationName;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.i(LOG_TAG, "TEST : ViewHolder() called...");

            LocationName = (TextView)itemView.findViewById(R.id.tv_LocationName);
        }
    }
}
