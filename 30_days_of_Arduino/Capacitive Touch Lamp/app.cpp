#include <CapacitiveSensor.h>

// Define pin numbers
const int touchSensorSendPin = 4; // CapSense send pin
const int touchSensorReceivePin = 2; // CapSense receive pin
const int relayPin = 7; // Relay control pin

// Create CapacitiveSensor object
CapacitiveSensor capSensor = CapacitiveSensor(touchSensorSendPin, touchSensorReceivePin);

// Define variables
const int threshold = 1000; // Capacitive touch threshold
bool lampOn = false; // Lamp status

void setup() {
  // Initialize serial communication for debugging
  Serial.begin(9600);

  // Initialize relay pin as output
  pinMode(relayPin, OUTPUT);

  // Start CapacitiveSensor library
  capSensor.set_CS_AutocaL_Millis(0xFFFFFFFF); // Disable autocalibration
}

void loop() {
  // Read capacitive touch sensor
  long capSensorValue = capSensor.capacitiveSensor(30);

  // Check if touch detected
  if (capSensorValue > threshold) {
    if (!lampOn) {
      // Turn lamp on
      digitalWrite(relayPin, HIGH);
      lampOn = true;
      Serial.println("Lamp turned ON");
    } else {
      // Turn lamp off
      digitalWrite(relayPin, LOW);
      lampOn = false;
      Serial.println("Lamp turned OFF");
    }
    delay(1000); // Debounce delay
  }
}
