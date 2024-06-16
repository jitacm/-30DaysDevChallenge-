#ifndef GRAPH_H
#define GRAPH_H

#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <fstream>
#include <sstream>
#include <stdexcept>
#include <string>
#include <utility> // for std::pair
#include <queue>
#include <stack>
#include <set>

using namespace std;

class Graph {
private:
    unordered_map<int, vector<int>> adjList;
    unordered_map<int, int> nodeIndices;

public:
    void addNode(int node) {
        if (adjList.find(node) == adjList.end()) {
            adjList[node] = vector<int>();
            nodeIndices[node] = adjList.size() - 1;
        }
    }

    void removeNode(int node) {
        if (adjList.find(node) == adjList.end())
            return;

        adjList.erase(node);
        nodeIndices.erase(node);

        // Update indices
        for (auto& it : nodeIndices) {
            if (it.second > nodeIndices[node])
                it.second--;
        }

        // Remove edges associated with the node
        for (auto& it : adjList) {
            auto& neighbors = it.second;
            neighbors.erase(remove(neighbors.begin(), neighbors.end(), node), neighbors.end());
        }
    }

    void addEdge(int node1, int node2) {
        if (adjList.find(node1) == adjList.end() || adjList.find(node2) == adjList.end())
            throw invalid_argument("One or both nodes do not exist.");

        adjList[node1].push_back(node2);
        adjList[node2].push_back(node1);
    }

    void removeEdge(int node1, int node2) {
        if (adjList.find(node1) == adjList.end() || adjList.find(node2) == adjList.end())
            throw invalid_argument("One or both nodes do not exist.");

        auto it1 = find(adjList[node1].begin(), adjList[node1].end(), node2);
        if (it1 != adjList[node1].end())
            adjList[node1].erase(it1);

        auto it2 = find(adjList[node2].begin(), adjList[node2].end(), node1);
        if (it2 != adjList[node2].end())
            adjList[node2].erase(it2);
    }

    void displayAdjList() const {
        for (const auto& pair : adjList) {
            int node = pair.first;
            const auto& neighbors = pair.second;
            cout << node << ": ";
            for (int neighbor : neighbors) {
                cout << neighbor << " ";
            }
            cout << endl;
        }
    }

    void displayAdjMatrix() const {
        int n = adjList.size();
        vector<vector<int>> adjMatrix(n, vector<int>(n, 0));

        for (const auto& pair : adjList) {
            int node = pair.first;
            const auto& neighbors = pair.second;
            for (int neighbor : neighbors) {
                adjMatrix[node][neighbor] = 1;
            }
        }

        for (const auto& row : adjMatrix) {
            for (int val : row) {
                cout << val << " ";
            }
            cout << endl;
        }
    }

    void dfs(int start) const {
        set<int> visited;
        stack<int> s;
        s.push(start);

        while (!s.empty()) {
            int node = s.top();
            s.pop();

            if (visited.find(node) == visited.end()) {
                cout << node << " ";
                visited.insert(node);

                for (int neighbor : adjList.at(node)) {
                    if (visited.find(neighbor) == visited.end()) {
                        s.push(neighbor);
                    }
                }
            }
        }
        cout << endl;
    }

    void bfs(int start) const {
        set<int> visited;
        queue<int> q;
        q.push(start);

        while (!q.empty()) {
            int node = q.front();
            q.pop();

            if (visited.find(node) == visited.end()) {
                cout << node << " ";
                visited.insert(node);

                for (int neighbor : adjList.at(node)) {
                    if (visited.find(neighbor) == visited.end()) {
                        q.push(neighbor);
                    }
                }
            }
        }
        cout << endl;
    }

    void visualize(const string& filename) const {
        ofstream file(filename);
        file << "graph G {\n";
        for (const auto& pair : adjList) {
            int node = pair.first;
            const auto& neighbors = pair.second;
            for (int neighbor : neighbors) {
                if (node < neighbor) {
                    file << "  " << node << " -- " << neighbor << ";\n";
                }
            }
        }
        file << "}\n";
        file.close();
    }
};

#endif // GRAPH_H
