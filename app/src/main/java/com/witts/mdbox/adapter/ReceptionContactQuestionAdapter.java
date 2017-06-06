package com.witts.mdbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.QAObject;

import java.util.List;

/**
 * Created by wm02 on 5/18/2017.
 */

public class ReceptionContactQuestionAdapter extends RecyclerView.Adapter<ReceptionContactQuestionAdapter.ViewHolder> {

    private Context context;
    private List<String> QAObjectList;
    private ItemClickListener<String> itemClickListener;

    public ReceptionContactQuestionAdapter(Context context, List<String> QAObjectList) {
        this.context = context;
        this.QAObjectList = QAObjectList;
    }

    @Override
    public ReceptionContactQuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReceptionContactQuestionAdapter.ViewHolder holder, final int position) {
        final String QAObject = QAObjectList.get(position);

        holder.tvquestion1.setText(QAObject);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, QAObject);
            }
        });

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    holder.llcontainer.setBackgroundResource(R.drawable.contact_background_select);
                }
                else {
                    holder.llcontainer.setBackgroundResource(R.drawable.contact_background_unselect);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return QAObjectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvquestion1;
        public LinearLayout llcontainer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvquestion1 = (TextView) itemView.findViewById(R.id.tvquestion1);
            llcontainer = (LinearLayout) itemView.findViewById(R.id.llcontainer);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
