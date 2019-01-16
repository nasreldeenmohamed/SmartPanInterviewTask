package com.smartpan.smartpaninterviewtask.modules.main.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smartpan.smartpaninterviewtask.R;
import com.smartpan.smartpaninterviewtask.utils.GPSTracker;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap googleMap;
    private List<Marker> AllLocationsMarkers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(this);
        mMapView = getActivity().findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

//        addMyLocationMarker();
//        addMallsLocationMarkers();
    }

    private void addMallsLocationMarkers() {
        LatLng coordinate1 = new LatLng(30.073313, 31.346018);
        Marker marker1 = googleMap.addMarker(new MarkerOptions().position(coordinate1)
                .title("")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        AllLocationsMarkers.add(marker1);

        LatLng coordinate2 = new LatLng(30.073313, 31.346018);
        Marker marker2 = googleMap.addMarker(new MarkerOptions().position(coordinate2)
                .title("")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        AllLocationsMarkers.add(marker2);

        LatLng coordinate3 = new LatLng(30.073313, 31.346018);
        Marker marker3 = googleMap.addMarker(new MarkerOptions().position(coordinate3)
                .title("")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        AllLocationsMarkers.add(marker3);
    }

    private void addMyLocationMarker() {
        GPSTracker gpsTracker = new GPSTracker(getActivity());
        MarkerOptions MyMarkerOptions = new MarkerOptions()
                .position(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()))
                .title(getString(R.string.my_location))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        Marker MyMarker = googleMap.addMarker(MyMarkerOptions);
        AllLocationsMarkers.add(MyMarker);

        zoomOnAllMarkers();
    }


    public void zoomOnAllMarkers() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : AllLocationsMarkers) {
            builder.include(marker.getPosition());
        }
        final LatLngBounds bounds = builder.build();
        int padding = 10; // offset from edges of the map in pixels
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 30));
                googleMap.animateCamera(cu);
            }
        });


    }
}
