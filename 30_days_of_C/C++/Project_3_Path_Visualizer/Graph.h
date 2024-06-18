#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

class Graph {
public:
    Graph(int nodes) : adjList(nodes) {}

    void addEdge(int u, int v) {
        adjList[u].push_back(v);
        adjList[v].push_back(u); // For undirected graph
    }

    void removeEdge(int u, int v) {
        auto it = std::find(adjList[u].begin(), adjList[u].end(), v);
        if (it != adjList[u].end()) adjList[u].erase(it);

        it = std::find(adjList[v].begin(), adjList[v].end(), u);
        if (it != adjList[v].end()) adjList[v].erase(it);
    }

    void displayAdjList() const {
        for (int i = 0; i < adjList.size(); ++i) {
            std::cout << i << ": ";
            for (int neighbor : adjList[i]) {
                std::cout << neighbor << " ";
            }
            std::cout << std::endl;
        }
    }

    const std::vector<int>& getNeighbors(int node) const {
        return adjList[node];
    }

private:
    std::vector<std::vector<int>> adjList;
};

#endif // GRAPH_H
