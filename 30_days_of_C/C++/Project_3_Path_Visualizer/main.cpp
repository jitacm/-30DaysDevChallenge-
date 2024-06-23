#include <iostream>
#include "Graph.h"
#include "AlgorithmVisualizer.h"

int main() {
    Graph g(6); // Create a graph with 6 nodes

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 4);
    g.addEdge(3, 4);
    g.addEdge(3, 5);
    g.addEdge(4, 5);

    std::cout << "Adjacency List:" << std::endl;
    g.displayAdjList();
    std::cout << std::endl;

    AlgorithmVisualizer av(g);

    std::cout << "Running DFS:" << std::endl;
    av.dfs(0);
    std::cout << std::endl;

    std::cout << "Running BFS:" << std::endl;
    av.bfs(0);
    std::cout << std::endl;

    std::cout << "Running Dijkstra:" << std::endl;
    av.dijkstra(0);
    std::cout << std::endl;

    std::cout << "Running A*:" << std::endl;
    av.aStar(0, 5);
    std::cout << std::endl;

    return 0;
}
