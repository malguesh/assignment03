package emerging_technologies.assignment03;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

// Custom view of the GPS tracker
class CustomViewGPSTracker extends LinearLayout {

    private TextView tvGpsState;
    private TextView tvCurrentSpeed;
    private TextView tvAverageSpeed;
    private TextView tvOverallTime;
    private Button btnTracking;
    private boolean isTracking = false;

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
        btnTracking = (Button) findViewById(R.id.btn_tracking);
        final String textStartTracking = getResources().getString(R.string.start_tracking);
        final String textStopTracking = getResources().getString(R.string.stop_tracking);

        btnTracking.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnTracking.getText().toString().equals(textStopTracking)) {
                    btnTracking.setText(R.string.start_tracking);
                    isTracking = true;
                } else if (btnTracking.getText().toString().equals(textStartTracking)) {
                    btnTracking.setText(R.string.stop_tracking);
                    isTracking = false;
                }
            }
        });
    }
}
