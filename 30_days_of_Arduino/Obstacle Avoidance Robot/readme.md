# Obstacle Avoidance Robot Project

## Description
Build a robot that can autonomously avoid obstacles using Arduino and an ultrasonic sensor. The robot moves forward until it detects an obstacle within 20cm, then reverses and turns left to avoid it.

## Components Needed
- Arduino board (e.g., Uno, Nano)
- Ultrasonic sensor (HC-SR04 or similar)
- Robot chassis with motors and wheels
- Motor driver module (L298N or similar)
- Jumper wires
- Breadboard (if needed for prototyping)

## Circuit Diagram
Include a circuit diagram image here if you create one.

## Instructions
1. **Setup**: Connect the ultrasonic sensor, motors, motor driver, and Arduino as shown in the circuit diagram.
2. **Upload Code**: Upload the provided Arduino code (`obstacle_avoidance_robot.ino`) to your Arduino board.
3. **Run**: Place the robot in an open space and power it on. It will move forward and autonomously avoid obstacles detected by the ultrasonic sensor.

## Additional Notes
- Adjust the distance thresholds and motor speeds (`MOTOR_SPEED_FWD`, `MOTOR_SPEED_REV`, `MOTOR_SPEED_TURN`) in the Arduino code to optimize robot performance.
- Ensure the ultrasonic sensor is positioned correctly to detect obstacles effectively.

Enhance the project by adding features such as obstacle detection using multiple sensors, wireless control, or mapping functionality.

Have fun building and experimenting with your obstacle avoidance robot!
