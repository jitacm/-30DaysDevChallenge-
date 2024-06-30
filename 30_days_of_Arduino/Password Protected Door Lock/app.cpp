#include <Keypad.h>
#include <Servo.h>

// Define the number of rows and columns in the keypad
const byte ROWS = 4;
const byte COLS = 4;

// Define the keypad layout
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};

// Define the pin numbers connected to the keypad
byte rowPins[ROWS] = {9, 8, 7, 6};
byte colPins[COLS] = {5, 4, 3, 2};

// Create a Keypad object
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

// Define servo motor pin
const int servoPin = 12;

// Define servo motor object
Servo doorLock;

// Define password and variables
String password = "1234";
String enteredPassword = "";
bool doorLocked = true;

void setup() {
  // Start serial communication for debugging
  Serial.begin(9600);

  // Attach servo to the pin
  doorLock.attach(servoPin);

  // Initialize the servo motor position
  doorLock.write(0);

  // Print initial message to serial monitor
  Serial.println("Enter the password:");
}

void loop() {
  // Get key press from keypad
  char key = keypad.getKey();

  // Check if a key is pressed
  if (key) {
    // Handle key press
    Serial.print("Key pressed: ");
    Serial.println(key);

    // Handle numeric keys for password entry
    if (key >= '0' && key <= '9') {
      enteredPassword += key;
    }

    // Handle '*' key for clearing the entered password
    if (key == '*') {
      enteredPassword = "";
    }

    // Check if '#' key is pressed to verify password
    if (key == '#') {
      if (enteredPassword == password) {
        // Correct password entered, unlock the door
        Serial.println("Password accepted - Door unlocked!");
        doorLock.write(90); // Unlock position
        delay(2000); // Allow time to open door
        doorLock.write(0); // Lock position
        enteredPassword = ""; // Clear entered password
      } else {
        // Incorrect password entered, keep the door locked
        Serial.println("Incorrect password - Door remains locked.");
        enteredPassword = ""; // Clear entered password
      }
    }
  }
}
