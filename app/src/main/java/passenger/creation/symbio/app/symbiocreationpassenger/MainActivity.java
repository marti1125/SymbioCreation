package passenger.creation.symbio.app.symbiocreationpassenger;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;

public class MainActivity extends AppCompatActivity {

    private MapView mvRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoib3NndXgiLCJhIjoiVlRDVmZrRSJ9.ykpiXi2ryABxfZaBfZxnYw");
        setContentView(R.layout.activity_main);
        mvRoute = findViewById(R.id.mv_route);
        mvRoute.onCreate(savedInstanceState);
        mvRoute.getMapAsync(mapbox -> {
            mapbox.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {

                }
            });
        });
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

}
