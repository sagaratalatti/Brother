package com.android.brother.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.brother.R;
import com.android.brother.entities.RushEvent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 22-06-2017.
 */

public class MapActivity extends BaseActivity {

    @BindView(R.id.map_rush_name)
    TextView rushName;
    @BindView(R.id.map_rush_date)
    TextView rushDate;
    @BindView(R.id.map_description)
    TextView rushDescription;
    @BindView(R.id.map_rush_time)
    TextView rushTime;
    @BindView(R.id.map_description1)
    TextView rushDescription1;
    @BindView(R.id.map_rush_info_layout)
    LinearLayout mapInfoLayout;

    private GoogleApiClient mClient;
    private GoogleMap map;
    private RushEvent rushEvent;
    public static final String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        rushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);

        rushName.setText(rushEvent.getEventName());
        rushDate.setText(rushEvent.getEventDate());
        rushTime.setText(rushEvent.getEventTime());
        rushDescription.setText(rushEvent.getEventLocation());
        rushDescription1.setText(rushEvent.getEventDescription());

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        updateUI();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                }).build();

        SupportMapFragment supportMapFragment =  (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });
    }

    private void updateUI(){
        LatLng rushEventPoint  = new LatLng(rushEvent.getEventLatitude(), rushEvent.getEventLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(rushEventPoint)
                .title("Rush Event Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        map.clear();
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(rushEventPoint, 15));
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(RUSH_EVENT_INFO, rushEvent);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClient.disconnect();
    }
}
