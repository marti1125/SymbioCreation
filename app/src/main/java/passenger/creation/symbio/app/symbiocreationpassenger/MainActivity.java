package passenger.creation.symbio.app.symbiocreationpassenger;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    private MapView mvRoute;
    private MapboxMap map;
    private PermissionsManager permissionsManager;

    private Location originLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoib3NndXgiLCJhIjoiVlRDVmZrRSJ9.ykpiXi2ryABxfZaBfZxnYw");
        setContentView(R.layout.activity_main);
        mvRoute = findViewById(R.id.mv_route);
        mvRoute.onCreate(savedInstanceState);
        mvRoute.getMapAsync(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mvRoute.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mvRoute.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mvRoute.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mvRoute.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mvRoute.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvRoute.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvRoute.onSaveInstanceState(outState);
    }



    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.map = mapboxMap;
        enableLocationComponent();
    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent() {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponent locationComponent = map.getLocationComponent();
            locationComponent.activateLocationComponent(this);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            originLocation = locationComponent.getLastKnownLocation();

            locationComponent.setRenderMode(RenderMode.COMPASS);

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent();
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

}

