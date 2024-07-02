# IoT Weather Station Project

## Description
Create an IoT weather station using Arduino with a DHT11 sensor and ESP8266 WiFi module. This project sends temperature and humidity data to a cloud service (e.g., ThingSpeak) for remote monitoring and analysis.

## Components Needed
- Arduino board (e.g., Uno, Nano)
- DHT11 temperature and humidity sensor
- ESP8266 WiFi module (e.g., ESP-01, ESP-12E)
- Breadboard
- Jumper wires

## Circuit Diagram
Include a circuit diagram image here if you create one.

## Instructions
1. **Setup**: Connect the DHT11 sensor, ESP8266 module, and Arduino as shown in the circuit diagram.
2. **Configure WiFi**: Replace `ssid` and `password` in the Arduino code (`iot_weather_station.ino`) with your WiFi credentials.
3. **Set up ThingSpeak**: Create an account on ThingSpeak, create a channel, and obtain your API key.
4. **Upload Code**: Upload the provided Arduino code (`iot_weather_station.ino`) to your Arduino board.
5. **Monitor Data**: Access your ThingSpeak channel to monitor real-time temperature and humidity data from anywhere.

## Additional Notes
- Ensure the ESP8266 module is compatible with the Arduino board and supports WiFi communication.
- Customize the data fields (`field1`, `field2`) in the Arduino code to match your ThingSpeak channel configuration.
- Enhance the project by adding more sensors or integrating with other IoT platforms for advanced applications.

Enjoy building and monitoring your IoT weather station with Arduino!
