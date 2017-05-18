package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ContactType contactType = contactTypeList.get(position);

        holder.tvcontact_type.setText(contactType.getContractType());
        if(contactType.getContactImageUrl() != null && !contactType.getContactImageUrl().equals("")) {
            Picasso.with(context).load(contactType.getContactImageUrl()).into(holder.ivcontact_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, contactType);
            }
        });
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
        public TextView tvcontact_type;
        public ImageView ivcontact_image;

        public ViewHolder(View itemView) {
            super(itemView);
            tvcontact_type = (TextView) itemView.findViewById(R.id.tvcontact_type);
            ivcontact_image = (ImageView) itemView.findViewById(R.id.ivcontact_image);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
