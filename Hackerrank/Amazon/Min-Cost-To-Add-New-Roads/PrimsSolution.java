class Solution {
    public int minimumCost(int n, int[][] connections) {
        if (n == 1) return 0;
        Map<Integer, List<int[]>> adjacentMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) ->Integer.compare(a[1], b[1]));  
        for (int[] connection: connections) {
            int nodeI = connection[0];
            int nodeJ = connection[1];
            int weight = connection[2];
            adjacentMap.computeIfAbsent(nodeI, i -> new ArrayList<>()).add(new int[]{nodeJ, weight});
            adjacentMap.computeIfAbsent(nodeJ, i -> new ArrayList<>()).add(new int[]{nodeI, weight});
        }

        minHeap.offer(new int[]{connections[0][0], 0});
        int minCost = 0;
        while(!minHeap.isEmpty()) {
            int[] currentEdge = minHeap.poll();
            int currentNode = currentEdge[0];
            int currentWeight = currentEdge[1];
            if (!visited.contains(currentNode)) {
            minCost += currentWeight;
            visited.add(currentNode);
            List<int[]> adjacentEdges = adjacentMap.get(currentNode);

            for (int[] adjacentEdge: adjacentEdges) {
                if (!visited.contains(adjacentEdge[0])) {
                    minHeap.offer(adjacentEdge);
                }
            }
            }
        
        }
        if (n != visited.size()) {
            return -1;
        }
        return minCost;
    }
}