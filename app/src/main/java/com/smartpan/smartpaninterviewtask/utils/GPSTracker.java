package com.smartpan.smartpaninterviewtask.utils;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.smartpan.smartpaninterviewtask.R;


public class GPSTracker extends Service implements LocationListener {

    private Context mContext;

    //    public static boolean isFromSetting = false;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location, gps_loc, net_loc; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100; // 10


    // The  minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 0; // 1000 * 60 * 11 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public GPSTracker() {
        mContext = this;
    }

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
//            assert locationManager != null;
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                if (ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                // First get location from Network Provider
                if (isNetworkEnabled) {

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        net_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (net_loc != null) {
                            latitude = net_loc.getLatitude();
                            longitude = net_loc.getLongitude();
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    Log.d("GPS Enabled", "GPS Enabled");
                    if (locationManager != null) {
                        gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (gps_loc != null) {
                            latitude = gps_loc.getLatitude();
                            longitude = gps_loc.getLongitude();
                        }
                    }
                }

                if (gps_loc != null && net_loc != null) {
////                     I used this just to get an idea (if both avail, its upto you which you want to take as I've taken
////                        location with more accuracy)
//                    //smaller the number more accurate result will

                    if (gps_loc.getAccuracy() > net_loc.getAccuracy())
                        location = net_loc;
                    else
                        location = gps_loc;


//
                } else {
                    if (isNetworkEnabled && net_loc != null)
                        location = net_loc;
                    if (isGPSEnabled && gps_loc != null)
                        location = gps_loc;
                }

                if (location != null && location.getLatitude() != 0 && location.getLongitude() != 0)
                    this.canGetLocation = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */

    public void stopUsingGPS() {

        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    /**
     * Function to get latitude
     */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public boolean knowIfGPSEnabled() {
        if (isGPSEnabled || isNetworkEnabled)
            return true;
        else
            return false;

    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     */
    public void showSettingsAlert() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            // Setting Dialog Title
            alertDialog.setTitle("GPS");

            // Setting Dialog Message
            alertDialog.setMessage(mContext.getString(R.string.gps_enable));

            // On pressing Settings button
            alertDialog.setPositiveButton(mContext.getString(R.string.ok),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mContext.startActivity(intent);
                        }
                    });

            // on pressing cancel button
            alertDialog.setNegativeButton(mContext.getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                            isFromSetting = true;
                            dialog.cancel();

                        }
                    });

            // Showing Alert Message
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//        if (location.getLatitude() != 0 && location.getLongitude() != 0)
//            this.canGetLocation = true;
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}