package com.fit5046.calorietrackerapp;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fit5046.commonTools.SearchMapAPI;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    ArrayList<String> latList = new ArrayList<>();
    ArrayList<String> lngList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();

    public MapsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_page, container, false);
        ShowNearbyParks showNearbyParks = new ShowNearbyParks();
        showNearbyParks.execute();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("geocode",0);
        Double lat = Double.valueOf(sharedPreferences.getString("latitude",String.valueOf(0)));
        Double lng = Double.valueOf(sharedPreferences.getString("longitude",String.valueOf(0)));
        LatLng home = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(home).title("Home"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 10));
    }

    private class ShowNearbyParks extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("geocode", 0);
            Double lat = Double.valueOf(sharedPreferences.getString("latitude",String.valueOf(0)));
            Double lng = Double.valueOf(sharedPreferences.getString("longitude",String.valueOf(0)));
            String result = SearchMapAPI.showNearParks(lat,lng);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray addressList = jsonObject.getJSONArray("results");
                if (jsonObject != null && addressList.length()>0) {
                    for (int i = 0;i<addressList.length();i++) {
                        latList.add(i,((JSONArray)jsonObject.get("results"))
                                .getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lat").toString());
                        lngList.add(i,((JSONArray)jsonObject.get("results"))
                                .getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lng").toString());
                        nameList.add(i,((JSONArray)jsonObject.get("results"))
                                .getJSONObject(i).getString("name"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  "ok";
        }
        @Override
        protected void onPostExecute(String result) {
            for (int i = 0; i < latList.size(); i++) {
                LatLng marker = new LatLng(Double.valueOf(latList.get(i)), Double.valueOf(lngList.get(i)));
                mMap.addMarker(new MarkerOptions().position(marker).title(nameList.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }
        }
    }
}
