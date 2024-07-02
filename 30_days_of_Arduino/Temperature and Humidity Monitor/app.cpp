// Include libraries
#include <DHT.h>
#include <LiquidCrystal.h>

// Constants for DHT sensor
#define DHTPIN 2     // Pin connected to DHT11 sensor
#define DHTTYPE DHT11 // DHT11 sensor type

// Initialize DHT sensor
DHT dht(DHTPIN, DHTTYPE);

// LCD module connections
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {
  // Start serial communication for debugging
  Serial.begin(9600);

  // Initialize the LCD
  lcd.begin(16, 2);

  // Start up the DHT sensor
  dht.begin();
}

void loop() {
  // Reading temperature or humidity takes about 250 milliseconds!
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

  // Check if readings are valid
  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Print temperature and humidity values to Serial monitor
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.println(" °C");
  Serial.print("Humidity: ");
  Serial.print(humidity);
  Serial.println(" %");

  // Display temperature and humidity on LCD
  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print(temperature);
  lcd.print(" C");

  lcd.setCursor(0, 1);
  lcd.print("Humidity: ");
  lcd.print(humidity);
  lcd.print(" %");

  // Adjust delay time as needed
  delay(2000); // Update every 2 seconds
}
