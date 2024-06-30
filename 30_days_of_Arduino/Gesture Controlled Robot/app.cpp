#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_ADXL345_U.h>

// Define motor control pins
const int motorPin1 = 2; // Motor 1 control pin 1
const int motorPin2 = 3; // Motor 1 control pin 2
const int motorPin3 = 4; // Motor 2 control pin 1
const int motorPin4 = 5; // Motor 2 control pin 2

// Define accelerometer object
Adafruit_ADXL345_Unified accel = Adafruit_ADXL345_Unified(12345);

void setup() {
  // Initialize serial communication for debugging
  Serial.begin(9600);

  // Initialize motor control pins as outputs
  pinMode(motorPin1, OUTPUT);
  pinMode(motorPin2, OUTPUT);
  pinMode(motorPin3, OUTPUT);
  pinMode(motorPin4, OUTPUT);

  // Initialize accelerometer
  if(!accel.begin()) {
    Serial.println("Could not find a valid ADXL345 sensor, check wiring!");
    while(1);
  }
}

void loop() {
  // Read accelerometer data
  sensors_event_t event; 
  accel.getEvent(&event);

  // Detect gestures
  if (event.acceleration.y > 8.0) {
    // Move forward
    Serial.println("Forward");
    digitalWrite(motorPin1, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin4, LOW);
  } else if (event.acceleration.y < -8.0) {
    // Move backward
    Serial.println("Backward");
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, HIGH);
  } else if (event.acceleration.x > 8.0) {
    // Turn right
    Serial.println("Right");
    digitalWrite(motorPin1, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, HIGH);
  } else if (event.acceleration.x < -8.0) {
    // Turn left
    Serial.println("Left");
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin4, LOW);
  } else {
    // Stop
    Serial.println("Stop");
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, LOW);
  }

  delay(100); // Adjust delay as needed for responsiveness
}
