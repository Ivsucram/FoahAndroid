package com.obdo.controllers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * This class gets the location of the device, either by GPS or by network
 * @author Oscar Juarez
 * @since 12/14/2014
 * @version 1.0
 */
public class LocationController {

    /** currentLocation stores the device's current (last know) location
     * @since 12/14/2014
     * @see com.google.android.gms.maps.model.LatLng
     */
    private LatLng currentLocation = new LatLng(0,0);

    /** map is the Google map object to hold the map
     * @since 12/14/2014
     * @see com.google.android.gms.maps.GoogleMap
     */
    private GoogleMap map;

    /** locationManager used to implement the LocationManager
     * @since 12/14/2014
     * @see android.location.LocationManager
     */
    private LocationManager locationManager;

    /** locationListener used to implement the LocationListener to receive updates of device's location
     * @since 12/14/2014
     * @see android.location.LocationListener
     */
    private LocationListener locationListener;

    /** context used to receive the context of the activity implementing invoking this class
     * @since 12/14/2014
     * @see android.content.Context
     */
    final Context context;

    public LocationController(Context _context, GoogleMap _map) {

        context = _context;
        map = _map;

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = InitializeLocationListener();

        //There are two ways to obtain the device's location, by GPS_PROVIDER or by NETWORK_PROVIDER
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    /** Initializes the locationListener and implements required methods
     * @since 12/14/2014
     * @return a new location listener implementing the required methods
     */
    private LocationListener InitializeLocationListener() {
        return new LocationListener() {
            public void onLocationChanged(Location location) {
                //Toast.makeText(context, "New Location detected!", Toast.LENGTH_SHORT).show();

                currentLocation = new LatLng(location.getLatitude(),location.getLongitude());

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                //Dont need to show a marker on user's location
                //map.addMarker(new MarkerOptions().position(currentLocation).title("Current location"));
                //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                //DO SOMETHING
            }

            public void onProviderEnabled(String provider) {
                //DO SOMETHING
            }

            public void onProviderDisabled(String provider) {
                //Toast.makeText(context,"GPS/Use Wireless network is not enabled" ,Toast.LENGTH_SHORT).show();
            }
        };

    }
}
