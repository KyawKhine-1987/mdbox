package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.adapter.CommonTypeChoiceAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.Souvenir;
import com.witts.mdbox.model.SouvenirCategory;
import com.witts.mdbox.model.SouvenirDetail;
import com.witts.mdbox.model.SouvenirImageandDetail;
import com.witts.mdbox.model.SouvenirListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.SouvenirService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SouvenirDetailFragment extends BaseFragment {
    private static final String ARG_SOUVENIRATTR = "param1";
    private int mSouvenirAttr;

    @BindView(R.id.rvchoosesouvenirtype)
    RecyclerView rvchoosesouvenirtype;

    @BindView(R.id.imguparrow)
    ImageView imguparrow;

    @BindView(R.id.imgdownarrow)
    ImageView imgdownarrow;

    @BindView(R.id.llinfocontainer)
    LinearLayout llinfocontainer;

    @BindView(R.id.svinfocontainer)
    ScrollView svinfocontainer;

    @BindView(R.id.ivDetailImageContainer)
    ImageView ivDetailImageContainer;

    @BindView(R.id.tvsouvenir)
    TextView tvsouvenir;

    @BindView(R.id.tvlabel)
    TextView tvlabel;

    Souvenir souvenir = new Souvenir();
    List<Souvenir> souvenirList = new ArrayList<>();

    private String accessToken= LanguageActivity.ACCESSTOKEN;
    private String languageCode= LanguageActivity.languageCode;
    private String date="";
    private String time="";
    private String timezone="UTC";
    private String channel="WEB";
    private String clientVersion="1.0";
    private String versionNo="0001";

    private List<String> imageList = new ArrayList<>();
    List<SouvenirDetail> souvenirDetailList = new ArrayList<>();
    CommonTypeChoiceAdapter commonTypeChoiceAdapter;
    LinearLayoutManager layoutManager;
    List<SouvenirImageandDetail> souvenirImageandDetailList = new ArrayList<>();

    public SouvenirDetailFragment() {
        // Required empty public constructor
    }


    public static SouvenirDetailFragment newInstance(int param1) {
        SouvenirDetailFragment fragment = new SouvenirDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SOUVENIRATTR, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSouvenirAttr = getArguments().getInt(ARG_SOUVENIRATTR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_souvenir_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

        callWebService();
    }

    private void callWebService() {
        showProgressDialog();
        final SouvenirService souvenirService = ServiceFactory.getService(SouvenirService.class);
        souvenirService.souvenirList(accessToken,languageCode,date,time,timezone,channel,clientVersion,versionNo,mSouvenirAttr)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<SouvenirListWrapper>>() {
                    @Override
                    public void onCompleted() {
                        prepareandbindData(souvenirList);
                        commonTypeChoiceAdapter = new CommonTypeChoiceAdapter(getContext(), imageList);
                        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
                        rvchoosesouvenirtype.setLayoutManager(layoutManager);
                        rvchoosesouvenirtype.setHasFixedSize(true);
                        commonTypeChoiceAdapter.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemClick(int position, Object data) {
                                if(imageList.get(position) != null && !imageList.get(position).equals("")) {
                                    String imageapi = Constant.IMAGE_UPLOAD_URL + imageList.get(position)+"/?accessToken="+accessToken+"&date="+date+"&" +
                                            "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";

                                    Glide.with(getContext())
                                            .load(imageapi)
                                            .placeholder(R.drawable.spinner_of_dots)
                                            .error(R.drawable.error_icon)
                                            .into(ivDetailImageContainer);
                                }
                                StringBuilder souvenirString=new StringBuilder();
                                for (int i=0;i<souvenirImageandDetailList.get(position).getSouvenirDetailList().size();i++) {
                                    souvenirString.append(souvenirImageandDetailList.get(position).getSouvenirDetailList().get(i).getDisplayName() + " :" +
                                            souvenirImageandDetailList.get(position).getSouvenirDetailList().get(i).getValue() + " " + souvenirImageandDetailList.get(position).getSouvenirDetailList().get(i).getUnit()+"\n");
                                }
                                tvlabel.setText(souvenirImageandDetailList.get(0).getSouvenirDetailList().get(0).getDisplayName());
                                tvsouvenir.setText(souvenirString);
                            }
                        });
                        rvchoosesouvenirtype.setAdapter(commonTypeChoiceAdapter);

                        String imageapi =  Constant.IMAGE_UPLOAD_URL + imageList.get(0)+"/?accessToken="+accessToken+"&date="+date+"&" +
                                "time="+time+"&timezone="+timezone+"&channel="+channel+"&clientVersion="+clientVersion+"&versionNo="+versionNo+"&name=image";
                            Glide.with(getContext())
                                    .load(imageapi)
                                    .placeholder(R.drawable.spinner_of_dots)
                                    .error(R.drawable.error_icon)
                                    .into(ivDetailImageContainer);

                        StringBuilder souvenirString=new StringBuilder();
                        for (int i=1;i<souvenirImageandDetailList.get(0).getSouvenirDetailList().size();i++) {
                            souvenirString.append(souvenirImageandDetailList.get(0).getSouvenirDetailList().get(i).getDisplayName() + " :" +
                                    souvenirImageandDetailList.get(0).getSouvenirDetailList().get(i).getValue() + " " + souvenirImageandDetailList.get(0).getSouvenirDetailList().get(i).getUnit()+"\n");
                        }
                        tvlabel.setText(souvenirImageandDetailList.get(0).getSouvenirDetailList().get(0).getDisplayName());
                        tvsouvenir.setText(souvenirString);
                        dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        showAlert("Connection Timeout");
                    }

                    @Override
                    public void onNext(final WebServiceResult<SouvenirListWrapper> souvenirListWrapperWebServiceResult) {
                        if (souvenirListWrapperWebServiceResult != null) {
                            if(souvenirListWrapperWebServiceResult.getResponse().getSouvenirCategoryList().size()>0)
                            for(int i=0;i<souvenirListWrapperWebServiceResult.getResponse().getSouvenirCategoryList().size();i++){
                                SouvenirCategory souvenirCategory = souvenirListWrapperWebServiceResult.getResponse().getSouvenirCategoryList().get(i);
                                souvenirList = new ArrayList<Souvenir>();
                                if(souvenirCategory.getSouvenirList().size()>0)
                                for(int j=0;j<souvenirCategory.getSouvenirList().size();j++)
                                {
                                    souvenir = new Souvenir();
                                    souvenir = souvenirCategory.getSouvenirList().get(j);
                                    souvenirList.add(souvenir);
                                }
                            }
                        }

                        dismissProgressDialog();
                    }
                });
    }

    private void prepareandbindData(List<Souvenir> souvenirList) {
        imageList = new ArrayList<>();
        souvenirImageandDetailList =new ArrayList<>();
        if(souvenirList.size()>0)
        for(int i=0;i<souvenirList.size();i++)
        {
            souvenirDetailList = new ArrayList<>();

            if(souvenirList.get(i).getGroupList().size()>0)
            for(int j=0;j<souvenirList.get(i).getGroupList().size();j++) {
                SouvenirDetail souvenirDetail = new SouvenirDetail();
                souvenirDetail.setDisplayName(souvenirList.get(i).getGroupList().get(j).getAttributeList().get(0).getDisplayName());
                souvenirDetail.setUnit(souvenirList.get(i).getGroupList().get(j).getAttributeList().get(0).getUnit());
                souvenirDetail.setValue(souvenirList.get(i).getGroupList().get(j).getAttributeList().get(0).getValue());
                souvenirDetailList.add(souvenirDetail);
            }
            SouvenirImageandDetail souvenirImageandDetail = new SouvenirImageandDetail();
            souvenirImageandDetail.setImageUrl(souvenirList.get(i).getImagePath());
            souvenirImageandDetail.setSouvenirDetailList(souvenirDetailList);
            souvenirImageandDetailList.add(souvenirImageandDetail);
            imageList.add(souvenirList.get(i).getImagePath());
        }
    }
}
