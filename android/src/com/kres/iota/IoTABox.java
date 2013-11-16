package com.kres.iota;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class IoTABox 
{
	boolean connected;
	//connected state
	BluetoothAdapter btAdpt;
	//a bluetooth handle
    BluetoothSocket sock;
    //socket opened via the adapter
    BluetoothDevice dev;
    //the refrence to the device we want to conncet to
    OutputStream dev_out;
    //corresponding in/out put stream 
    InputStream dev_in;
	
	public int find(String addr)
	{
	//initialize dev to the requested IoTA box
		btAdpt = BluetoothAdapter.getDefaultAdapter();
		
		//in case bluetooth is not enabled on my phone, requests to enable it!!
		if(!btAdpt.isEnabled())
		{
		   return -1;
		   //if bluetooth is not enabled, return -1
		}
		dev = btAdpt.getRemoteDevice(addr);
		if (dev == null)
		{
			return 0;
			//device is not found
		}
		else 
		{
			return 1;
			//dev found
		}
	}
	
	public void connect()
	{
	//initializes sock, in/out put stream
	//should be preceded by find(address)
		UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
		try
		{
	        sock = dev.createRfcommSocketToServiceRecord(uuid);        
	        sock.connect();
	        connected = true;
	        dev_out = sock.getOutputStream();
	        dev_in = sock.getInputStream();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void GPIOWrite(int pin, int data)
	{
		//write() => sends action
		int gpioMask = 1 << 4;
		int meta = gpioMask | (pin);
		write(meta);
		write(data);
	}
	public char GPIORead(int pin)
	{
		//write() => sends which pin to read from
		//ch = read() => reads the data
		return '0';
	}
	public void analogWrite(int pin, int data)
	{
		//write()
		//same as GPIOWrite logic
		int analogMask = 1 << 5;
		int meta = analogMask | (pin);
		write(meta);
		write(data);
	}
	
	public char analogRead(int pin)
	{
		return '0';
	}
	
	public boolean isConnected()
	{
		return connected;
	}
	
	private void write(int data)
	{
		try
		{	
			dev_out.write(data);
		}
		catch(Exception e)
		{
			
		}
	}
	private char read()
	{
		//have our own delimiter --- problem much?
		//or fixed 8 bits!! or 16 bits ;)
		//blocking call
		return '0';
	}
	
	public void close()
	{
		try
		{
	        dev_out.close();
	        dev_in.close();
	        sock.close();
	        connected = false;
		}
		catch (Exception e){
		}
	}
}
