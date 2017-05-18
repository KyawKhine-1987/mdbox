package com.witts.mdbox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.ContactQuestion;
import com.witts.mdbox.model.ContactType;

import java.util.List;

/**
 * Created by wm02 on 5/18/2017.
 */

public class ReceptionContactQuestionAdapter extends RecyclerView.Adapter<ReceptionContactQuestionAdapter.ViewHolder> {

    private Context context;
    private List<ContactQuestion> contactQuestionList;
    private ItemClickListener<ContactQuestion> itemClickListener;

    public ReceptionContactQuestionAdapter(Context context, List<ContactQuestion> contactQuestionList) {
        this.context = context;
        this.contactQuestionList = contactQuestionList;
    }

    @Override
    public ReceptionContactQuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceptionContactQuestionAdapter.ViewHolder holder,final int position) {
        final ContactQuestion contactQuestion = contactQuestionList.get(position);

        holder.tvquestion1.setText(contactQuestion.getContactQuestion1());
        holder.tvquestion2.setText(contactQuestion.getContactQuestion2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, contactQuestion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactQuestionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvquestion1;
        public TextView tvquestion2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvquestion1 = (TextView) itemView.findViewById(R.id.tvquestion1);
            tvquestion2 = (TextView) itemView.findViewById(R.id.tvquestion2);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
