#include <LiquidCrystal.h>

// Define LCD pins
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

// Define pulse sensor pin
const int pulseSensorPin = A0;

// Variables
int pulseSensorValue;
int heartRate;
unsigned long lastBeatTime = 0;
unsigned long period = 600;
boolean pulse = false;

void setup() {
  // Initialize LCD
  lcd.begin(16, 2);
  lcd.clear();
  
  // Initialize pulse sensor pin
  pinMode(pulseSensorPin, INPUT);
  
  // Print initial message
  lcd.print(" Heart Rate ");
  lcd.setCursor(0, 1);
  lcd.print("  Monitor   ");
  delay(2000);
  lcd.clear();
}

void loop() {
  // Read pulse sensor value
  pulseSensorValue = analogRead(pulseSensorPin);
  
  if (pulseSensorValue > 600) {
    pulse = true;
  }

  if (pulse) {
    if (pulseSensorValue < 550) {
      if (millis() - lastBeatTime > period) {
        lastBeatTime = millis();
        heartRate++;
        lcd.clear();
        lcd.print("Heart Rate:");
        lcd.setCursor(0, 1);
        lcd.print(heartRate);
        lcd.print(" BPM");
      }
    }
    pulse = false;
  }
}
