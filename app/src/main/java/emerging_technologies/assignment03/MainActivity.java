package emerging_technologies.assignment03;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private boolean isTracking = false;
    private CustomViewGPSTracker cvGPSTracker;
    private CustomGraph cvCustomGraph;
    private Button btnTracking;
    private LocationListener locationListener;
    float average_speed;
    int time;

    // -- LifeCycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnTracking = (Button) findViewById(R.id.btn_tracking);
        cvGPSTracker = (CustomViewGPSTracker) findViewById(R.id.custom_gps_tracker);
        cvCustomGraph = (CustomGraph) findViewById(R.id.custom_graph);
        average_speed = 0;

        initLocationListener();

        btnTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTracking) {
                    btnTracking.setText(R.string.stop_tracking);
                    time = 0;
                    addLocationListener();
                    isTracking = true;
                } else {
                    btnTracking.setText(R.string.start_tracking);
                    locationManager.removeUpdates(locationListener);
                    cvGPSTracker.tvCurrentSpeed.setText(R.string.current_speed);
                    cvGPSTracker.tvAverageSpeed.setText(R.string.average_speed);
                    cvGPSTracker.tvOverallTime.setText(R.string.overall_time);
                    isTracking = false;
                }
            }
        });
    }

    /**
     * Init Location Listener to make a difference with the addLocationListener
     * because we need to remove the listener when we stop tracking
     */
    private void initLocationListener() {
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                float speed = location.getSpeed() * 3600 / 1000;

                cvGPSTracker.tvCurrentSpeed.setText("Current Speed: " + speed);
                cvCustomGraph.fillListSpeeds(speed);

                for (int i = 0; i < cvCustomGraph.listSpeeds.size(); i++) {
                    average_speed += cvCustomGraph.listSpeeds.get(i);
                }
                average_speed = average_speed / cvCustomGraph.listSpeeds.size();

                cvGPSTracker.tvAverageSpeed.setText("Average Speed: " + average_speed);

                cvGPSTracker.tvOverallTime.setText("Overall Time: " + time + "s");
                time += 1;

                cvCustomGraph.invalidate();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                if (provider == LocationManager.GPS_PROVIDER) {

                }
            }
        };
    }

    private void addLocationListener() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                0,
                locationListener);
    }
}

/**
 * Custom View of the GPS Tracker
 */
class CustomViewGPSTracker extends LinearLayout {

    public TextView tvGpsState, tvCurrentSpeed, tvAverageSpeed, tvOverallTime;

    public CustomViewGPSTracker(Context context) {
        super(context);
        init();
    }

    public CustomViewGPSTracker(Context context, AttributeSet as) {
        super(context, as);
        init();
    }

    public CustomViewGPSTracker(Context context, AttributeSet as, int default_style) {
        super(context, as, default_style);
        init();
    }

    private void init() {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.compound_view_layout, this, true);

        tvGpsState = (TextView) findViewById(R.id.tv_gps_state);
        tvCurrentSpeed = (TextView) findViewById(R.id.tv_current_speed);
        tvAverageSpeed = (TextView) findViewById(R.id.tv_average_speed);
        tvOverallTime = (TextView) findViewById(R.id.tv_overall_time);
    }


}
