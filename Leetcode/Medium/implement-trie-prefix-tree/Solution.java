class Trie {
    static class Node {
        Map<Character, Node> children;
        boolean terminal;

        public Node() {
            this.children = new HashMap<>();
            this.terminal = false;
        }
    }

    private final Node root;

    public Trie() {
        this.root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
        for (char ch: word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new Node());
            }
            node = node.children.get(ch);
        }
        node.terminal = true;
    }
    
    public boolean search(String word) {
       Node node = root;
        for (char ch: word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.terminal;
    }
    
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char ch: prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */