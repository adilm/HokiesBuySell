package org.vt.ece4564.hokiebuysell;

import org.vt.ece4564.hokiebuysell.R;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class AccActivity extends Activity {
	  private SensorManager sensorManager;
	  private boolean color = false;
	  private View view;
	  private long lastUpdate;
	  Button buttonGoBackAcc;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		        WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acc);
		
	    view = findViewById(R.id.textView);
	    view.setBackgroundColor(Color.GREEN);
		buttonGoBackAcc = (Button) findViewById(R.id.backButtonAcc);

	    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    Sensor AccSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    if (AccSensor == null) {
			Toast.makeText(AccActivity.this,
					"No Light Sensor! quit-", Toast.LENGTH_LONG).show();
		} else {

			//counterText.setText("caccelSensor");
			sensorManager.registerListener(accSensorEventListener, AccSensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	    
	    lastUpdate = System.currentTimeMillis();
		
	    buttonGoBackAcc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();

			}
		});
	}
	
	SensorEventListener accSensorEventListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				getAccelerometer(event);
			}
		}

		private void getAccelerometer(SensorEvent event) {
			float[] values = event.values;
			// Movement
			float x = values[0];
			float y = values[1];
			float z = values[2];

			float accelationSquareRoot = (x * x + y * y + z * z)
					/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
			long actualTime = System.currentTimeMillis();
			if (accelationSquareRoot >= 2) //
			{
				if (actualTime - lastUpdate < 200) {
					return;
				}
				lastUpdate = actualTime;
			
				if (color) {
					view.setBackgroundColor(Color.YELLOW);

				}
				else{
					view.setBackgroundColor(Color.BLUE);

				}
				color = !color;
			}
		}

	};

}
