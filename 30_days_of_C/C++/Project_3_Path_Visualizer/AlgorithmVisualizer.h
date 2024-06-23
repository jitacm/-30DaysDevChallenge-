#ifndef ALGORITHM_VISUALIZER_H
#define ALGORITHM_VISUALIZER_H

#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <set>
#include <limits>
#include <algorithm>
#include "Graph.h"

class AlgorithmVisualizer {
public:
    AlgorithmVisualizer(const Graph& graph) : graph(graph) {}

    // Depth-First Search (DFS) Visualization
    void dfs(int startNode) {
        std::vector<bool> visited(graph.getNeighbors(startNode).size(), false);
        std::stack<int> stack;
        stack.push(startNode);

        std::cout << "DFS traversal: ";
        while (!stack.empty()) {
            int node = stack.top();
            stack.pop();

            if (!visited[node]) {
                visited[node] = true;
                std::cout << node << " ";

                for (auto it = graph.getNeighbors(node).rbegin(); it != graph.getNeighbors(node).rend(); ++it) {
                    if (!visited[*it]) {
                        stack.push(*it);
                    }
                }
            }
        }
        std::cout << std::endl;
    }

    // Breadth-First Search (BFS) Visualization
    void bfs(int startNode) {
        std::vector<bool> visited(graph.getNeighbors(startNode).size(), false);
        std::queue<int> queue;
        queue.push(startNode);
        visited[startNode] = true;

        std::cout << "BFS traversal: ";
        while (!queue.empty()) {
            int node = queue.front();
            queue.pop();
            std::cout << node << " ";

            for (int neighbor : graph.getNeighbors(node)) {
                if (!visited[neighbor]) {
                    queue.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        std::cout << std::endl;
    }

    // Dijkstra's Algorithm Visualization
    void dijkstra(int startNode) {
        std::vector<int> dist(graph.getNeighbors(startNode).size(), std::numeric_limits<int>::max());
        std::set<std::pair<int, int>> set;
        dist[startNode] = 0;
        set.insert({0, startNode});

        while (!set.empty()) {
            int node = set.begin()->second;
            set.erase(set.begin());

            for (int neighbor : graph.getNeighbors(node)) {
                int weight = 1; // Assuming all edges have weight 1
                if (dist[node] + weight < dist[neighbor]) {
                    set.erase({dist[neighbor], neighbor});
                    dist[neighbor] = dist[node] + weight;
                    set.insert({dist[neighbor], neighbor});
                }
            }
        }

        std::cout << "Dijkstra's shortest paths from node " << startNode << ": ";
        for (int i = 0; i < dist.size(); ++i) {
            std::cout << "Node " << i << " : " << dist[i] << " ";
        }
        std::cout << std::endl;
    }

    // A* Algorithm Visualization
    void aStar(int startNode, int goalNode) {
        std::vector<int> dist(graph.getNeighbors(startNode).size(), std::numeric_limits<int>::max());
        std::vector<int> heuristic(graph.getNeighbors(startNode).size(), 0); // Assuming 0 heuristic for simplicity
        std::set<std::pair<int, int>> set;
        dist[startNode] = 0;
        set.insert({0, startNode});

        while (!set.empty()) {
            int node = set.begin()->second;
            set.erase(set.begin());

            if (node == goalNode) break;

            for (int neighbor : graph.getNeighbors(node)) {
                int weight = 1; // Assuming all edges have weight 1
                int cost = dist[node] + weight + heuristic[neighbor];
                if (cost < dist[neighbor] + heuristic[neighbor]) {
                    set.erase({dist[neighbor] + heuristic[neighbor], neighbor});
                    dist[neighbor] = dist[node] + weight;
                    set.insert({dist[neighbor] + heuristic[neighbor], neighbor});
                }
            }
        }

        std::cout << "A* shortest path from node " << startNode << " to node " << goalNode << ": ";
        std::cout << "Node " << goalNode << " : " << dist[goalNode] << std::endl;
    }

private:
    const Graph& graph;
};

#endif // ALGORITHM_VISUALIZER_H
