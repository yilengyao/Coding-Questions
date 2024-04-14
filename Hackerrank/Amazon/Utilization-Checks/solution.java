import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public static final int MAX_INSTANCES = (int) Math.pow(10,8);
    public static final int MAX_UTILIZATION = 60;
    public static final int MIN_UTILIZATION = 25;
    
    public int finalInstances(int instances, int[] averageUtil) {
        
        int n = averageUtil.length;
        
        for (int time = 0; time < n; time++) {
            int prevInstances = instances;
            int util = averageUtil[time];
            
            if (util < MIN_UTILIZATION) {
                instances = (int) Math.ceil(instances/2.0);
            } else if (util > MAX_UTILIZATION  && instances <= MAX_INSTANCES) {
                instances = instances * 2;
            }
            if (prevInstances != instances) {
                time+=10;
            }
            
        }
        return instances;
    }
}