package emerging_technologies.assignment03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Custom View of the Graph
 */
public class CustomGraph extends View {

    private Paint paintGraphLine;
    private Paint paintSpeedLine;
    private Paint paintAverage;
    public ArrayList<Float> listSpeeds;
    float xStart;
    float yStart;
    float xStop;
    float yStop;

    // -- LifeCycle

    public CustomGraph(Context context) {
        super(context);
        init();
    }

    public CustomGraph(Context context, AttributeSet as) {
        super(context, as);
        init();
    }

    public CustomGraph(Context context, AttributeSet as, int default_style) {
        super(context, as, default_style);
        init();
    }

    private void init() {
        paintGraphLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintSpeedLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintAverage = new Paint(Paint.ANTI_ALIAS_FLAG);
        listSpeeds = new ArrayList<>();

        paintGraphLine.setColor(Color.WHITE);
        paintSpeedLine.setColor(Color.GREEN);
        paintAverage.setColor(Color.RED);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        // value to get the distance between every line
        int distHeight = height / 6;
        float average_speed = 0;

        if (listSpeeds.size() == 100) {
            for (int i = 0; i < listSpeeds.size(); i++) {
                average_speed += listSpeeds.get(i);
            }
            average_speed = average_speed / listSpeeds.size();
            canvas.drawLine(0, width - (average_speed / 60 * width), width, height - (average_speed / 60 * height), paintAverage);
        }

        // Loop to draw the white lines
        for (int i = distHeight; i < height - distHeight; i += distHeight) {
            canvas.drawLine(0, i, width, i, paintGraphLine);
        }

        // Loop to browse the list of speeds and draw a line between each values
        for (int j = 0; j < listSpeeds.size() - 1; j++) {
            xStart = (float) width / 99 * j;
            yStart = (float) width - (listSpeeds.get(j) / 60 * width);
            xStop = (float) width / 99 * (j + 1);
            yStop = (float) width - (listSpeeds.get(j + 1) / 60 * width);
            canvas.drawLine(xStart, yStart, xStop, yStop, paintSpeedLine);
            xStart = xStop;
            yStart = yStop;
        }
        if (listSpeeds.size() == 100) {

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    // -- Utilities

    /**
     * Fills an ArrayList to stock the speeds
     * Check that the ArrayList is never longer than 100 values
     *
     * @param speed
     */
    public void fillListSpeeds(float speed) {
        listSpeeds.add(speed);
        if (listSpeeds.size() > 100) {
            listSpeeds.remove(0);
        }
    }

}
