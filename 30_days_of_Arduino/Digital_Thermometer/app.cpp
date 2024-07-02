// Include libraries
#include <LiquidCrystal.h>
#include <OneWire.h>
#include <DallasTemperature.h>

// Data wire is connected to pin 2 on the Arduino
const int oneWireBus = 2;

// Setup a oneWire instance to communicate with any OneWire devices
OneWire oneWire(oneWireBus);

// Pass our oneWire reference to Dallas Temperature sensor
DallasTemperature sensors(&oneWire);

// LCD module connections (adjust according to your setup)
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {
  // Start serial communication for debugging
  Serial.begin(9600);

  // Initialize the LCD
  lcd.begin(16, 2);

  // Start up the library
  sensors.begin();
}

void loop() {
  // Request temperature readings
  sensors.requestTemperatures();

  // Read temperature in Celsius
  float temperatureC = sensors.getTempCByIndex(0);

  // Print temperature on Serial monitor for debugging
  Serial.print("Temperature: ");
  Serial.print(temperatureC);
  Serial.println(" °C");

  // Display temperature on LCD
  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print(temperatureC);
  lcd.print(" C");

  // Adjust delay time as needed
  delay(1000);
}
