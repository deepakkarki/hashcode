#include<Servo.h>
//should modify android code to do handshake
//two 0 bytes and two 255 bytes for start and stop respectively

Servo s;
boolean con = false;
int a_pin = 9;
int(*func[10])(int,int);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  s.attach(a_pin);
  s.write = 0;
}

void loop() 
{
  // put your main code here, to run repeatedly: 
  int val;
  if (Serial.available())
  {
      val = Serial.read();
      if (val != 0) continue;
      val = Serial.read();
      if(val == 0)
      {
        con = true;
        listen(); 
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
    while(Serial.available == 0){;}
    meta = Serial.read();
    while(Serial.available == 0){;}
    data = Serial.read();
    ins = meta >> 4;
    pin = meta & 15;
    val = func[ins](pin,data);
  }
}

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
  analogWrite(pin,data);
  return 0;
}

int digital_read(int pin,int data) //data is always 0 for read
{
  //to be filled later
  return 0;
}

int analog_read(int pin, int data)//data is always 0
{
  return analogRead(sensorPin); 
}

int close_con(int pin, int data)// they params dont matter
{
  return -1;
}






