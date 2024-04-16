public class Solution {
    public int lruCacheMisses(int num, List<Integer> pages, int maxCacheSize) {
        
        if (maxCacheSize == 0) return num; // If cache size is zero, all are misses

        List<Integer> lru = new LinkedList<Integer>();
        int misses = 0;
        
        for (Integer page: pages) {
            if (lru.size() >= maxCacheSize) {
                if (!lru.contains(page)) {
                    misses++;
                    lru.remove(lru.size() - 1);
                    lru.add(page);
                } else {
                    lru.remove(lru.indexOf(page));
                    lru.add(page);
                }
            } else {
                if (!lru.contains(page)) {
                    lru.add(page);
                    misses++;
                } else {
                    lru.remove(lru.indexOf(page));
                    lru.add(page);
                }
            }
        }
        return misses;
    }
}