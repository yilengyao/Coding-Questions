class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
        Set<Integer> courses = new HashSet<>();
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int[] prerequisite: prerequisites) {
            adjacencyList.computeIfAbsent(prerequisite[0], (i) -> new HashSet<>()).add(prerequisite[1]);
            courses.add(prerequisite[0]);
            courses.add(prerequisite[1]);
        }
        Deque<Integer> seen = new ArrayDeque<>();
        Deque<Integer> topSort = new ArrayDeque<>();

        for (int course: courses) {
            // System.out.println(course);
            // System.out.println(topSort);
            if(!topSort.contains(course)) {
                if (!DFS(adjacencyList, course, seen, topSort)) {
                    return new int[0];
                }
            }
        }
        Set<Integer> bag = new HashSet();
        for (int j = 0; j < numCourses; j++) {
            bag.add(j);
        }
        for(int element: bag) {
            if (!topSort.contains(element)) {
                topSort.push(element);
            }
        }
int[] result = new int[numCourses];
        int i = 0;
        for (Integer num : topSort) {
            result[i++] = num;
        } 
     
        return result;
    }

    boolean DFS(Map<Integer, Set<Integer>> adjacencyList, int course, Deque<Integer> seen, Deque<Integer> topSort) {
        // System.out.println(course);
        if (seen.contains(course)) {
            return false;
        }

        seen.push(course);
        
        if (adjacencyList.containsKey(course)) {
            for (int prerequisite: adjacencyList.get(course)) {
                if (!DFS(adjacencyList, prerequisite, seen, topSort)) {
                    return false;
                }
            }
        } 
        
        // System.out.println(seen);
        int toAdd = seen.pop();
        if (!topSort.contains(toAdd)) {
            topSort.add(toAdd);
        }

        return true;
    }
}