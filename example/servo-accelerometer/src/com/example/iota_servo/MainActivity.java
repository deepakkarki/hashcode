package com.example.iota_servo;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.view.Menu;
import android.content.Context;
import android.content.Intent;


import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.kres.iota.IoTABox;

public class MainActivity extends Activity implements SensorEventListener{

	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
	Button conn;
	IoTABox box; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        
        conn = (Button)findViewById(R.id.button1);
        conn.setOnClickListener(connect_it);
        
        box = new IoTABox();
	}
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

        float mSensorX = event.values[0], mSensorY = event.values[1];
		
		int deg = (int)(Math.tan(mSensorX/mSensorY) * (180/Math.PI))/2;
		
		if(box.isConnected())
		{
			box.analogWrite(9, deg);
		}
		
	}
	
	@Override
	public void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		box.close();
		conn.setText("Connect to Device");
	}
	
	OnClickListener connect_it = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			if (!box.isConnected())
			{
				int res = box.find("00:12:12:24:16:30");
				if(res == -1)
				{
				   Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				   startActivityForResult(enableBluetooth, 0);
				   box.find("00:12:12:24:16:30");
				}
				box.connect();
				conn.setText("Disconnect");
			}
			else
			{
				box.close();
				conn.setText("Connect to Device");
			}
		}
	};

}