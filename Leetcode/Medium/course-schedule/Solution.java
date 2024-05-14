class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;

        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        Set<Integer> courses = new HashSet<>();
        Set<Integer> cycles = new HashSet<>();

        for (int[] prerequisite: prerequisites) {
            adjacencyList.computeIfAbsent(prerequisite[0], i -> new HashSet<>()).add(prerequisite[1]);
            courses.add(prerequisite[0]);
            courses.add(prerequisite[1]);
        }

        Set<Integer> good = new HashSet<>();

        for (int course: courses) {
            if (!good.contains(course)) {
                Deque<Integer> seen = new ArrayDeque<>();
                Set<Integer> goodCandidate = new HashSet<>();
                if(DFS(adjacencyList, course, cycles, seen, goodCandidate)) {
                    good.addAll(goodCandidate);
                    adjacencyList.remove(course);
                } else {
                    return false;
                }
            }
                
        }
        return true;
    }

    boolean DFS(Map<Integer, Set<Integer>> adjacencyList, int course, Set<Integer> cycles, Deque<Integer> seen, Set<Integer> goodCandidate) {
        if (!adjacencyList.containsKey(course)) {
            return true;
        }
        if (cycles.contains(course)) {
            return false;
        }
        if (seen.contains(course)) {
            cycles.add(course);
            return false;
        }

        seen.push(course);
        goodCandidate.add(course);

        for (Integer prerequisite: adjacencyList.get(course)) {
            if (!DFS(adjacencyList, prerequisite, cycles, seen, goodCandidate)) {
                cycles.add(prerequisite);
                seen.pop();
                return false;
            }
        }
        
        seen.pop();
        return true;
    }
}