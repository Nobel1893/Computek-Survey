package holyquran.cls.com.computek_survey.View;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import holyquran.cls.com.computek_survey.Base.MyBaseFragment;
import holyquran.cls.com.computek_survey.LocationProvider.MyLocationProvider;
import holyquran.cls.com.computek_survey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends MyBaseFragment implements OnMapReadyCallback{

    MapView mMapView;
    private GoogleMap mMap;
    MyLocationProvider myLocationProvider;
    public AboutUsFragment() {
        // Required empty public constructor
    }


    View mainView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_about_us, container, false);
        mMapView =  mainView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        if (CheckPermessoinAllowed()){
            printUserLocation();
        }else {
            RequestLocationPermession();
        }
        return mainView;
    }

    Location myLocation;
    public  void printUserLocation(){
        myLocationProvider = new MyLocationProvider(activity);
        myLocation = myLocationProvider.getLocation();
        if (myLocationProvider.CanGetLocation()){
            Log.e("lat",myLocationProvider.getLatitude()+" ");
            Log.e("long",myLocationProvider.getLongitude()+"");
            LatLng sydney = new LatLng(myLocationProvider.getLatitude(), myLocationProvider.getLongitude());
            if (mMap!=null) {
                mMap.addMarker(new MarkerOptions().position(sydney).title("new location marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
            }
        }else {
            Log.e("location","cannot getLocation");
        }
    }
    public boolean CheckPermessoinAllowed(){

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("permession","permession is denied");

            return false;
           }
        Log.e("permession","permession allowed ");

        return true;

    }
   public static final int  MY_PERMISSIONS_REQUEST_LOCATION =20;
    public void RequestLocationPermession(){
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
              if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                  Log.e("permession","we should show explanation");

                  //Show Explanation to the user
                  ShowConfirmationDialoge("Permession Request", "we need to acccess your location to blablaba ", "ok"
                        , "no", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //Request Permession
                                requestPermissions(
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        });
            } else {
                  // first time to request accees location
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                  Log.e("permession","Requesting location ");

              }
        } else {
            // Permission has already been granted
            printUserLocation();

            Log.e("permession","allowed before");
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(30.0395, 31.2025);
        if (myLocation!=null)
            sydney = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in dokki"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.e("function","aaaa");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //the user allowed the application to use location Permession
                    Log.e("permession","user accepted access location ");
                    printUserLocation();


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.e("permession","user denied access location ");

                }
                return;
            }


        }
    }
}
