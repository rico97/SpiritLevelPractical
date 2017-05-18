package com.example.rico.spiritlevelpractical;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public Vibrator vibrating;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float deltaX = 0;
    private float deltaY = 0;
    private float initialX, initialY;
    private float vibrateThreshold = 0;
    private TextView currentX, currentY;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        display = (Display) findViewById(R.id.display);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null){
            //accelarometer is set. does our device have accelerometer
            //we initialize our sensorManager in order to bind the sensor to listen to the accelerometer events.
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() /2;
        }else{
            //in case device doesnt have accelerometer
        }
        vibrating = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ResetValues();

        deltaX = 0- event.values[0];
        deltaY = event.values[1];
        double angle = Math.toDegrees(Math.atan2(deltaX,deltaY));
        displayCurrentCoordinates(Math.abs(angle));
        displayWaterLevel((float)angle);
        display.invalidate();
    }

    public void ResetValues() {
        display.setMessage("0.0");
    }

    // display the current x,y,z accelerometer values
    public void displayCurrentCoordinates(double angle) {
        display.setMessage(String.format("%.0f°",(float)angle));
    }

    public void displayWaterLevel(float angle) {
        display.setAngle(angle);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
