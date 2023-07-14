class Solution {
    public int reverse(int x) {
        int n = 0;
        while (x != 0) {
            if (n > Integer.MAX_VALUE / 10  || n < Integer.MIN_VALUE / 10 ) {
                return 0;
            }
            n*=10;
            n+=x%10;
            x=x/10;
        }
        if (n > Integer.MAX_VALUE|| n < Integer.MIN_VALUE) {
            return 0;
        }
        return n;
    }
}