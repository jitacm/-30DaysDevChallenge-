#include <Servo.h>

// Define servo motor object
Servo servoMotor;

// Pin assignment for potentiometer
const int potPin = A0;

void setup() {
  // Attach servo to pin 9
  servoMotor.attach(9);
}

void loop() {
  // Read the position of the potentiometer (0 to 1023)
  int potValue = analogRead(potPin);

  // Map the potentiometer value (0 to 1023) to servo angle (0 to 180)
  int servoAngle = map(potValue, 0, 1023, 0, 180);

  // Move the servo motor to the mapped angle
  servoMotor.write(servoAngle);

  // Delay to stabilize servo position
  delay(15); // Adjust delay as needed for smoother operation
}
