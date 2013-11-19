#include<Servo.h>
//should modify android code to do handshake
//two 0 bytes and two 255 bytes for start and stop respectively

Servo s;
boolean con = false;
int a_pin = 9;
int(*f[5])(int,int);



int gpio_write(int pin, int data)
{
  pinMode(pin, OUTPUT);
  if(data==0)
  digitalWrite(pin, LOW);
  else
  digitalWrite(pin,HIGH);
  return 0;
}

int analog_write(int pin, int data)
{
  if(pin == 9)
  s.write(data);
  
  else
  analogWrite(pin,data);
  
  return 0;
}

int gpio_read(int pin,int data) //data is always 0 for read
{
  //to be filled later
  return 0;
}

int analog_read(int pin, int data)//data is always 0
{
  int senVal =  analogRead(pin);
  Serial.write(senVal) ;
}

int defaultx(int pin, int data)// they params dont matter
{
  return 0;
}


void setup()
{
  // put your setup code here, to run once:
  Serial.begin(9600);
  s.attach(9);
  s.write(0);
  f[0] = defaultx;
  f[1] = gpio_write;
  f[2] = analog_write;
  f[3] = gpio_read;
  f[4] = analog_read;
}

void loop() 
{
  // put your main code here, to run repeatedly: 
  int val;
  if (Serial.available())
  {
      val = Serial.read();
      if (val == 0)
      {
        while(Serial.available()==0);
        val = Serial.read();
        
        if(val == 0)
        {
          con = true;
          listen(); 
        }
      }
  }
  
}



void listen()
{
  int meta;
  int data;
  int ins;
  int pin;
  while(true)
  {
    while(Serial.available() == 0){;}
    meta = Serial.read();
    while(Serial.available() == 0){;}
    data = Serial.read();
    if(meta == 255 && data == 255) break;
    ins = meta >> 4;
    pin = meta & 15;
    f[ins](pin,data);
  }
}





