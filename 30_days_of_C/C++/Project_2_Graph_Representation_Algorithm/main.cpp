#include <iostream>
#include "Graph.h"

int main() {
    Graph g;
    g.addNode(1);
    g.addNode(2);
    g.addNode(3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(1, 3);

    std::cout << "Adjacency List:" << std::endl;
    g.displayAdjList();

    std::cout << "Adjacency Matrix:" << std::endl;
    g.displayAdjMatrix();

    std::cout << "DFS from node 1:" << std::endl;
    g.dfs(1);

    std::cout << "BFS from node 1:" << std::endl;
    g.bfs(1);

    g.visualize("graph.dot");

    return 0;
}
