package com.example.iota_triled;


import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import com.kres.iota.IoTABox;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    
   SeekBar rSeekBar;
   IoTABox box;
   Button conn;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       box = new IoTABox();
       rSeekBar = (SeekBar)findViewById(R.id.seekBar1);
       rSeekBar.setOnSeekBarChangeListener(this);
       rSeekBar.setMax(180);
       conn = (Button)findViewById(R.id.button1);
       conn.setOnClickListener(connect_it);
      
   }

   @Override
   public void onProgressChanged(SeekBar bar, int progress, boolean fromUser)
   {
	   
	   box.analogWrite(9, progress);
	   /*switch(bar.getId())
	   {
	   		case R.id.seekBar1:
	   			//pass 'progress' to change RED
	   			box.analogWrite(5, progress);
	   			break;
	   		case R.id.seekBar2:
	   			box.analogWrite(3, progress);
	   			break;
	   		case R.id.seekBar3:
	   			box.analogWrite(6, progress);
	   			break;
	   }*/
   }
   
   @Override
	public void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		box.close();
		conn.setText("Connect to Device");
	}
   
   @Override
   public void onStartTrackingTouch(SeekBar bar) 
   {
	   
   }
   @Override
   public void onStopTrackingTouch(SeekBar bar) 
   {
	   
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