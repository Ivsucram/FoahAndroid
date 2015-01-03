package com.obdo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.obdo.controllers.HTTPRequestControllerV2;
import com.obdo.controllers.LocationController;

import java.util.ArrayList;
import java.util.List;

/** This layout is used to display posts in a map view, using the device's current location.
 * To obtain the current location we make use of LocationController class
 * @author Oscar Juarez
 * @since 12/14/2014
 * @version 1.0
 * @see com.google.android.gms.maps.GoogleMap
 * @see com.obdo.controllers.LocationController
 */
public class MapActivity extends Activity {

    /** map implements a GoogleMap object to display the map asa fragment in the layout
     * @since 12/14/2014
     * @see com.google.android.gms.maps.GoogleMap
     */
    private GoogleMap map;
    private List<PostModel> posts;


    /**
     * locationController is used to obtain the device's current (last known) location
     * @since 12/14/2014
     * @see com.obdo.controllers.LocationController
     */
    private LocationController locationController;

    private HTTPRequestControllerV2 httpRequestControllerV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        posts = new ArrayList<PostModel>();
        //httpRequestControllerV2 = new HTTPRequestControllerV2(this);
        //httpRequestControllerV2.getPosts();

        locationController = new LocationController(MapActivity.this, map);

        httpRequestControllerV2 = new HTTPRequestControllerV2(this.getApplicationContext());

        httpRequestControllerV2.getPosts();
    }
}
