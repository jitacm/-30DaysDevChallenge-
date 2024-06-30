# Password Protected Door Lock Project

## Description
Create a door lock system using Arduino that operates with a password entered via a 4x4 matrix keypad. When the correct password is entered, a servo motor unlocks the door temporarily.

## Components Needed
- Arduino board (e.g., Uno, Nano)
- 4x4 matrix keypad
- Servo motor (standard)
- Resistors (if needed for the keypad)
- Breadboard
- Jumper wires

## Circuit Diagram
Include a circuit diagram image here if you create one.

## Instructions
1. **Setup**: Connect the keypad, servo motor, and Arduino as shown in the circuit diagram.
2. **Set Password**: Modify the `password` variable in the Arduino code (`password_protected_door_lock.ino`) to set your desired password (default: "1234").
3. **Upload Code**: Upload the provided Arduino code to your Arduino board.
4. **Run**: Power on the system and enter the password via the keypad. Press '#' to verify the password and unlock the door temporarily.

## Additional Notes
- Adjust the servo motor angles (`doorLock.write()`) in the Arduino code to match your door lock mechanism.
- Enhance security by implementing timeout mechanisms or additional security features.

Enjoy building your password protected door lock system with Arduino!
