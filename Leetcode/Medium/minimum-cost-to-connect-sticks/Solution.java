class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int stick: sticks) {
            minHeap.offer(stick);
        }
        int cost = 0;
        while(minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            cost += sum;
            minHeap.offer(sum);
        }

        return cost;
    }
}