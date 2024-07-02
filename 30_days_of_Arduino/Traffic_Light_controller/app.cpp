// Pin assignments for LEDs
const int redPin = 12;
const int yellowPin = 11;
const int greenPin = 10;

void setup() {
  // Initialize the digital pins as outputs
  pinMode(redPin, OUTPUT);
  pinMode(yellowPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
}

void loop() {
  // Red light (stop)
  digitalWrite(redPin, HIGH);
  digitalWrite(yellowPin, LOW);
  digitalWrite(greenPin, LOW);
  delay(5000); // Red light for 5 seconds
  
  // Red and yellow lights (prepare to go)
  digitalWrite(redPin, HIGH);
  digitalWrite(yellowPin, HIGH);
  digitalWrite(greenPin, LOW);
  delay(2000); // Red and yellow lights for 2 seconds
  
  // Green light (go)
  digitalWrite(redPin, LOW);
  digitalWrite(yellowPin, LOW);
  digitalWrite(greenPin, HIGH);
  delay(5000); // Green light for 5 seconds
  
  // Yellow light (prepare to stop)
  digitalWrite(redPin, LOW);
  digitalWrite(yellowPin, HIGH);
  digitalWrite(greenPin, LOW);
  delay(2000); // Yellow light for 2 seconds
}
