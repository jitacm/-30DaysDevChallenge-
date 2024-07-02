#include <SoftwareSerial.h>

// Define the pins for LED and Bluetooth module
const int ledPin = 12;  // Pin connected to LED
SoftwareSerial bluetooth(2, 3); // RX, TX pins for Bluetooth communication

void setup() {
  // Initialize LED pin as an output
  pinMode(ledPin, OUTPUT);

  // Initialize serial communication for debugging
  Serial.begin(9600);
  
  // Initialize Bluetooth serial communication
  bluetooth.begin(9600);
  Serial.println("Bluetooth LED Control");

}

void loop() {
  if (bluetooth.available() > 0) {
    char command = bluetooth.read();

    // Check commands received from Bluetooth
    switch (command) {
      case '0':
        digitalWrite(ledPin, LOW); // Turn off LED
        Serial.println("LED OFF");
        break;
      case '1':
        digitalWrite(ledPin, HIGH); // Turn on LED
        Serial.println("LED ON");
        break;
      default:
        Serial.println("Invalid command");
        break;
    }
  }
}
