#include <Keypad.h>
#include <toneAC.h>  // Library for piezo buzzer

// Define the keypad layout and pins
const byte ROWS = 4;
const byte COLS = 4;
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};
byte rowPins[ROWS] = {9, 8, 7, 6}; // Connect to the row pinouts of the keypad
byte colPins[COLS] = {5, 4, 3, 2}; // Connect to the column pinouts of the keypad

// Initialize Keypad object
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

// Piezo buzzer pin
const int buzzerPin = 10; // Connect piezo buzzer to pin 10

// Define alarm state and code
bool alarmActive = false;
const char* alarmCode = "1234"; // Change this to your desired alarm code

void setup() {
  // Initialize Serial communication for debugging
  Serial.begin(9600);

  // Initialize piezo buzzer
  pinMode(buzzerPin, OUTPUT);

  // Print initial message
  Serial.println("Enter alarm code:");
}

void loop() {
  // Listen for keypad input
  char key = keypad.getKey();

  // Check if a key is pressed
  if (key) {
    Serial.print("Key pressed: ");
    Serial.println(key);

    // Check if the key matches the alarm code
    if (alarmActive && key == '#') {
      deactivateAlarm();
    } else if (!alarmActive && key == '#') {
      activateAlarm();
    } else if (alarmActive && key != '#') {
      // Check entered code
      checkAlarmCode(key);
    }
  }
}

void activateAlarm() {
  alarmActive = true;
  Serial.println("Alarm activated. Enter code to deactivate.");
  toneAC(buzzerPin, 3000); // Play a tone indicating alarm activation
}

void deactivateAlarm() {
  alarmActive = false;
  Serial.println("Alarm deactivated.");
  noToneAC(buzzerPin); // Stop the tone
}

void checkAlarmCode(char key) {
  static int index = 0;
  static char enteredCode[5] = "";

  // Add the key to the entered code
  enteredCode[index++] = key;
  enteredCode[index] = '\0'; // Null-terminate the string

  // Check if the entered code matches the alarm code
  if (strcmp(enteredCode, alarmCode) == 0) {
    Serial.println("Code accepted. Alarm deactivated.");
    deactivateAlarm();
  } else if (index >= 4) {
    Serial.println("Incorrect code. Alarm remains active.");
    index = 0;
  }
}
