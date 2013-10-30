package org.vt.ece4564.hokiebuysell;

import org.vt.ece4564.hokiebuysell.R;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LightActivity extends Activity {
	ProgressBar lightMeter;
	TextView textMax, textReading;
	Button buttonGoBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		lightMeter = (ProgressBar) findViewById(R.id.lightmeter);
		textMax = (TextView) findViewById(R.id.max);
		textReading = (TextView) findViewById(R.id.reading);
		buttonGoBack = (Button) findViewById(R.id.backButton);
		
		buttonGoBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();

			}
		});
		
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		if (lightSensor == null) {
			Toast.makeText(LightActivity.this,
					"No Light Sensor! quit-", Toast.LENGTH_LONG).show();
		} else {
			// float max = lightSensor.getMaximumRange();
			float max = 100;
			lightMeter.setMax((int) max);
			textMax.setText("Max Reading: " + String.valueOf(max));
			//Log.e(tag, " lightSensor");
			sensorManager.registerListener(lightSensorEventListener,
					lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}
	
	SensorEventListener lightSensorEventListener = new SensorEventListener() {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			//Log.e(tag, " light onSensorChanged");
			if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
				float currentReading = event.values[0];
				lightMeter.setProgress((int) currentReading);
				textReading.setText("Current Reading: "
						+ String.valueOf(currentReading));
			}

		}

	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
