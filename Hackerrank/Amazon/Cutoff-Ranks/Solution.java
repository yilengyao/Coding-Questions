import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public int cutOffRank(int cutOffRank, int num, int[] scores) {
        if (cutOffRank == 0) {
            return 0;
        }

        int[] frequencyTable = new int[101];
        
        for (int i : scores) {
            frequencyTable[i]++;
        }
        
        int count = 0;
        for (int score=100; score >=0; score--) {
            count += frequencyTable[score];
            if (count >= cutOffRank) {
                return count;
            }
        }
        
        return count;
    }
}