package pl.kosteklvp.ball_v3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView ball;
    float screenHeightInDp;
    float screenWidthInDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),0,null);

        Context context = getApplicationContext();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        screenWidthInDp = Math.round(dm.widthPixels / dm.density) + 85;
        screenHeightInDp = Math.round(dm.heightPixels / dm.density) + 105;

        _("SCREEN HEIGHT: " + screenHeightInDp );
        _("SCREEN WIDTH: " + screenWidthInDp );

        ball = findViewById(R.id.imageView2);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
     //   _("X: " + sensorEvent.values[0] + " | Y: " + sensorEvent.values[1]);
        if(ball.getX()>0.0 && ball.getX()<screenWidthInDp) {
            ball.setX(ball.getX() - sensorEvent.values[0]);
            if(ball.getX()<0.0) ball.setX((float) 1.0);
            if(ball.getX()>screenWidthInDp) ball.setX((float) (screenWidthInDp-1.0));
        }
        if(ball.getY()>0.0 && ball.getY()<screenHeightInDp) {
            ball.setY(ball.getY() + sensorEvent.values[1]);
            if(ball.getY()<0.0) ball.setY((float) 1.0);
            if(ball.getY()>screenHeightInDp) ball.setY((float) (screenHeightInDp-1.0));
        }
    }

    private void _(String napis) {
        Log.d("MainActivity", napis);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
