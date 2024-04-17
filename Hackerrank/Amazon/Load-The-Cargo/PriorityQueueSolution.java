import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public int loadTheCargo(int num, int[] containers, int itemSize, int[] itemsPerContainer, long cargoSize) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        
        
        for (int i = 0; i < num; i++) {
            maxHeap.add(new int[]{containers[i], itemsPerContainer[i]});
        }
        
        int itemCount = 0;
        
        while (cargoSize > 0 && !maxHeap.isEmpty()) {
            int[] container = maxHeap.poll();
            itemCount += Math.min(container[0], cargoSize) * container[1];
            cargoSize -= container[0];
        }
        
        return itemCount;
        
    }
}
