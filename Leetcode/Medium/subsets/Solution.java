class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> listNums = new ArrayList<>();
        Set<List<Integer>> result = new HashSet<>();
        for (int num: nums) {
            listNums.add(num);
        }
        powerSet(listNums, result);
        return new ArrayList(result);
    }

    public void powerSet(List<Integer> listNums, Set<List<Integer>> result) {
        result.add(listNums);

        if (listNums.size() == 0) {
            return;
        }

        for (int i = 0; i < listNums.size(); i++) {
            List<Integer> newListNums = new ArrayList<>(listNums);
            newListNums.remove(i);
            powerSet(newListNums, result);
        }
    }
}