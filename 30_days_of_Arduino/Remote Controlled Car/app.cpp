// Motor pins
const int leftMotorPin1 = 2;
const int leftMotorPin2 = 3;
const int rightMotorPin1 = 4;
const int rightMotorPin2 = 5;

// IR receiver pin
const int irReceiverPin = 6;  // Connect IR receiver output to pin 6

void setup() {
  // Motor pins as outputs
  pinMode(leftMotorPin1, OUTPUT);
  pinMode(leftMotorPin2, OUTPUT);
  pinMode(rightMotorPin1, OUTPUT);
  pinMode(rightMotorPin2, OUTPUT);

  // IR receiver pin as input
  pinMode(irReceiverPin, INPUT);

  // Initialize serial communication for debugging
  Serial.begin(9600);
}

void loop() {
  // Read IR receiver input
  int irValue = digitalRead(irReceiverPin);

  // Debugging output
  Serial.println(irValue);

  // Control motors based on IR remote commands
  if (irValue == HIGH) {
    // Forward
    analogWrite(leftMotorPin1, 200);
    analogWrite(leftMotorPin2, 0);
    analogWrite(rightMotorPin1, 200);
    analogWrite(rightMotorPin2, 0);
  } else {
    // Stop (or other commands based on IR remote buttons)
    analogWrite(leftMotorPin1, 0);
    analogWrite(leftMotorPin2, 0);
    analogWrite(rightMotorPin1, 0);
    analogWrite(rightMotorPin2, 0);
  }
}
