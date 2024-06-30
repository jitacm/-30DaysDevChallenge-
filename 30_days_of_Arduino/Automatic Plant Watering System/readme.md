# Automatic Plant Watering System Project

## Description
Create an automatic plant watering system using Arduino to monitor soil moisture and control a water pump. The system waters the plant when the soil moisture level drops below a specified threshold.

## Components Needed
- Arduino board (e.g., Uno, Nano)
- Soil moisture sensor
- Water pump
- Relay module
- OLED display (optional for displaying moisture levels)
- Breadboard
- Jumper wires

## Circuit Diagram
Include a circuit diagram image here if you create one.

## Instructions
1. **Setup**: Connect the soil moisture sensor, pump, relay module, and optionally the OLED display to the Arduino as shown in the circuit diagram.
2. **Upload Code**: Upload the provided Arduino code (`auto_plant_watering_system.ino`) to your Arduino board.
3. **Run**: Once uploaded, the system will monitor the soil moisture level. When it's below the set threshold, the pump will automatically water the plant.

## Additional Notes
- Adjust the moisture threshold (`moisturePercent < 50`) and watering duration (`delay(5000)`) in the Arduino code according to your plant's needs.
- Ensure the pump and relay module can handle the required voltage and current for safe operation.

Enhance the project by adding features like water level monitoring, scheduling, or integrating with IoT platforms for remote monitoring.

Enjoy automating your plant watering with Arduino!
