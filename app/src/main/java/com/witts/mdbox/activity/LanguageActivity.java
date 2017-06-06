package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.LanguageAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.WelcomeMessage;
import com.witts.mdbox.model.WelcomeMessageWrapper;
import com.witts.mdbox.service.WelcomeService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LanguageActivity extends BasedActivity implements ItemClickListener<WelcomeMessage> {
    @BindView(R.id.rvChooseLanguage)
    RecyclerView rvChooseLanguage;

    LanguageAdapter languageAdapter;
    List<WelcomeMessage> welcomeMessageList = new ArrayList<>();
    WelcomeMessage welcomeMessage;

    private String accessToken= Constant.ACCESS_TOKEN;
    private String languageCode="jp";
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    private String key="welcomeMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);

        welcomeMessage = new WelcomeMessage();
        welcomeMessage.setDisplayLanguageName("English");
        welcomeMessage.setWelcomeMessage("Welcome To Hotel Sakura");
        welcomeMessageList.add(welcomeMessage);
        welcomeMessageList.add(welcomeMessage);
        welcomeMessageList.add(welcomeMessage);
        welcomeMessageList.add(welcomeMessage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        languageAdapter = new LanguageAdapter(getApplicationContext(), welcomeMessageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvChooseLanguage.setLayoutManager(layoutManager);
        rvChooseLanguage.setHasFixedSize(true);
        rvChooseLanguage.setAdapter(languageAdapter);
        languageAdapter.setItemClickListener(LanguageActivity.this);


//        final WelcomeService welcomeService = ServiceFactory.getService(WelcomeService.class);
//        welcomeService.hotelLanguageSettingList(accessToken,languageCode,key,date,time,timezone,channel,clientVersion,versionNo)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<WebServiceResult<WelcomeMessageWrapper>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        dismissProgressDialog();
//
//                        showAlert(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(final WebServiceResult<WelcomeMessageWrapper> welcomeMessageWrapperWebServiceResult) {
//                        welcomeMessageList.add(welcomeMessage);
//                        if (welcomeMessageWrapperWebServiceResult != null) {
//                            welcomeMessageList = welcomeMessageWrapperWebServiceResult.getResponse().getWelcomeMessageList();
//
//                            languageAdapter = new LanguageAdapter(getApplicationContext(), welcomeMessageList);
//
//                            rvChooseLanguage.setHasFixedSize(true);
//                            rvChooseLanguage.setAdapter(languageAdapter);
//                            languageAdapter.setItemClickListener(LanguageActivity.this);
//                        }
//
//                        dismissProgressDialog();
//                    }
//                });
    }

    @Override
    public void onItemClick(int position, WelcomeMessage welcomeMessage) {
        Intent intent = new Intent(LanguageActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
