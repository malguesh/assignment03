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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private boolean isTracking = false;
    private CustomViewGPSTracker cvGPSTracker;
    private Button btnTracking;
    private LocationListener locationListener;
    private TextView tvGpsState, tvCurrentSpeed, tvAverageSpeed, tvOverallTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnTracking = (Button) findViewById(R.id.btn_tracking);
        cvGPSTracker = (CustomViewGPSTracker) findViewById(R.id.custom_gps_tracker);

        initLocationListener();

        btnTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("values: " + cvGPSTracker.speedValues);
                if (!isTracking) {
                    btnTracking.setText(R.string.stop_tracking);
                    addLocationListener();
                    isTracking = true;
                } else {
                    btnTracking.setText(R.string.start_tracking);
                    locationManager.removeUpdates(locationListener);
                    isTracking = false;
                }
            }
        });
    }

    private void initLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                float speed = location.getSpeed() * 3600 / 1000;
                System.out.println("location.getSpeed(): " + speed);
                System.out.println("values.size(): " + cvGPSTracker.speedValues.size());
                cvGPSTracker.fillValues(speed);
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
                5,
                locationListener);
    }
}

// Custom view of the GPS tracker
class CustomViewGPSTracker extends LinearLayout {

    private TextView tvGpsState, tvCurrentSpeed, tvAverageSpeed, tvOverallTime;
    // TODO: change speedValue into private and erase the test in MainActivity println(cv.speedValues)
    public ArrayList<Float> speedValues = new ArrayList<Float>();

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

    /**
     * Fills an ArrayList to stock the speeds
     * Checks that the ArrayList is never longer than 100 values
     *
     * @param speed
     */
    public void fillValues(float speed) {
        speedValues.add(speed);
        if (speedValues.size() > 100) {
            speedValues.remove(0);
        }
    }

}
