#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <stack>
#include <fstream>

class Graph {
private:
    std::unordered_map<int, std::vector<int>> adjList;
    std::vector<std::vector<int>> adjMatrix;
    std::unordered_map<int, int> nodeIndices;

public:
    Graph() {}

    void addNode(int node) {
        if (adjList.find(node) != adjList.end()) {
            std::cout << "Node " << node << " already exists." << std::endl;
            return;
        }
        adjList[node] = {};
        nodeIndices[node] = adjMatrix.size();
        for (auto& row : adjMatrix) {
            row.push_back(0);
        }
        adjMatrix.push_back(std::vector<int>(adjMatrix.size() + 1, 0));
    }

    void removeNode(int node) {
        if (adjList.find(node) == adjList.end()) {
            std::cout << "Node " << node << " does not exist." << std::endl;
            return;
        }
        adjList.erase(node);
        int index = nodeIndices[node];
        adjMatrix.erase(adjMatrix.begin() + index);
        for (auto& row : adjMatrix) {
            row.erase(row.begin() + index);
        }
        nodeIndices.erase(node);
        for (auto& [n, i] : nodeIndices) {
            if (i > index) {
                --i;
            }
        }
    }

    void addEdge(int node1, int node2) {
        if (adjList.find(node1) == adjList.end() || adjList.find(node2) == adjList.end()) {
            std::cout << "One or both nodes do not exist." << std::endl;
            return;
        }
        adjList[node1].push_back(node2);
        adjList[node2].push_back(node1);
        adjMatrix[nodeIndices[node1]][nodeIndices[node2]] = 1;
        adjMatrix[nodeIndices[node2]][nodeIndices[node1]] = 1;
    }

    void removeEdge(int node1, int node2) {
        if (adjList.find(node1) == adjList.end() || adjList.find(node2) == adjList.end()) {
            std::cout << "One or both nodes do not exist." << std::endl;
            return;
        }
        auto it1 = std::find(adjList[node1].begin(), adjList[node1].end(), node2);
        if (it1 != adjList[node1].end()) {
            adjList[node1].erase(it1);
        }
        auto it2 = std::find(adjList[node2].begin(), adjList[node2].end(), node1);
        if (it2 != adjList[node2].end()) {
            adjList[node2].erase(it2);
        }
        adjMatrix[nodeIndices[node1]][nodeIndices[node2]] = 0;
        adjMatrix[nodeIndices[node2]][nodeIndices[node1]] = 0;
    }

    void displayAdjList() const {
        for (const auto& [node, neighbors] : adjList) {
            std::cout << node << ": ";
            for (const auto& neighbor : neighbors) {
                std::cout << neighbor << " ";
            }
            std::cout << std::endl;
        }
    }

    void displayAdjMatrix() const {
        for (const auto& row : adjMatrix) {
            for (const auto& val : row) {
                std::cout << val << " ";
            }
            std::cout << std::endl;
        }
    }

    void visualize(const std::string& filename) const {
        std::ofstream file(filename);
        file << "graph G {\n";
        for (const auto& [node, neighbors] : adjList) {
            for (const auto& neighbor : neighbors) {
                file << "  " << node << " -- " << neighbor << ";\n";
            }
        }
        file << "}\n";
        file.close();
        std::string command = "dot -Tpng " + filename + " -o graph.png";
        system(command.c_str());
    }

    void dfs(int start) const {
        std::unordered_set<int> visited;
        std::stack<int> stack;
        stack.push(start);
        while (!stack.empty()) {
            int node = stack.top();
            stack.pop();
            if (visited.find(node) == visited.end()) {
                std::cout << node << " ";
                visited.insert(node);
                for (const auto& neighbor : adjList.at(node)) {
                    stack.push(neighbor);
                }
            }
        }
        std::cout << std::endl;
    }

    void bfs(int start) const {
        std::unordered_set<int> visited;
        std::queue<int> queue;
        visited.insert(start);
        queue.push(start);
        while (!queue.empty()) {
            int node = queue.front();
            queue.pop();
            std::cout << node << " ";
            for (const auto& neighbor : adjList.at(node)) {
                if (visited.find(neighbor) == visited.end()) {
                    visited.insert(neighbor);
                    queue.push(neighbor);
                }
            }
        }
        std::cout << std::endl;
    }
};

#endif // GRAPH_H
