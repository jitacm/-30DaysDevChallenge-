#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BMP085_U.h>
#include <DHT.h>
#include <LiquidCrystal.h>

// Define the pins for DHT sensor and LCD
#define DHTPIN 2      // Digital pin connected to DHT sensor
#define DHTTYPE DHT11  // DHT sensor type
#define I2C_ADDR 0x3C // I2C address for the LCD

// Initialize DHT sensor
DHT dht(DHTPIN, DHTTYPE);

// Initialize BMP180 sensor
Adafruit_BMP085_Unified bmp = Adafruit_BMP085_Unified(10085);

// Initialize LCD display
LiquidCrystal lcd(I2C_ADDR, 16, 2);

void setup() {
  // Start serial communication for debugging
  Serial.begin(9600);

  // Initialize DHT sensor
  dht.begin();

  // Initialize BMP180 sensor
  if (!bmp.begin()) {
    Serial.println("Could not find a valid BMP180 sensor, check wiring!");
    while (1);
  }

  // Initialize LCD
  lcd.begin(16, 2);
  lcd.clear();
}

void loop() {
  // Read temperature from DHT11 sensor
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

  // Read temperature and pressure from BMP180 sensor
  sensors_event_t event;
  bmp.getEvent(&event);
  if (event.pressure) {
    float pressure = event.pressure;
    
    // Print temperature, humidity, and pressure to Serial monitor
    Serial.print("Temperature: ");
    Serial.print(temperature);
    Serial.println(" °C");
    Serial.print("Humidity: ");
    Serial.print(humidity);
    Serial.println(" %");
    Serial.print("Pressure: ");
    Serial.print(pressure);
    Serial.println(" hPa");
    
    // Display temperature, humidity, and pressure on LCD
    lcd.setCursor(0, 0);
    lcd.print("Temp: ");
    lcd.print(temperature);
    lcd.print(" C");

    lcd.setCursor(0, 1);
    lcd.print("Humidity: ");
    lcd.print(humidity);
    lcd.print(" %");

    // Wait before updating readings
    delay(2000); // Adjust delay time as needed
  } else {
    Serial.println("Sensor error");
    while (1);
  }
}
