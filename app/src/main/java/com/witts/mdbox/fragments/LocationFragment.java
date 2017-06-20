package com.witts.mdbox.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.witts.mdbox.R;
import com.witts.mdbox.activity.LanguageActivity;
import com.witts.mdbox.adapter.LocationAdapter;
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

public class LocationFragment extends BaseFragment implements OnMapReadyCallback {

    private static final String LOG_TAG = LocationFragment.class.getName();

    //There are recyclerView and textView which is binding relevant data from API-2.
    @BindView(R.id.rvLocationNameList)
    RecyclerView rvLocationNameList;

    @BindView(R.id.tvPostalCode)
    TextView tvPostalCode;

    @BindView(R.id.tvPlaceName)
    TextView tvPlaceName;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @BindView(R.id.tvURL)
    TextView tvURL;

    @BindView(R.id.tvDesp)
    TextView tvDesp;

    //Declare locationAdapter, locationList and location each arrayList objects.
    LocationAdapter locationAdapter;

    List<LocationList> locationList = new ArrayList<>();

    List<String> location = new ArrayList<>();

    //Declare view inflate of rootView and static int of GPS_ERRORDIALOG_REQUEST.
    private View rootView; //not need static
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;

    //Declare we used to GoogleMap, passed through with eight arguments and which is binding relevant data from API-2.
    private String accessToken = LanguageActivity.ACCESSTOKEN;
    private String languageCode = LanguageActivity.languageCode;
    private String date = "";
    private String time = "";
    private String timezone = "UTC";
    private String channel = "WEB";
    private String clientVersion = "1.0";
    private String versionNo = "0001";

    GoogleMap mGoogleMap;
    String mLocality, mPostalCode, mPlaceName, mAddress, mPhone, mURL, mDesp;
    int locationCategoryId;
    double api1_Lat, api1_Lng, api2_Lat, api2_Lng, api2_ZoomLat, api2_ZoomLng;
    float api1_Zoom, api2_Zoom;

    //Constructor
    public LocationFragment() {
        Log.i(LOG_TAG, "TEST: LocationFragment() called...");

    }

    //There're locationId, Latitude, Longitude and ZoomLevel passed through the relevant data from LocationActivity.java.
    public static LocationFragment newInstance(int locationId, double Lat, double Lng, float zoom) {
        LocationFragment locationFragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putInt("locationCategoryId", locationId);
        args.putDouble("latitude", Lat);
        args.putDouble("longitude", Lng);
        args.putFloat("zoomLevel", zoom);
        locationFragment.setArguments(args);
        return locationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "TEST: onCreate() called...");

        //Declare SimpleDateFormat in Date and Time for custom format and reduce from six hours to system time.
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hourformat = new SimpleDateFormat("kkmmss");
        date = dateformat.format(new Date(System.currentTimeMillis() - 21600000));
        time = hourformat.format(new Date(System.currentTimeMillis() - 21600000));

        //Check the arguments if you didn't get any value from LocationActivity.java then you won't get next step.
        if (getArguments() != null) {
            locationCategoryId = getArguments().getInt("locationCategoryId");
            api1_Lat = getArguments().getDouble("latitude");
            api1_Lng = getArguments().getDouble("longitude");
            api1_Zoom = getArguments().getFloat("zoomLevel");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreateView() called...");

        //Check the view inflate of rootView which isn't equal null then go to next condition and if rootView isn't equal null then remove your ViewGroup or parent.
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();

            if (parent != null) {
                parent.removeView(rootView);
            }
        } else {
            try {
                //If not view inflate of rootView will inflate your fragment_location layout then will do underneath instructions and return.
                rootView = inflater.inflate(R.layout.fragment_location, container, false);
                ButterKnife.bind(this, rootView);

                //Check the google play services and the supportMapFragment for the GoogleMap.
                if (servicesOK() && initializeMap()) {
                    Toast.makeText(getContext(), "Ready to Map!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Map not available!", Toast.LENGTH_SHORT).show();
                }

            } catch (InflateException e) {
                Log.e(LOG_TAG, "Problem making the SupportMapFragment.", e);
            }
        }

        tvPostalCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvPostalCode.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvPostalCode.setBackgroundResource(0);
                }
            }
        });

        tvPlaceName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvPlaceName.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvPlaceName.setBackgroundResource(0);
                }
            }
        });

        tvAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvAddress.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvAddress.setBackgroundResource(0);
                }
            }
        });

        tvPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvPhone.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvPhone.setBackgroundResource(0);
                }
            }
        });

        tvURL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvURL.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvURL.setBackgroundResource(0);
                }
            }
        });

        tvDesp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    tvDesp.setBackground(getContext().getResources().getDrawable(R.drawable.selected_background));
                }else{
                    tvDesp.setBackgroundResource(0);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onViewCreated() called...");

        super.onViewCreated(view, savedInstanceState);

        //Call the webService
        callWebService();
    }

    private void callWebService() {
        Log.i(LOG_TAG, "TEST: callWebService() called...");

        //Call the interface of LocationCategoryListService class with relevant nine arguments.
        final LocationCategoryListService locationCategoryListService = ServiceFactory.getService(LocationCategoryListService.class);
        locationCategoryListService.locationList(accessToken, languageCode, date, time, timezone, channel, clientVersion, versionNo, locationCategoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WebServiceResult<LocationMap>>() {

                    @Override
                    public void onCompleted() {
                        Log.i(LOG_TAG, "TEST: onCompleted() called...");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(LOG_TAG, "TEST: onError() called...");

                        dismissProgressDialog();

                        //If the connection is break down then set the Toast message show with Fail.
                        Toast.makeText(getContext(), "Failed..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(final WebServiceResult<LocationMap> locationMapWebServiceResult) {
                        Log.i(LOG_TAG, "TEST: onNext() called...");

                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Successful..", Toast.LENGTH_SHORT).show();

                        location = new ArrayList<>();
                        locationList = new ArrayList<LocationList>();
                        locationList = locationMapWebServiceResult.getResponse().getLocationList();

                        //Fetch the relevant data from API-2 then called the goToLocation with three arguments.
                        for (int i = 0; i < locationList.size(); i++) {
                            if (locationMapWebServiceResult.getResponse().getLocationList().get(i).getPublishInd().equals("Y")) {
                                location.add(locationMapWebServiceResult.getResponse().getLocationList().get(i).getLocationName());
                                mLocality = locationMapWebServiceResult.getResponse().getLocationList().get(i).getLocationName();
                                api2_Lat = locationMapWebServiceResult.getResponse().getLocationList().get(i).getLatitude();
                                api2_Lng = locationMapWebServiceResult.getResponse().getLocationList().get(i).getLongitude();

                                goToLocation(api2_Lat, api2_Lng, 0);
                            }
                        }

                        //Called the goToLocation with three arguments.
                        goToLocation(api1_Lat, api1_Lng, api1_Zoom);

                        //Set the location object to locationAdapter constructor method for LocationAdapter.java Class.
                        locationAdapter = new LocationAdapter(getContext(), location);

                        //Assign the color, divider, left and right margins for that RecyclerView.
                        rvLocationNameList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                                .color(R.color.grey)
                                .sizeResId(R.dimen.divider)
                                .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                                .build());

                        //Set the RecyclerView with this below methods which are setLayoutManager, setHasFixedSize, setAdapter and also implement onItemClick method for rvLocationNameList.
                        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
                        rvLocationNameList.setLayoutManager(llManager);
                        rvLocationNameList.setHasFixedSize(true);
                        rvLocationNameList.setAdapter(locationAdapter);
                        locationAdapter.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemClick(int position, Object data) {
                                Log.i(LOG_TAG, "TEST: onItemClick() called...");

                                //Fetch the relevant data from API-2 then called the goToLocation with three arguments and also called the binding data method.
                                mPostalCode = locationMapWebServiceResult.getResponse().getLocationList().get(position).getPostalCode();
                                mPlaceName = locationMapWebServiceResult.getResponse().getLocationList().get(position).getPlaceName();
                                mAddress = locationMapWebServiceResult.getResponse().getLocationList().get(position).getAddress();
                                mPhone = locationMapWebServiceResult.getResponse().getLocationList().get(position).getTelephone();
                                mURL = locationMapWebServiceResult.getResponse().getLocationList().get(position).getUrl();
                                mDesp = locationMapWebServiceResult.getResponse().getLocationList().get(position).getDescription();

                                api2_ZoomLat = locationMapWebServiceResult.getResponse().getLocationList().get(position).getLatitude();
                                api2_ZoomLng = locationMapWebServiceResult.getResponse().getLocationList().get(position).getLongitude();
                                api2_Zoom = locationMapWebServiceResult.getResponse().getLocationList().get(position).getZoomLevel();

                                goToLocation(api2_ZoomLat, api2_ZoomLng, api2_Zoom);

                                //This method is included textview which is binding relevant data from API-2.
                                bindingData();
                            }
                        });
                    }
                });
    }

    public boolean servicesOK() {
        Log.i(LOG_TAG, "TEST : servicesOK() called...");

        //Check the google play services.
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getContext());

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, getActivity(), GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getContext(), "You couldn't connect to Google Play Services.Please, Try again!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private boolean initializeMap() {
        Log.i(LOG_TAG, "TEST : initializeMap() called...");

        //Check the SupportMapFragment of GoogleMap and getMapAsync for that mapFragment in activity_map.xml then return.
        if (mGoogleMap == null) {
            SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
            smf.getMapAsync(this);
        }
        return (mGoogleMap == null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(LOG_TAG, "TEST : onMapReady() called...");

        //Fetch the value and assign to the mGoogleMap.
        mGoogleMap = googleMap;
    }

    private void goToLocation(double lat, double lng, float zoom) {
        Log.i(LOG_TAG, "TEST : gotoLocation() called...");

        //Create the LatLng object and assign cameraUpdate with LatLng and zoom then mark with the relevant data from API-2 attributes. For example latitude, longtidue, zoomLevel and locationName and so on.
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mGoogleMap.moveCamera(cameraUpdate);

        MarkerOptions options = new MarkerOptions()
                .title(mLocality)
                .position(new LatLng(lat, lng));
        mGoogleMap.addMarker(options);
    }

    private void bindingData(){
        Log.i(LOG_TAG, "TEST : bindingData() called...");

        //This is binding the relevant data from API-2.
        tvPostalCode.setText(mPostalCode);
        tvPlaceName.setText(mPlaceName);
        tvAddress.setText(mAddress);
        tvPhone.setText(mPhone);
        tvURL.setText(mURL);
        tvDesp.setText(mDesp);
    }
}
