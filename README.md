#IoTA - Internet of Things : Android
This is our Hardware-Mobile App entery for the 24 hour hackathon "#Code-2013"

##Abstract

Gecko, Jawbone, Nike+, Smart Brush, Lynx, NFC Rings, NinjaBlocks. 

Whats common among all these? They are all hardware apps. 
People know that mobile users no longer like to be limited to the hardware on their mobile. Bringing mobile computing in the sensor world and vice a versa will let us develop innovative and amazing apps. Yet there is a major hiccup most software developers face while developing such applications: the HARDWARE! 


The hack is to develop a hardware framework for Android that allows people to rapid-prototype and develop IOT applications, in fact any hardware enabled application,  virtually without touching hardware ;)



##Description


We propose to build our IoTA box (+ an Android SDK for the same) during the hackathon. Think of the IoTA box as a hardware extension of the Android phone. The box is something to which you can add sensors and actuators and make everything work using our SDK, just as if it were a part of the phone. 
No more protocol handling, hardware debugging etc.! Just use this framework to build any hardware application of your choice, by programming in native Android. 

The IoTA box will be bluetooth-enabled and powered by a ATmega 8bit MCU. As an example, if I want to make an datalogger and visualizer, I can write some code on my Android phone and use the IoTA box as the required hardware, with no extra coding. Using the same box(s) I can also build something as complex as an IoT :D



Technical Aspects:


The project consists of three main parts:
 
IoTA box Hardware : Building our own hardware board, from scratch. For tha hackathon it will be on a bread board, powered by an ATmega 8bit MCU. 
IoTA Box Firmware : Writing a bluetooth bootloader, and custom protocol handling.
IoTA  Android API : Packaging a API to interface with the box based on our custom protocol.
