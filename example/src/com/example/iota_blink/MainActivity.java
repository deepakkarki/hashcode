package com.example.iota_blink;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.kres.iota.IoTABox;

public class MainActivity extends Activity 
{
	//three buttons I have used
	Button conn;
    Button on;
    Button off;
    IoTABox box;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		conn = (Button) findViewById(R.id.button1);
		on = (Button) findViewById(R.id.button2);
		off = (Button) findViewById(R.id.button3);
		box = new IoTABox();
		conn.setOnClickListener(connect_it);
		on.setOnClickListener(send_on);
		off.setOnClickListener(send_off);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
	OnClickListener send_on = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			box.GPIOWrite(13, 1);
		}
	};
	
	OnClickListener send_off = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			box.GPIOWrite(13,0);
		}
	};
}
