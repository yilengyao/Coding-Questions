import java.util.*;
import java.io.File;     // Import for handling files
import java.io.FileNotFoundException;  // Import for handling the file not found exception
import java.util.function.Function;

class Solution {
    public static class Node<T> {
        public T val;
        public List<Node<T>> children;

        public Node(T val) {
            this(val, new ArrayList<>());
        }

        public Node(T val, List<Node<T>> children) {
            this.val = val;
            this.children = children;
        }
    }

    static class NodeEntry implements Comparable<NodeEntry> {
        double average;
        Node node;
        
        public NodeEntry(double average, Node node) {
            this.average = average;
            this.node = node;
        }
        
        @Override
        public int compareTo(NodeEntry other) {
            return Double.compare(other.average, this.average);
        }
    }
    
    public static int subtreeMaxAvg(Node<Integer> root) {
        // WRITE YOUR BRILLIANT CODE HERE
        PriorityQueue<NodeEntry> maxHeap = new PriorityQueue<>();
        
        subtreeMaxAvgHelper(root, maxHeap);
        
        Node result = maxHeap.poll().node;

        return (int) result.val;
    }
    
    public static int[] subtreeMaxAvgHelper(Node<Integer> node, PriorityQueue<NodeEntry> maxHeap) {
        int[] state = new int[2];
        for(Node<Integer> child: node.children) {
            int[] r = subtreeMaxAvgHelper(child, maxHeap);
            state[0] += r[0];
            state[1] += r[1];
        }
        
        state[0] += (int) node.val;
        state[1]++;
        
        double average = 1.0 * state[0]/ state[1];
        
        maxHeap.add(new NodeEntry(average, node));
        return state;
    }

    // this function builds a tree from input; you don't have to modify it
    // learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        int num = Integer.parseInt(iter.next());
        ArrayList<Node<T>> children = new ArrayList<>();
        for (int i = 0; i < num; i++)
            children.add(buildTree(iter, f));
        return new Node<T>(f.apply(val), children);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        File file = new File("test.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Input:")) {
                    String inputData = line.substring(6).trim(); // Remove "Input:" prefix
                    Node<Integer> root = buildTree(splitWords(inputData).iterator(), Integer::parseInt);
                    int res = subtreeMaxAvg(root);
                    String outputLine = scanner.nextLine().trim(); // Read the expected output line
                    int expectedOutput = Integer.parseInt(outputLine.substring(7).trim()); // Remove "Output:" prefix
                    System.out.println("Computed Output: " + res);
                    System.out.println("Expected Output: " + expectedOutput);
                    System.out.println(res == expectedOutput ? "Test Passes" : "Test Fails");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

}
