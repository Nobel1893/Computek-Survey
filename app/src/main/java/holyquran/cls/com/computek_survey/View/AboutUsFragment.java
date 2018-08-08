package holyquran.cls.com.computek_survey.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import holyquran.cls.com.computek_survey.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment implements OnMapReadyCallback{

    MapView mMapView;
    private GoogleMap mMap;
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
      //  mMapView.onResume(); // needed to get the map to display immediately

        mMapView.getMapAsync(this);


    return mainView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(30.0395, 31.2025);
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
}
