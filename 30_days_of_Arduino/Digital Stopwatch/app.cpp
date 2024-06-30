#include <LiquidCrystal.h>

// Define LCD pins
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

// Define push button pins
const int startStopButtonPin = 7;
const int resetButtonPin = 6;

// Define variables
unsigned long startTime = 0;
unsigned long elapsedTime = 0;
bool running = false;

void setup() {
  // Initialize LCD
  lcd.begin(16, 2);
  lcd.clear();

  // Initialize push buttons
  pinMode(startStopButtonPin, INPUT_PULLUP);
  pinMode(resetButtonPin, INPUT_PULLUP);

  // Print initial message
  lcd.print("  Digital");
  lcd.setCursor(0, 1);
  lcd.print(" Stopwatch");
  delay(2000);
  lcd.clear();
}

void loop() {
  // Check if start/stop button is pressed
  if (digitalRead(startStopButtonPin) == LOW) {
    if (!running) {
      // Start the stopwatch
      startTime = millis() - elapsedTime;
      running = true;
    } else {
      // Stop the stopwatch
      elapsedTime = millis() - startTime;
      running = false;
    }
    delay(200); // Debounce delay
  }

  // Check if reset button is pressed
  if (digitalRead(resetButtonPin) == LOW) {
    // Reset the stopwatch
    startTime = millis();
    elapsedTime = 0;
    running = false;
    lcd.clear();
    lcd.print("  Stopwatch");
    delay(1000);
    lcd.clear();
  }

  // Display elapsed time on LCD
  lcd.setCursor(0, 0);
  lcd.print("Time: ");
  lcd.print(formatTime(elapsedTime / 1000));

  // Update LCD every second while running
  if (running) {
    lcd.setCursor(0, 1);
    lcd.print("Running...");
  } else {
    lcd.setCursor(0, 1);
    lcd.print("Stopped");
  }

  delay(500); // Update LCD every 0.5 seconds
}

String formatTime(unsigned long seconds) {
  String formattedTime = "";
  int hours = seconds / 3600;
  int minutes = (seconds % 3600) / 60;
  int secs = seconds % 60;

  if (hours < 10) {
    formattedTime += "0";
  }
  formattedTime += hours;
  formattedTime += ":";

  if (minutes < 10) {
    formattedTime += "0";
  }
  formattedTime += minutes;
  formattedTime += ":";

  if (secs < 10) {
    formattedTime += "0";
  }
  formattedTime += secs;

  return formattedTime;
}
