# Bluetooth Controlled LED Project

## Description
Control an LED using an Arduino and a smartphone via Bluetooth. This project utilizes the HC-05 Bluetooth module to receive commands from a Bluetooth terminal app on your smartphone.

## Components Needed
- Arduino board (e.g., Uno, Nano)
- HC-05 Bluetooth module
- LED
- Resistor (appropriate value for the LED)
- Breadboard
- Jumper wires

## Circuit Diagram
Include a circuit diagram image here if you create one.

## Instructions
1. **Setup**: Connect the HC-05 Bluetooth module, LED, resistor, and Arduino as shown in the circuit diagram.
2. **Bluetooth Setup**: Pair the HC-05 module with your smartphone using Bluetooth settings.
3. **Install Bluetooth Terminal App**: Install a Bluetooth terminal app (e.g., Serial Bluetooth Terminal for Android, Bluetooth Serial Terminal for iOS).
4. **Upload Code**: Upload the provided Arduino code (`bluetooth_led_control.ino`) to your Arduino board.
5. **Run**: Open the Bluetooth terminal app on your smartphone, connect to the HC-05 module, and send '1' to turn the LED ON or '0' to turn it OFF.

## Additional Notes
- Ensure the Bluetooth module is set to the correct baud rate (9600 baud in this example) matching both the Arduino code and your Bluetooth terminal app settings.
- Customize the Arduino code to add more functionality, such as controlling multiple LEDs or implementing feedback messages.

Enjoy controlling your LED wirelessly with Arduino and Bluetooth!
