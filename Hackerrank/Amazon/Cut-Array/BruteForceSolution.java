import java.util.*;

public class Solution {
	public boolean loadBalance(int[] arr) {
	    int n = arr.length;
		int[] cumulativeSum = new int[n+1];
		if (n == 2) {
		    return false;
		}
		for (int i = 1; i <= n; i++) {
		    cumulativeSum[i] = cumulativeSum[i - 1] + arr[i - 1];
		}
		
		for (int i = 1; i < n; i++) {
		    for (int j = i + 1; j <= n; j++) {
		        int left = cumulativeSum[i-1];
		        int middle = cumulativeSum[j - 1] - cumulativeSum[i];
		        int right = cumulativeSum[n] - cumulativeSum[j];
		        if (left == middle && middle == right) {
		            return true;
		        }
		    }
		}
		
		return false;
	}
}