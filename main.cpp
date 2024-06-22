#include <iostream>
#include "Graph.h"

using namespace std;

int main() {
    Graph g;
    
    g.addNode(0);
    g.addNode(1);
    g.addNode(2);
    g.addNode(3);
    
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    
    cout << "Adjacency List:" << endl;
    g.displayAdjList();
    
    cout << "\nAdjacency Matrix:" << endl;
    g.displayAdjMatrix();
    
    cout << "\nDFS traversal starting from node 0:" << endl;
    g.dfs(0);
    
    cout << "\nBFS traversal starting from node 0:" << endl;
    g.bfs(0);
    
    g.visualize("graph.dot");

    return 0;
}
