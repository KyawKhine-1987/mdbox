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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.witts.mdbox.R;
import com.witts.mdbox.activity.BasedActivity;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.adapter.ReceptionContactQuestionAdapter;
import com.witts.mdbox.adapter.ReceptionContactTypeAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.MenuContent;
import com.witts.mdbox.model.QAListWrapper;
import com.witts.mdbox.model.QAObject;
import com.witts.mdbox.model.ContactType;
import com.witts.mdbox.model.Question;
import com.witts.mdbox.model.QuestionContent;
import com.witts.mdbox.model.QuestionSubCategory;
import com.witts.mdbox.model.QuestionWrapper;
import com.witts.mdbox.model.SendQuestionWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.QAListService;
import com.witts.mdbox.service.QuestionService;
import com.witts.mdbox.service.SendQuestionService;
import com.witts.mdbox.service.WelcomeService;
import com.witts.mdbox.util.PropertiesUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContactReceptionFragment extends BaseFragment {
    private static final String ARG_CONTACTTYPE = "contactType";
    private ArrayList<QuestionSubCategory> questionSubCategoryList;
    @BindView(R.id.rvcontact_type)
    RecyclerView rvcontact_type;
    @BindView(R.id.rvcontact_question)
    RecyclerView rvcontact_question;
    ReceptionContactTypeAdapter contactTypeAdapter;
    ReceptionContactQuestionAdapter contactQuestionAdapter;
    ContactType contactType;
    List<ContactType> contactTypeList = new ArrayList<>();
    QAObject QAObject;
    List<QuestionContent> QAObjectList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();
    QuestionContent questionContent;
    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    public ContactReceptionFragment() {}

    public static ContactReceptionFragment newInstance(ArrayList<QuestionSubCategory> questionSubCategoryList) {
        ContactReceptionFragment fragment = new ContactReceptionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CONTACTTYPE, questionSubCategoryList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionSubCategoryList = new ArrayList<>();
        if (getArguments() != null) {
            questionSubCategoryList = getArguments().getParcelableArrayList(ARG_CONTACTTYPE);
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
        bindquestiondata();
        }

    private void bindquestiondata() {
        for (int i = 0; i < questionSubCategoryList.size(); i++){
            if(questionSubCategoryList.get(i).getPublishInd().equals("Y")){
                int size = questionSubCategoryList.get(i).getSubCategoryAttributes().size();
                for(int j = 0;j<size;j ++){
                    if(questionSubCategoryList.get(i).getSubCategoryAttributes().get(j).getLanguageCode().equals(LanguageActivity.languageCode))
                    {
                        contactType = new ContactType();
                        contactType.setContractType(questionSubCategoryList.get(i).getSubCategoryAttributes().get(j).getName());
                        contactTypeList.add(contactType);
                    }
                }
                questionList = questionSubCategoryList.get(i).getQuestionList();
                QAObjectList = new ArrayList<>();
                if(questionList!=null) {
                    for (int j = 0; j < questionList.size(); j++) {
                        if (questionList.get(j).getQuestionAttributes().size() > 0)
                            if (questionList.get(j).getQuestionAttributes().get(0).getLanguageCode().equals(LanguageActivity.languageCode)) {
                                questionContent = new QuestionContent();
                                questionContent.setQuestion(questionList.get(j).getQuestionAttributes().get(0).getQuestionText());
                                questionContent.setQuestionID(questionList.get(j).getQuestionId());
                                QAObjectList.add(questionContent);
                            }
                    }
                }
//                else
//                     QAObjectList.add("Nothing to show");
//
            }
        }
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
        rvcontact_type.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        contactTypeAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                questionList = questionSubCategoryList.get(position).getQuestionList();
                QAObjectList = new ArrayList<>();
                if(questionList!=null) {
                    for (int j = 0; j < questionList.size(); j++) {
                        if (questionList.get(j).getQuestionAttributes().size() > 0)
                            if (questionList.get(j).getQuestionAttributes().get(0).getLanguageCode().equals(LanguageActivity.languageCode)) {
                                questionContent = new QuestionContent();
                                questionContent.setQuestion(questionList.get(j).getQuestionAttributes().get(0).getQuestionText());
                                questionContent.setQuestionID(questionList.get(j).getQuestionId());
                                QAObjectList.add(questionContent);
                            }
                }
                }
                callrvContactQuestionAdapter();}
        });
        questionList = questionSubCategoryList.get(0).getQuestionList();
        QAObjectList = new ArrayList<>();
        if(questionList!=null) {
            for (int j = 0; j < questionList.size(); j++) {
                if (questionList.get(j).getQuestionAttributes().size() > 0)
                    if (questionList.get(j).getQuestionAttributes().get(0).getLanguageCode().equals(LanguageActivity.languageCode)) {
                        questionContent = new QuestionContent();
                        questionContent.setQuestion(questionList.get(j).getQuestionAttributes().get(0).getQuestionText());
                        questionContent.setQuestionID(questionList.get(j).getQuestionId());
                        QAObjectList.add(questionContent);
                    }
            }
        }
        callrvContactQuestionAdapter();
    }

    private void callrvContactQuestionAdapter() {
            contactQuestionAdapter = new ReceptionContactQuestionAdapter(getContext(), QAObjectList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            rvcontact_question.setLayoutManager(gridLayoutManager);
            rvcontact_question.setHasFixedSize(true);
            rvcontact_question.setAdapter(contactQuestionAdapter);
            contactQuestionAdapter.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position, final Object data) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
                    builder.setCancelable(false)
                            .setPositiveButton(PropertiesUtil.getProperty("i0001",LanguageActivity.languageCode+"_message.properties",getContext()), new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    QuestionContent questionContent = (QuestionContent) data;
                                    callWebService(questionContent.getQuestionID());
                                }
                            })
                            .setNegativeButton(PropertiesUtil.getProperty("i0002",LanguageActivity.languageCode+"_message.properties",getContext()), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    try {
                        AlertDialog alert = builder.create();
                        alert.show();
                        Button positiveButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                        if (positiveButton != null) {
                            positiveButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_background));
                        }
                        if (negativeButton != null) {
                            negativeButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.alertdialog_background));
                        }
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(20, 0, 20, 0);
                        negativeButton.setLayoutParams(params);
                        positiveButton.setLayoutParams(params);
                    } catch (Exception e) {
                        System.out.println("display dialog error");
                    }
                }
            });
    }

    private void callWebService(int questionID) {
        showProgressDialog();
        final SendQuestionService loginService = ServiceFactory.getService(SendQuestionService.class);
        loginService.getAccessToken(accessToken,questionID,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SendQuestionWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert("Connection Timeout");
                    }

                    @Override
                    public void onNext(final SendQuestionWrapper sendQuestionWrapper) {
                        dismissProgressDialog();

                        if (sendQuestionWrapper.getMeta().getCode() != null && !sendQuestionWrapper.getMeta().getCode().isEmpty()) {
                            Toast.makeText(getContext(),"Question has been sent",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),"Fail ..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
