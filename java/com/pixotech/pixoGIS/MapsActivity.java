package com.pixotech.pixoGIS;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/*  If you're a developer, and want to run this app, you will need to add-in your own API Key, as
    well as the SHA-1 signature generated w/the application.
     -you'll need to add this information to the application in your Google APIs & Services section
      of the actual website for your Google APIs.

     -you may also want to restrict the usage of where your API key can be used, but this is up to you.
 */



public class MapsActivity extends AppCompatActivity
    implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        // This is for the "U of I Sustainable Student Farm"; it will be the top right corner of the polyline box
        LatLng sfarm = new LatLng(40.08327, -88.2189);
        googleMap.addMarker(new MarkerOptions().position(sfarm).title("U of I Sustainable Student Farm"));

        LatLng topleft = new LatLng(40.083283, -88.220938);
        LatLng btmleft = new LatLng(40.081735, -88.220971);
        LatLng btmright = new LatLng(40.081743, -88.218986);
        LatLng topright = new LatLng(40.08327, -88.2189);
        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .width(9)
                .color(Color.RED)
                .add(topright, btmright, btmleft, topleft, topright));
                polyline.setTag("pixotech");

         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.083, -88.218), 16));
        googleMap.setOnPolylineClickListener(this);
    }

        private static final PatternItem DOT = new Dot();
        private static final PatternItem GAP = new Gap(9);
        private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
        @Override
        public void onPolylineClick(Polyline polyline) {
                if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
                    polyline.setPattern(PATTERN_POLYLINE_DOTTED);
                }
                else {
                    polyline.setPattern(null);
                }
                Toast.makeText(this, "Well Hello there " + Objects.requireNonNull(polyline.getTag()).toString(), Toast.LENGTH_SHORT).show();
    }
}