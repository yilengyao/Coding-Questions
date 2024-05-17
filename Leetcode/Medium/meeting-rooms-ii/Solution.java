class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minEndtimes = new PriorityQueue<>();
        int meetingRooms = 0;
        for (int[] interval: intervals) {
            minEndtimes.offer(interval[1]);
            while (!minEndtimes.isEmpty() && minEndtimes.peek() <= interval[0]) {
                minEndtimes.poll();
            }
            meetingRooms = Math.max(meetingRooms, minEndtimes.size());
        }
        return meetingRooms;
    }
}