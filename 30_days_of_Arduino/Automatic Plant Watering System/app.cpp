// Include necessary libraries
#include <Wire.h>
#include <Adafruit_SSD1306.h>

// Initialize OLED display using I2C protocol
Adafruit_SSD1306 display(128, 32, &Wire, -1);

// Define pins for components
const int soilMoisturePin = A0;  // Analog pin for soil moisture sensor
const int pumpRelayPin = 10;     // Digital pin for relay module controlling pump

void setup() {
  // Initialize serial communication for debugging
  Serial.begin(9600);

  // Initialize OLED display
  if(!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) {
    Serial.println(F("SSD1306 allocation failed"));
    for(;;);
  }

  // Display initialization message on OLED
  display.display();
  delay(2000);  // Delay to let display stabilize

  // Initialize pump control pin
  pinMode(pumpRelayPin, OUTPUT);
}

void loop() {
  // Read soil moisture sensor value
  int moistureValue = analogRead(soilMoisturePin);

  // Map moisture value to a range (adjust as needed based on sensor characteristics)
  int moisturePercent = map(moistureValue, 0, 1023, 0, 100);

  // Display moisture percentage on OLED
  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(0,0);
  display.print("Moisture: ");
  display.print(moisturePercent);
  display.println("%");
  display.display();

  // Check moisture level and control pump accordingly
  if (moisturePercent < 50) {
    // Soil is dry, turn on pump
    digitalWrite(pumpRelayPin, HIGH);
    Serial.println("Pump turned on to water the plant.");
    delay(5000); // Watering duration (adjust as needed)
    digitalWrite(pumpRelayPin, LOW);
    Serial.println("Pump turned off.");
  }

  // Delay before next sensor reading
  delay(10000); // Adjust delay time based on the desired update frequency
}
