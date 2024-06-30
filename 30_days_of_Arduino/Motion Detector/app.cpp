// Pin assignments
const int pirPin = 2;  // PIR sensor output pin
const int ledPin = 13; // LED pin (for demonstration)

void setup() {
  pinMode(pirPin, INPUT);
  pinMode(ledPin, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  int pirState = digitalRead(pirPin);

  if (pirState == HIGH) {
    digitalWrite(ledPin, HIGH); // Turn on LED (or trigger buzzer)
    Serial.println("Motion detected!");
    delay(1000); // Delay to debounce
  } else {
    digitalWrite(ledPin, LOW); // Turn off LED (or buzzer)
  }
}

