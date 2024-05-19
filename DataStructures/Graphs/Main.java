/**
# Define the graph data structure that is acyclic, non directed and non weighted

# The nodes of the graph should store letters “A”, “B”, … “Z”

# On the defined graph structure implement an algorithm that would find the shortest path between two nodes

# If you call the function findPath(“A”, “D”) it should return a string containing the path “ABCD” assuming that between node A and D are nodes B and C
*/

class Main {

    public static void main(String[] args) {
        System.out.println("hello world");
        String[][] edges = new String[][]{{"A","B"}};
        Graphs graphs = new Graphs(edges);
        System.out.println(graphs.adjacencyList);
        System.out.println(graphs.findPath("A", "B"));
        System.out.println(graphs.findShortestPath("A", "B"));

        graphs.addEdge("B", "C");
        graphs.addEdge("C", "D");
        System.out.println(graphs.findPath("A", "D"));
        System.out.println(graphs.findShortestPath("A", "D"));

    }
}