// Define pins for components
const int buttonPin = 2;       // Digital pin for push button
const int segmentA = 3;        // Segment A pin
const int segmentB = 4;        // Segment B pin
const int segmentC = 5;        // Segment C pin
const int segmentD = 6;        // Segment D pin
const int segmentE = 7;        // Segment E pin
const int segmentF = 8;        // Segment F pin
const int segmentG = 9;        // Segment G pin
const int digit1Pin = 10;      // Digit 1 pin
const int digit2Pin = 11;      // Digit 2 pin
const int buttonLedPin = 12;   // LED pin for button press indicator

// Define patterns for each digit (0-9) on a 7-segment display
const byte digitPatterns[10] = {
  B11111100, // 0
  B01100000, // 1
  B11011010, // 2
  B11110010, // 3
  B01100110, // 4
  B10110110, // 5
  B10111110, // 6
  B11100000, // 7
  B11111110, // 8
  B11110110  // 9
};

void setup() {
  // Initialize button and LED pins
  pinMode(buttonPin, INPUT);
  pinMode(buttonLedPin, OUTPUT);
  
  // Initialize seven-segment display pins as outputs
  pinMode(segmentA, OUTPUT);
  pinMode(segmentB, OUTPUT);
  pinMode(segmentC, OUTPUT);
  pinMode(segmentD, OUTPUT);
  pinMode(segmentE, OUTPUT);
  pinMode(segmentF, OUTPUT);
  pinMode(segmentG, OUTPUT);
  pinMode(digit1Pin, OUTPUT);
  pinMode(digit2Pin, OUTPUT);
  
  // Turn off all segments initially
  digitalWrite(segmentA, LOW);
  digitalWrite(segmentB, LOW);
  digitalWrite(segmentC, LOW);
  digitalWrite(segmentD, LOW);
  digitalWrite(segmentE, LOW);
  digitalWrite(segmentF, LOW);
  digitalWrite(segmentG, LOW);
}

void loop() {
  if (digitalRead(buttonPin) == HIGH) {
    // Button is pressed, simulate dice roll
    int diceValue = random(1, 7); // Generate a random number between 1 and 6
    
    // Display the dice value on the seven-segment display
    displayNumber(diceValue);
    
    // Blink the LED to indicate button press
    digitalWrite(buttonLedPin, HIGH);
    delay(100);
    digitalWrite(buttonLedPin, LOW);
    
    // Wait for a while before resetting
    delay(1000); // Adjust delay time as needed
  }
}

void displayNumber(int number) {
  // Display a number (0-9) on the two-digit seven-segment display
  if (number >= 0 && number <= 9) {
    // Get the corresponding pattern for the number
    byte pattern = digitPatterns[number];
    
    // Extract individual segments from the pattern
    digitalWrite(segmentA, bitRead(pattern, 0));
    digitalWrite(segmentB, bitRead(pattern, 1));
    digitalWrite(segmentC, bitRead(pattern, 2));
    digitalWrite(segmentD, bitRead(pattern, 3));
    digitalWrite(segmentE, bitRead(pattern, 4));
    digitalWrite(segmentF, bitRead(pattern, 5));
    digitalWrite(segmentG, bitRead(pattern, 6));
    
    // Enable the corresponding digit
    digitalWrite(digit1Pin, LOW);
    digitalWrite(digit2Pin, HIGH);
  }
}
