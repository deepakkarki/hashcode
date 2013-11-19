package com.example.bluetoothled;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{

	BluetoothAdapter btAdpt;
	//returns a bluetooth handle
    BluetoothSocket sock;
    //socket opened via the adapter
    BluetoothDevice dev;
    //the refrence to the device we want to conncet to
    OutputStream dev_out;
    //corresponding in/out put stream 
    InputStream dev_in;
    Button conn;
    //three buttons I have used
    Button on;
    Button off;
    boolean connected;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		connected = false;
		setContentView(R.layout.activity_main);
		conn = (Button) findViewById(R.id.button1);
		on = (Button) findViewById(R.id.button2);
		off = (Button) findViewById(R.id.button3);
		conn.setOnClickListener(connect_it);
		on.setOnClickListener(send_on);
		off.setOnClickListener(send_off);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void find(String addr)
	//initializes dev with reference to IoTA box
	{
			btAdpt = BluetoothAdapter.getDefaultAdapter();
			
			//in case bluetooth is not enabled on my phone, requests to enable it!!
			if(!btAdpt.isEnabled())
			{
			   Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			   startActivityForResult(enableBluetooth, 0);
			}
			dev = btAdpt.getRemoteDevice(addr);
			//or should I : return btAdpt.getRemoteDevice(addr); 
			//this wud be better for more generic case in the final app
	}
	
	public void connect()
	//initializes sock, in/out put stream
	{
		UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
		try
		{
	        sock = dev.createRfcommSocketToServiceRecord(uuid);        
	        sock.connect();
	        dev_out = sock.getOutputStream();
	        dev_in = sock.getInputStream();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void send(char ch)
	{
		int c = (int)ch;
		try
		{	
			dev_out.write(c);
		}
		catch(Exception e)
		{
			
		}
	}
	
	void close() 
    {
		try
		{
	        dev_out.close();
	        dev_in.close();
	        sock.close();
		}
		catch (Exception e){
		}
		
    }
	
	OnClickListener connect_it = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			if (!connected)
			{
				find("00:12:12:24:16:30");
				connect();
				connected = true;
				conn.setText("Disconnect");
			}
			else
			{
				connected = false;
				close();
				conn.setText("Connect to Device");
			}
		}
	};
	
	OnClickListener send_on = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			send('1');
		}
	};
	
	OnClickListener send_off = new OnClickListener() 
	{
		public void onClick(View arg0) 
		{
			send('0');
		}
	};
}
