package com.witts.mdbox.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.witts.mdbox.R;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.fragments.ContactReceptionFragment;
import com.witts.mdbox.model.CategoryAttributes;
import com.witts.mdbox.model.QuestionCategory;
import com.witts.mdbox.model.QuestionWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.QuestionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContactReceptionActivity extends BasedActivity {

    @BindView(R.id.tlreceptioncontacttype)
    TabLayout tlreceptioncontacttype;
    @BindView(R.id.vprecptioncontacttype)
    ViewPager vprecptioncontacttype;
    @BindView(R.id.ivback)
    ImageView ivback;

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode=LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    List<QuestionCategory> questionCategoryList;
    public List<String> categoryTabTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_reception);
        ButterKnife.bind(this);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
    }

    @Override
    protected void onResume() {
        super.onResume();
        callWebService();

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ivback.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivback.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.focus_background));
                } else
                {
                    ivback.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.backarrow));
                }
            }
        });
    }

    private void callWebService() {
        showProgressDialog();
        final QuestionService questionListService = ServiceFactory.getService(QuestionService.class);
        questionListService.questions(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<QuestionWrapper>>() {
                    @Override
                    public void onCompleted() {
                        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
                        vprecptioncontacttype.setAdapter(pagerAdapter);
                        vprecptioncontacttype.clearOnPageChangeListeners();
                        vprecptioncontacttype.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlreceptioncontacttype));
                        tlreceptioncontacttype.setTabsFromPagerAdapter(pagerAdapter);
                        tlreceptioncontacttype.post(new Runnable() {
                            @Override
                            public void run() {
                                tlreceptioncontacttype.setupWithViewPager(vprecptioncontacttype);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        Toast.makeText(getApplicationContext(),"Fail..",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(final WebServiceResult<QuestionWrapper> qaListWrapperWebServiceResult) {
                        dismissProgressDialog();
                        Toast.makeText(getApplicationContext(),"Success..",Toast.LENGTH_SHORT).show();
                        questionCategoryList = new ArrayList<QuestionCategory>();
                        questionCategoryList = qaListWrapperWebServiceResult.getResponse().getQuestionCategoryList();
                        int size = qaListWrapperWebServiceResult.getResponse().getQuestionCategoryList().size();
                        categoryTabTitle = new ArrayList<String>();
                        for (int i = 0;i < size ; i ++)
                        {
                            if(qaListWrapperWebServiceResult.getResponse().getQuestionCategoryList().get(i).getPublishInd().equals("Y")) { //here to change
                                List<CategoryAttributes> categoryAttributesList = new ArrayList<CategoryAttributes>();
                                categoryAttributesList = qaListWrapperWebServiceResult.getResponse().getQuestionCategoryList().get(i).getCategoryAttributes();
                                for (int j = 0; j < categoryAttributesList.size(); j++)
                                {
                                    if(categoryAttributesList.get(j).getLanguageCode().equals(LanguageActivity.languageCode))
                                        categoryTabTitle.add(categoryAttributesList.get(j).getName());
                                }
                            }
                        }
                    }
                });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = categoryTabTitle.size();
        private String tabTitles[] = new String[] { "Room Service", "Hotel Restaurant", "Other Service"};
        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            ContactReceptionFragment fragment;
            for (int i = 0; i < categoryTabTitle.size(); i++){
                if( i == position){
                    fragment = ContactReceptionFragment.newInstance(questionCategoryList.get(i).getQuestionSubCategoryList());
                    return fragment;
                }
            }
//            switch (position) {
//                case 0:
//                    fragment = ContactReceptionFragment.newInstance("Room Service");
//                    return fragment;
//                case 1:
//                    fragment = ContactReceptionFragment.newInstance("Hotel Restaurant");
//                    return fragment;
//                case 2:
//                    fragment = ContactReceptionFragment.newInstance("Other Service");
//                    return fragment;
//            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categoryTabTitle.get(position);
        }
    }
}
