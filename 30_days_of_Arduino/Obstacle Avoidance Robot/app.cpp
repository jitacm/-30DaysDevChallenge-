#include <NewPing.h>

// Define ultrasonic sensor pins
#define TRIGGER_PIN  12  // Arduino pin tied to trigger pin on the ultrasonic sensor.
#define ECHO_PIN     11  // Arduino pin tied to echo pin on the ultrasonic sensor.
#define MAX_DISTANCE 200 // Maximum distance we want to ping for (in centimeters). Maximum sensor distance is rated at 400-500cm.

// Define motor control pins
#define MOTOR_LEFT_PIN1   4  // Motor A direction pin
#define MOTOR_LEFT_PIN2   5  // Motor A PWM pin
#define MOTOR_RIGHT_PIN1  7  // Motor B direction pin
#define MOTOR_RIGHT_PIN2  6  // Motor B PWM pin

// Define motor speeds
#define MOTOR_SPEED_FWD   200 // Motor speed forward
#define MOTOR_SPEED_REV   200 // Motor speed reverse
#define MOTOR_SPEED_TURN  150 // Motor speed for turning

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); // NewPing setup of pins and maximum distance.

void setup() {
  // Motor control pins as outputs
  pinMode(MOTOR_LEFT_PIN1, OUTPUT);
  pinMode(MOTOR_LEFT_PIN2, OUTPUT);
  pinMode(MOTOR_RIGHT_PIN1, OUTPUT);
  pinMode(MOTOR_RIGHT_PIN2, OUTPUT);
}

void loop() {
  delay(50); // Wait 50ms between pings (about 20 pings/sec). 29ms should be the shortest delay between pings.

  // Measure distance from the ultrasonic sensor
  unsigned int distance = sonar.ping_cm();

  // Check if there's an obstacle within 20cm
  if (distance > 0 && distance < 20) {
    // Obstacle detected, reverse and turn
    moveBackward();
    delay(500); // Reverse for 0.5 seconds
    turnLeft();
    delay(1000); // Turn left for 1 second
  } else {
    // No obstacle, move forward
    moveForward();
  }
}

void moveForward() {
  // Set motor directions for forward motion
  digitalWrite(MOTOR_LEFT_PIN1, LOW);
  digitalWrite(MOTOR_LEFT_PIN2, HIGH);
  digitalWrite(MOTOR_RIGHT_PIN1, LOW);
  digitalWrite(MOTOR_RIGHT_PIN2, HIGH);

  // Set motor speeds
  analogWrite(MOTOR_LEFT_PIN2, MOTOR_SPEED_FWD);
  analogWrite(MOTOR_RIGHT_PIN2, MOTOR_SPEED_FWD);
}

void moveBackward() {
  // Set motor directions for backward motion
  digitalWrite(MOTOR_LEFT_PIN1, HIGH);
  digitalWrite(MOTOR_LEFT_PIN2, LOW);
  digitalWrite(MOTOR_RIGHT_PIN1, HIGH);
  digitalWrite(MOTOR_RIGHT_PIN2, LOW);

  // Set motor speeds
  analogWrite(MOTOR_LEFT_PIN2, MOTOR_SPEED_REV);
  analogWrite(MOTOR_RIGHT_PIN2, MOTOR_SPEED_REV);
}

void turnLeft() {
  // Set motor directions for left turn
  digitalWrite(MOTOR_LEFT_PIN1, HIGH);
  digitalWrite(MOTOR_LEFT_PIN2, LOW);
  digitalWrite(MOTOR_RIGHT_PIN1, LOW);
  digitalWrite(MOTOR_RIGHT_PIN2, HIGH);

  // Set motor speeds
  analogWrite(MOTOR_LEFT_PIN2, MOTOR_SPEED_TURN);
  analogWrite(MOTOR_RIGHT_PIN2, MOTOR_SPEED_TURN);
}
