# Flutter Splash Screen Example

This is a simple Flutter application that demonstrates how to implement a splash screen that transitions to a home page after a few seconds.

## Features

- Splash screen with a logo, progress indicator, and welcome text.
- Automatically navigates to the home page after 3 seconds.

## Screenshots

### Splash Screen
![Splash Screen](assets/splash_screen.png)

### Home Page
![Home Page](assets/home_page.png)

## Getting Started

### Prerequisites

- Flutter installed on your machine. For installation instructions, visit the [official Flutter website](https://flutter.dev/docs/get-started/install).

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/flutter_splash_screen_example.git
    ```
2. Navigate to the project directory:
    ```sh
    cd flutter_splash_screen_example
    ```
3. Install the dependencies:
    ```sh
    flutter pub get
    ```
4. Run the app:
    ```sh
    flutter run
    ```

## Code Overview

### SplashScreen

The `SplashScreen` is a stateful widget that displays a logo, a circular progress indicator, and a welcome text. After 3 seconds, it navigates to the `HomePage`.

```dart
import 'package:flutter/material.dart';
import 'dart:async';

