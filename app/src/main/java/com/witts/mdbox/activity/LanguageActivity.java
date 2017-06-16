package com.witts.mdbox.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.LanguageAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Key;
import com.witts.mdbox.model.Language;
import com.witts.mdbox.model.LoginMACAddressWrapper;
import com.witts.mdbox.model.Message;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.WelcomeMessage;
import com.witts.mdbox.model.WelcomeMessageWrapper;
import com.witts.mdbox.service.LoginService;
import com.witts.mdbox.service.WelcomeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    List<Language> languageList = new ArrayList<>();
    Language language;

    List<Message> messageList = new ArrayList<>();
    Message message;

    List<Key> keyList = new ArrayList<>();
    Key key;

    public static String languageCode ;
    public static String ACCESSTOKEN ;

    private String accessToken= Constant.ACCESS_TOKEN;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";
    private String keys="welcomeMessage,";
    private String versionNoMAC="0002";
    List<String> mykeyList;
    LinearLayoutManager layoutManager;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
        showProgressDialog();
        WifiManager wimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        address = wimanager.getConnectionInfo().getMacAddress();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

        if(!address.equals("")){
            callWebserviceForMAC(address);
        }
        layoutManager = new LinearLayoutManager(this);
        mykeyList = new ArrayList<String>(Arrays.asList(keys.split(",")));

//        welcomeMessage = new WelcomeMessage();
//        welcomeMessage.setDisplayLanguageName("English");
//        welcomeMessage.setWelcomeMessage("Welcome To Hotel Sakura");
//        welcomeMessageList.add(welcomeMessage);
//        welcomeMessageList.add(welcomeMessage);
//        welcomeMessageList.add(welcomeMessage);
//        welcomeMessageList.add(welcomeMessage);
    }

    private void callWebserviceForMAC(String address) {
        final LoginService loginService = ServiceFactory.getService(LoginService.class);
        loginService.getAccessToken(address,date,time,timezone,channel,clientVersion,versionNoMAC)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<LoginMACAddressWrapper>>() {
                    @Override
                    public void onCompleted() {
                        callWebservice();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();

                        showAlert(e.getMessage());
                    }

                    @Override
                    public void onNext(final WebServiceResult<LoginMACAddressWrapper> loginMACAddressWrapperWebServiceResult) {

                        if (loginMACAddressWrapperWebServiceResult != null) {
                            ACCESSTOKEN = loginMACAddressWrapperWebServiceResult.getResponse().getAccessToken();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        languageAdapter = new LanguageAdapter(getApplicationContext(), welcomeMessageList);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvChooseLanguage.setLayoutManager(layoutManager);
//        rvChooseLanguage.setHasFixedSize(true);
//        rvChooseLanguage.setAdapter(languageAdapter);
//        languageAdapter.setItemClickListener(LanguageActivity.this);
    }

    private void callWebservice() {

        final WelcomeService welcomeService = ServiceFactory.getService(WelcomeService.class);
        welcomeService.hotelLanguageSettingList(accessToken,keys,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<WelcomeMessageWrapper>>() {
                    @Override
                    public void onCompleted() {
                        welcomeMessageList = new ArrayList<>();
                        for(int i = 0;i<keyList.size();i++){
                            key = new Key();
                            language = new Language();
                            for(int j=0;j<mykeyList.size();j++) {
                                if (keyList.get(i).getKey().equals(mykeyList.get(j))){
                                    key =keyList.get(i);
                                    for(int k =0;k<keyList.get(i).getMessageList().size();k++)
                                    {
                                        message = new Message();
                                        welcomeMessage = new WelcomeMessage();
                                        message = keyList.get(i).getMessageList().get(k);
                                        welcomeMessage.setWelcomeMessage(message.getMessage());
                                        for(int l = 0;l<languageList.size();l++){
                                            if(languageList.get(l).getDisplayLanguageCode().equals(message.getLanguageCode()) && languageList.get(l).getLanguageCode().equals(message.getLanguageCode())) {
                                                welcomeMessage.setDisplayLanguageName(languageList.get(l).getDisplayName());
                                                welcomeMessage.setLanguageFlagUrl(languageList.get(l).getLanguageFlagUrl());
                                                welcomeMessage.setLanguageCode(message.getLanguageCode());
                                            }
                                        }
                                        welcomeMessageList.add(welcomeMessage);
                                    }
                                }
                            }
                        }
                        languageAdapter = new LanguageAdapter(getApplicationContext(), welcomeMessageList);
                        rvChooseLanguage.setLayoutManager(layoutManager);
                        rvChooseLanguage.setHasFixedSize(true);
                        rvChooseLanguage.setAdapter(languageAdapter);
                        languageAdapter.setItemClickListener(LanguageActivity.this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();

                        showAlert(e.getMessage());
                    }

                    @Override
                    public void onNext(final WebServiceResult<WelcomeMessageWrapper> welcomeMessageWrapperWebServiceResult) {

                        if (welcomeMessageWrapperWebServiceResult != null) {
                            languageList = welcomeMessageWrapperWebServiceResult.getResponse().getLanguageList();
                            keyList = welcomeMessageWrapperWebServiceResult.getResponse().getKeyList();
                        }

                        dismissProgressDialog();
                    }
                });
    }

    @Override
    public void onItemClick(int position, WelcomeMessage welcomeMessage) {
        String selectedLanguageCode = welcomeMessage.getLanguageCode();
        languageCode = selectedLanguageCode;
        Intent intent = new Intent(LanguageActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
