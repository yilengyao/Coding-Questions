import java.util.*;

class Graphs {

    Map<String, Set<String>> adjacencyList;

    public Graphs(String[][] edges) {
        this.adjacencyList = new HashMap<>();

        for (String[] edge: edges) {
            this.adjacencyList.computeIfAbsent(edge[0], (i) -> new HashSet<>()).add(edge[1]);
            this.adjacencyList.computeIfAbsent(edge[1], (i) -> new HashSet<>()).add(edge[0]);

        }
    }

    public void addEdge(String node1, String node2) {
        this.adjacencyList.computeIfAbsent(node1, (i) -> new HashSet<>()).add(node2);
        this.adjacencyList.computeIfAbsent(node2, (i) -> new HashSet<>()).add(node1);
    }

    public List<String> findShortestPath(String node1, String node2) {
        Map<String, String> reversePath = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(node1);

        while(!queue.isEmpty()){
            String currentNode = queue.poll();
            Set<String> adjacentNodes = this.adjacencyList.getOrDefault(currentNode, new HashSet<>());
            
            for (String adjacentNode: adjacentNodes) {
                if (!reversePath.keySet().contains(adjacentNode)) {
                        reversePath.put(adjacentNode, currentNode);
                        queue.offer(adjacentNode);
                }
            }
        }

        List<String> path = new ArrayList<>();
        if (reversePath.keySet().contains(node2)) {
            path.add(node2);
            while (!node2.equals(node1)) {
                node2 = reversePath.get(node2);
                path.add(node2);
            }
            Collections.reverse(path);
            return path;
        } else {
            return path;
        }
    }

    public List<String> findPath(String node1, String node2) {
        Deque<String> path = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        DFS(this.adjacencyList, node1, node2, path);
        while(!path.isEmpty()) {
            result.add(path.poll());
        }
        return result;
    }

    private boolean DFS(Map<String, Set<String>> adjacencyList, String sourceNode, String destinationNode, Deque<String> path) {
        if (path.contains(sourceNode)) {
            return false;
        }

        path.addLast(sourceNode);

        if (sourceNode.equals(destinationNode)) {
            return true;
        }

        Set<String> adjacentNodes = adjacencyList.getOrDefault(sourceNode, Set.of());
    
        for(String adjacentNode: adjacentNodes) {
            if (DFS(adjacencyList, adjacentNode, destinationNode, path)) {
                return true;
            }
        }
        path.removeLast();
        return false;
                
    }

}