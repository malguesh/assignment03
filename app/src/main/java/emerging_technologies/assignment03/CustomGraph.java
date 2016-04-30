package emerging_technologies.assignment03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomGraph extends View {

    private Paint paintGraphLine;

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

        paintGraphLine.setColor(Color.WHITE);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        // value to get the distance between every line
        int distHeight = height / 6;

        for (int i = distHeight; i < height - distHeight; i += distHeight) {
            canvas.drawLine(0, i, width, i, paintGraphLine);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
