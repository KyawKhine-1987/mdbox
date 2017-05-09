package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.witts.mdbox.R;

/**
 * Created by wm02 on 5/4/2017.
 */

public class RoomTypeChoiceAdapter extends RecyclerView.Adapter<RoomTypeChoiceAdapter.roomTypeChoiceAdapterViewHolder> implements View.OnKeyListener{

    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private roomTypeChoiceAdapterOnClickHandler mClickHandler;
    private roomTypeChoiceAdapterViewHolder mViewHolder;
    private int focusedItem = 0;
    public RoomTypeChoiceAdapter() {}
    public RoomTypeChoiceAdapter(Context context, roomTypeChoiceAdapterOnClickHandler dh) {
        mContext = context;
        mClickHandler = dh;
        }

    @Override
    public roomTypeChoiceAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hotel_room_choice_adapter, viewGroup, false);
        mViewHolder = new roomTypeChoiceAdapterViewHolder(view);
        return mViewHolder;
        }

    @Override
    public void onBindViewHolder(roomTypeChoiceAdapterViewHolder holder, int position) {
        loadButton(holder,position);
        holder.itemView.setSelected(focusedItem == position);
        }

    private void loadButton(final roomTypeChoiceAdapterViewHolder holder, int position) {
        if(position==0){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.handcream));
        }
        else if(position==1){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.singleroom_small));
        }
        else if(position==2){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_small));
        }
        else if(position==3){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bedroom_overview_small));
        }
        else if(position==4){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.bathtub_small));
        }
        else if(position==5){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.toilet_small));
        }
        else if(position==6){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.washroom_small));
        }
        else if(position==7){
            holder.imgvroomviewchoice.setImageDrawable(mContext.getResources().getDrawable(R.drawable.handcream));
        }
//        holder.llimgchooser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            holder.
//            }
//        });
        }

    @Override
    public int getItemViewType(int position) {
        return position;
        }

    @Override
    public int getItemCount() {return 8;}

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    public class roomTypeChoiceAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgvroomviewchoice;
        public ViewHolderClickListener onDemandListener;
        public LinearLayout llimgchooser;

        public roomTypeChoiceAdapterViewHolder(View itemView) {
            super(itemView);
            imgvroomviewchoice = (ImageView) itemView.findViewById(R.id.imgvroomviewchoice);
            llimgchooser = (LinearLayout) itemView.findViewById(R.id.llimgchooser);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(focusedItem);
                    focusedItem = getLayoutPosition();
                    imgvroomviewchoice.setBackgroundResource(R.drawable.image_choicer_background);
                    notifyItemChanged(focusedItem);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public static interface ViewHolderClickListener {
        public void onClick(View v);
}

    public interface roomTypeChoiceAdapterOnClickHandler {
    void onClick(String bookingId, roomTypeChoiceAdapterViewHolder vh);
}
    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        // Handle key up and key down and attempt to move selection
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
                // Return false if scrolled to the bounds and allow focus to move off the list
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        return tryMoveSelection(lm, 1);
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        return tryMoveSelection(lm, -1);
                    }
                }

                return false;
            }
        });
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int tryFocusItem = focusedItem + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()) {
            notifyItemChanged(focusedItem);
            focusedItem = tryFocusItem;
            notifyItemChanged(focusedItem);
            lm.scrollToPosition(focusedItem);
            return true;
        }

        return false;
    }

}

