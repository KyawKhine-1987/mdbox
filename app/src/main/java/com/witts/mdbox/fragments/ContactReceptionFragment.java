package com.witts.mdbox.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.ReceptionContactQuestionAdapter;
import com.witts.mdbox.adapter.ReceptionContactTypeAdapter;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.ContactQuestion;
import com.witts.mdbox.model.ContactType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactReceptionFragment extends Fragment {
    private static final String ARG_CONTACTTYPE = "contactType";
    private String mContactType;
    @BindView(R.id.rvcontact_type)
    RecyclerView rvcontact_type;
    @BindView(R.id.rvcontact_question)
    RecyclerView rvcontact_question;
    ReceptionContactTypeAdapter contactTypeAdapter;
    ReceptionContactQuestionAdapter contactQuestionAdapter;
    ContactType contactType;
    List<ContactType> contactTypeList = new ArrayList<>();
    ContactQuestion contactQuestion;
    List<ContactQuestion> contactQuestionList = new ArrayList<>();
    public ContactReceptionFragment() {}

    public static ContactReceptionFragment newInstance(String contactType) {
        ContactReceptionFragment fragment = new ContactReceptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTACTTYPE, contactType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContactType = getArguments().getString(ARG_CONTACTTYPE);
        }
        contactType = new ContactType();
        contactType.setContractType("Water");
        contactTypeList.add(contactType);
        contactType = new ContactType();
        contactType.setContractType("Toilet");
        contactTypeList.add(contactType);
        contactType = new ContactType();
        contactType.setContractType("Bathtub");
        contactTypeList.add(contactType);
        contactType = new ContactType();
        contactType.setContractType("Service");
        contactTypeList.add(contactType);
        contactType = new ContactType();
        contactType.setContractType("Room");
        contactTypeList.add(contactType);
        contactQuestion = new ContactQuestion();
        contactQuestion.setContactQuestion1("Problem with water supply");
        contactQuestion.setContactQuestion2("Problem with water supply");
        contactQuestionList.add(contactQuestion);
        contactQuestion = new ContactQuestion();
        contactQuestion.setContactQuestion1("Problem with water supply");
        contactQuestion.setContactQuestion2("Problem with water supply");
        contactQuestionList.add(contactQuestion);
        contactQuestion = new ContactQuestion();
        contactQuestion.setContactQuestion1("Problem with water supply");
        contactQuestion.setContactQuestion2("Problem with water supply");
        contactQuestionList.add(contactQuestion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactTypeAdapter = new ReceptionContactTypeAdapter(getContext(), contactTypeList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvcontact_type.setLayoutManager(layoutManager);
        rvcontact_type.setHasFixedSize(true);
        rvcontact_type.setAdapter(contactTypeAdapter);
        contactTypeAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                callrvContactQuestionAdapter();}
        });
        callrvContactQuestionAdapter();

    }

    private void callrvContactQuestionAdapter(){
        contactQuestionAdapter = new ReceptionContactQuestionAdapter(getContext(), contactQuestionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvcontact_question.setLayoutManager(gridLayoutManager);
        rvcontact_question.setHasFixedSize(true);
        rvcontact_question.setAdapter(contactQuestionAdapter);
        contactQuestionAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.CustomDialog);
                builder.setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                try{
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button positiveButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negativeButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                    if(positiveButton != null) {
                        positiveButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_background));
                    }
                    if(negativeButton != null) {
                        negativeButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_background));
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(20,0,20,0);
                    negativeButton.setLayoutParams(params);
                    positiveButton.setLayoutParams(params);
                }catch (Exception e) {
                    System.out.println("display dialog error");
                }
            }
        });
    }
}
