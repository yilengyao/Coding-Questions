class LRUCache {
    int capacity;
    HashMap<Integer, Integer> cache;
    Queue<Integer> fifo;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.fifo = new LinkedList<>();    
    }
    
    public int get(int key) {
        int value = this.cache.getOrDefault(key, -1);
        if (value != -1) {
            this.update(key, value);
        }
        return value;
    }
    
    public void put(int key, int value) {
        /**
            Key Exists in cache
                - update, cache and fifo (done)
            Does not Exist in cache
                - at capacity
                    - remove last element of fifo, from fifo and cache 
                    - add new element
                - not at capacity
                    - add to cache and and fifo
         */
        if (this.get(key) == -1) {
            if (this.fifo.size() >= capacity) {
                this.deleteLast(key);
                this.add(key, value);
            } else {
                 this.add(key, value);
            }
        } else {
            this.cache.put(key, value);
        }
    }

    void update(int key, int value) {
        this.fifo.remove(key);
        this.add(key, value);
    }

    void deleteLast(int key){
        this.cache.remove(this.fifo.poll());
    }

    void add(int key, int value) {
        this.fifo.add(key);
        this.cache.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */