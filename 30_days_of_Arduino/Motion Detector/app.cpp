// Motor pins
const int leftMotorPin1 = 2;
const int leftMotorPin2 = 3;
const int rightMotorPin1 = 4;
const int rightMotorPin2 = 5;

// Photoresistor pins
const int leftPhotoresistorPin = A0;
const int rightPhotoresistorPin = A1;

void setup() {
  // Motor pins as outputs
  pinMode(leftMotorPin1, OUTPUT);
  pinMode(leftMotorPin2, OUTPUT);
  pinMode(rightMotorPin1, OUTPUT);
  pinMode(rightMotorPin2, OUTPUT);
}

void loop() {
  int leftSensorValue = analogRead(leftPhotoresistorPin);
  int rightSensorValue = analogRead(rightPhotoresistorPin);

  // Adjust motor speeds based on light intensity difference
  if (leftSensorValue > rightSensorValue) {
    // More light on the left, turn right
    analogWrite(leftMotorPin1, 200);
    analogWrite(leftMotorPin2, 0);
    analogWrite(rightMotorPin1, 0);
    analogWrite(rightMotorPin2, 200);
  } else if (rightSensorValue > leftSensorValue) {
    // More light on the right, turn left
    analogWrite(leftMotorPin1, 0);
    analogWrite(leftMotorPin2, 200);
    analogWrite(rightMotorPin1, 200);
    analogWrite(rightMotorPin2, 0);
  } else {
    // Both photoresistors see similar light intensity, move forward
    analogWrite(leftMotorPin1, 200);
    analogWrite(leftMotorPin2, 0);
    analogWrite(rightMotorPin1, 200);
    analogWrite(rightMotorPin2, 0);
  }
}
