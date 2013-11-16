package com.kres.iota;

public class IoTABox 
{
	public void find()
	{
		
	}
	public void connect()
	{
		
	}
	public void GPIOWrite(int pin, char data)
	{
		//write() => sends action
	}
	public char GPIORead(int pin)
	{
		//write() => sends which pin to read from
		//ch = read() => reads the data
		return '0';
	}
	public void analogWrite(int pin, char data)
	{
		//write()
		//same as GPIOWrite logic
	}
	public char analogRead(int pin)
	{
		return '0';
	}
	private void write(char data)
	{
		
	}
	private char read()
	{
		//have our own delimiter --- problem much?
		//or fixed 8 bits!! or 16 bits ;)
		return '0';
	}
}
