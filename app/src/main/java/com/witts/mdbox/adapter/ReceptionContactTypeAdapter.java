package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.ContactType;

import java.util.List;

/**
 * Created by wm02 on 5/18/2017.
 */

public class ReceptionContactTypeAdapter extends RecyclerView.Adapter<ReceptionContactTypeAdapter.ViewHolder> {

    private Context context;
    private List<ContactType> contactTypeList;
    private ItemClickListener<ContactType> itemClickListener;
    private int focusedItem = 0;
    private static int selected_item = -1;
    private int previous_selected_item = 0;
    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    public ReceptionContactTypeAdapter(Context context, List<ContactType> contactTypeList) {
        this.context = context;
        this.contactTypeList = contactTypeList;
    }
    @Override
    public ReceptionContactTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ContactType contactType = contactTypeList.get(position);
        holder.tvcontact_type.setText(contactType.getContractType());
        if(contactType.getContactImageUrl() != null && !contactType.getContactImageUrl().equals("")) {
                String imageapi = contactType.getContactImageUrl()+"/?accessToken="+accessToken+"&date="+date+"&" +
                        "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
                Glide.with(context)
                        .load(imageapi)
                        .placeholder(R.drawable.spinner_of_dots)
                        .error(R.drawable.spinner_of_dots)
                        .into(holder.ivcontact_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, contactType);
                if(selected_item == -1)
                selected_item = position;
                notifyItemChanged(selected_item);
                holder.itemView.setSelected(true);
                focusedItem = holder.getLayoutPosition();
                selected_item = focusedItem;
                holder.llcontainer.setBackground(context.getResources().getDrawable(R.drawable.contact_background_on));
                notifyItemChanged(selected_item);
//                if(holder.itemView.isSelected())
//                {
//                if(previous_selected_item != selected_item) {
//                    contactTypeList.get(position).setTextColor(context.getResources().getColor(R.color.black));
//                }
//                    holder.tvcontact_type.setTextColor(context.getResources().getColor(R.color.white));
                // previous_selected_item = position;
//                }
//                else {
                    //holder.tvcontact_type.setTextColor(context.getResources().getColor(R.color.black));
//                }
            }
        });

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                  holder.llcontainer.setBackground(context.getResources().getDrawable(R.drawable.contact_background_on));
                }
                else if(selected_item == position){
                    holder.llcontainer.setBackground(context.getResources().getDrawable(R.drawable.contact_background_on));
                }
                else {
                    holder.llcontainer.setBackground(context.getResources().getDrawable(R.drawable.contact_background_unselect));
                }
            }
        });

        holder.tvcontact_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
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

    @Override
    public int getItemCount() {
        return contactTypeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llcontainer;
        public TextView tvcontact_type;
        public ImageView ivcontact_image;

        public ViewHolder(View itemView) {
            super(itemView);
            tvcontact_type = (TextView) itemView.findViewById(R.id.tvcontact_type);
            ivcontact_image = (ImageView) itemView.findViewById(R.id.ivcontact_image);
            llcontainer = (LinearLayout) itemView.findViewById(R.id.llcontainer);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
