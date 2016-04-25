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

    private TextView tv_gps_state;
    private TextView tv_current_speed;
    private TextView tv_average_speed;
    private TextView tv_overall_time;
    private Button btn_tracking;

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

        tv_gps_state = (TextView) findViewById(R.id.tv_gps_state);
        tv_current_speed = (TextView) findViewById(R.id.tv_current_speed);
        tv_average_speed = (TextView) findViewById(R.id.tv_average_speed);
        tv_overall_time = (TextView) findViewById(R.id.tv_overall_time);
        btn_tracking = (Button) findViewById(R.id.btn_tracking);
    }
}
