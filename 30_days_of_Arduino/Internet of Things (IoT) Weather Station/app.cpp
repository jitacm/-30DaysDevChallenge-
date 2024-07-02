#include <ESP8266WiFi.h>
#include <DHT.h>

// WiFi credentials
const char* ssid = "your_wifi_ssid";
const char* password = "your_wifi_password";

// ThingSpeak settings
const char* server = "api.thingspeak.com";
const String apiKey = "your_thingspeak_api_key";

// DHT sensor settings
#define DHTPIN D4     // DHT sensor pin
#define DHTTYPE DHT11 // DHT sensor type
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  // Initialize serial communication for debugging
  Serial.begin(115200);

  // Initialize DHT sensor
  dht.begin();

  // Connect to WiFi
  connectWiFi();
}

void loop() {
  // Read temperature and humidity from DHT sensor
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

  // Check if any reads failed and exit early (to try again).
  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Print temperature and humidity to serial monitor
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.println(" °C");
  Serial.print("Humidity: ");
  Serial.print(humidity);
  Serial.println(" %");

  // Send data to ThingSpeak
  sendDataToThingSpeak(temperature, humidity);

  // Delay before next data send (in milliseconds)
  delay(30000); // 30 seconds delay
}

void connectWiFi() {
  // Connect to WiFi
  WiFi.begin(ssid, password);
  Serial.print("Connecting to WiFi");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
}

void sendDataToThingSpeak(float temperature, float humidity) {
  // Create HTTP client instance
  WiFiClient client;
  
  // Prepare URL for ThingSpeak
  String url = "/update?api_key=" + apiKey;
  url += "&field1=" + String(temperature);
  url += "&field2=" + String(humidity);

  // Send HTTP request to ThingSpeak
  if (client.connect(server, 80)) {
    client.print(String("GET ") + url + " HTTP/1.1\r\n");
    client.print(String("Host: ") + server + "\r\n");
    client.print("Connection: close\r\n\r\n");
    delay(500); // Wait for server response
    Serial.println("Data sent to ThingSpeak");
    client.stop(); // Disconnect from server
  } else {
    Serial.println("Failed to send data to ThingSpeak");
  }
}
