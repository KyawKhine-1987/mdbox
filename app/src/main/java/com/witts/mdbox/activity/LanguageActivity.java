package com.witts.mdbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.LanguageAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final WelcomeService welcomeService = ServiceFactory.getService(WelcomeService.class);
        welcomeService.hotelLanguageSettingList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<WelcomeMessageWrapper>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();

                        showAlert(e.getMessage());
                    }

                    @Override
                    public void onNext(final WebServiceResult<WelcomeMessageWrapper> welcomeMessageWrapperWebServiceResult) {
                        if (welcomeMessageWrapperWebServiceResult != null) {
                            welcomeMessageList = welcomeMessageWrapperWebServiceResult.getResponse().getWelcomeMessageList();

                            languageAdapter = new LanguageAdapter(getApplicationContext(), welcomeMessageList);

                            rvChooseLanguage.setHasFixedSize(true);
                            rvChooseLanguage.setAdapter(languageAdapter);
                            languageAdapter.setItemClickListener(LanguageActivity.this);
                        }

                        dismissProgressDialog();
                    }
                });
    }

    @Override
    public void onItemClick(int position, WelcomeMessage welcomeMessage) {
        Intent intent = new Intent(LanguageActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
