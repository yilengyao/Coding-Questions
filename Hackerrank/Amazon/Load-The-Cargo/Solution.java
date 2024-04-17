import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public int loadTheCargo(int num, int[] containers, int itemSize, int[] itemsPerContainer, long cargoSize) {
        List<Integer> containerAndItem = new ArrayList<>();
        
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < containers[i]; j++) {
                containerAndItem.add(itemsPerContainer[i]);
            }
        }
        int result = 0;
        Collections.sort(containerAndItem);
        int totalSize = containerAndItem.size() - 1;
        while (cargoSize > 0 && totalSize >= 0) {
            result += containerAndItem.get(totalSize);
            totalSize--;
            cargoSize--;
        }
        return result;
    }
}