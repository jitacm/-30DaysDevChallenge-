# Pathfinding Visualizer

## Overview

The Pathfinding Visualizer is an application designed to visualize graph traversal algorithms and calculate paths within a graph. It supports various algorithms such as Depth-First Search (DFS), Breadth-First Search (BFS), Dijkstra's algorithm, A*, and more. This tool aims to provide a clear visual representation of how these algorithms work and helps in understanding their behavior by showing the shortest and longest path calculations.

## Features

- **Visual Representation of Algorithms**: Visually demonstrate the functioning of various graph traversal algorithms including DFS, BFS, Dijkstra's algorithm, A*, and others as required.
- **Path Optimization**: Implement and visualize path selection optimization techniques.
- **Shortest and Longest Path Calculation**: Calculate and display the shortest and longest paths in a graph.
- **Path Visualization**: Display the optimized paths on the graph for easy understanding.

## Project Structure

```
PathfindingVisualizer/
src/
├── main.cpp
├── Graph.h
├── Graph.cpp
├── AlgorithmVisualizer.h
├── AlgorithmVisualizer.cpp
```

## Getting Started

### Prerequisites

- **C++17** or higher
- **Graphviz** (for visualizing graphs in DOT format)

### Installation

1. **Clone the Repository**
    ```sh
    git clone https://github.com/yourusername/PathfindingVisualizer.git
    cd PathfindingVisualizer
    ```

2. **Build the Project**
    Use CMake to build the project.
    ```sh
    mkdir build
    cd build
    cmake ..
    make
    ```

3. **Run the Application**
    ```sh
    ./PathfindingVisualizer
    ```

