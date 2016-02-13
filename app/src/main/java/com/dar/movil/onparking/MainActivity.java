package com.dar.movil.onparking;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.dar.movil.onparking.util.AppUtil;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener {




    GoogleMap map;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        View v =  View.inflate(this, R.layout.dialog_parking, null);


        dialog =  new AlertDialog.Builder(this)
                .setTitle(AppUtil.ADDRESS)
                .setView(v)
                .create();

        v.findViewById(R.id.opc1).setOnClickListener(this);
        v.findViewById(R.id.opc2).setOnClickListener(this);
        v.findViewById(R.id.opc3).setOnClickListener(this);
        v.findViewById(R.id.opc4).setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        map.setOnMarkerClickListener(this);

        LatLng pos =  new LatLng(AppUtil.LAT, AppUtil.LON);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,18));

        map.addMarker(new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.maker_small)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        dialog.show();
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent =  new Intent(this, TimeActivity.class);
        startActivity(intent);
    }
}
