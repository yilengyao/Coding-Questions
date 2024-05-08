class Solution {
    public List<List<Integer>> combine(int n, int k) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i+1;
        }
        List<Integer> candidates = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        generateCombination(numbers, 0, n, k, candidates, results);
        return results;
    }

    public void generateCombination(int[] numbers, int index, int length, int k, List<Integer> candidates, List<List<Integer>> results) {
        if (index >= length) {
            return;
        }

        if (k == 1) {
            for (int i = index; i < length; i++) {
                List<Integer> result = new ArrayList<>(candidates);
                result.add(numbers[i]);
                results.add(result);
            }
        }

        for (int i = index; i < length; i++) {
            candidates.add(numbers[i]);
            generateCombination(numbers, i + 1, length, k - 1, candidates, results);
            candidates.remove(candidates.size() - 1);
        }
    }
}