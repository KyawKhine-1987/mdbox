package com.witts.mdbox.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.witts.mdbox.R;
import com.witts.mdbox.adapter.LocationAdapter;
import com.witts.mdbox.common.Constant;
import com.witts.mdbox.common.ServiceFactory;
import com.witts.mdbox.interfaces.ItemClickListener;
import com.witts.mdbox.model.LocationList;
import com.witts.mdbox.model.LocationMap;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.service.LocationCategoryListService;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

public class LocationFragment extends BaseFragment {
//public class LocationFragment extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG = LocationFragment.class.getName();

    @BindView(R.id.rvLocationNameList)
    RecyclerView rvLocationNameList;

    LocationAdapter locationAdapter;
    List<LocationList> locationList = new ArrayList<>();
    List<String> location = new ArrayList<>();
//    private List<LocationMap> LocationMapList;

    private String accessToken = Constant.ACCESS_TOKEN;
    private String languageCode = "JP";
    private String date = "";
    private String time = "";
    private String timezone = "UTC";
    private String channel = "WEB";
    private String clientVersion = "1.0";
    private String versionNo = "0001";
    private String locationCategoryId = "1";

    public LocationFragment() {
        Log.i(LOG_TAG, "TEST: LocationFragment() called...");

    }

    public static LocationFragment newInstance(int locationId) {
        LocationFragment locationFragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putInt("location", locationId);
        locationFragment.setArguments(args);
        return locationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "TEST: onCreate() called...");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreateView() called...");

        View rootView = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onViewCreated() called...");

        super.onViewCreated(view, savedInstanceState);
        callWebService();
    }

    private void callWebService() {
        Log.i(LOG_TAG, "TEST: callWebService() called...");

        final LocationCategoryListService locationCategoryListService = ServiceFactory.getService(LocationCategoryListService.class);
//        qaListService.qaList(accessToken, languageCode, date, time, timezone, channel, clientVersion, versionNo, locationCategoryId)
        locationCategoryListService.locationList(accessToken, date, time, timezone, channel, clientVersion, versionNo, locationCategoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<LocationMap>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Fail..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(WebServiceResult<LocationMap> locationMapWebServiceResult) {
                        dismissProgressDialog();
                        //Toast.makeText(getContext(), "Success..", Toast.LENGTH_SHORT).show();
                        locationList = new ArrayList<LocationList>();
                        locationList = locationMapWebServiceResult.getResponse().getLocationList();

                        for (int i = 0; i < locationList.size(); i++) {
                            if (locationMapWebServiceResult.getResponse().getLocationList().get(i).getPublishInd().equals("Y")) {
                                location.add(locationMapWebServiceResult.getResponse().getLocationList().get(i).getLocationName());
                            }
                        }

                        locationAdapter = new LocationAdapter(getContext(), location);

                        rvLocationNameList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                        .color(R.color.grey)
                        .sizeResId(R.dimen.divider)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

                        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
                        rvLocationNameList.setLayoutManager(llManager);
                        rvLocationNameList.setHasFixedSize(true);
                        rvLocationNameList.setAdapter(locationAdapter);
                        locationAdapter.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemClick(int position, Object data) {
                                //TODO LIST
                            }
                        });
                    }
                });
    }


}
