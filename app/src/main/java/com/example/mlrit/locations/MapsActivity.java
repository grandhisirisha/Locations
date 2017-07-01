package com.example.mlrit.locations;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText et;
    Button b;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        et=(EditText)findViewById(R.id.et);
        b=(Button)findViewById(R.id.b);
        
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=et.getText().toString();

                if (s!=null || !s.equals(""))
                {
                    Geocoder g=new Geocoder(MapsActivity.this);
                    try {
                        List<Address> addressList = g.getFromLocationName(s,1);
                        Address adr=addressList.get(0);
                        LatLng ll=new LatLng(adr.getLatitude(),adr.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(ll).title("Marker"+s));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }

                Toast.makeText(MapsActivity.this,s,Toast.LENGTH_SHORT).show();
            }

        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(20, 78);
        mMap.addMarker(new MarkerOptions().position(sydney).title("INDIA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
