import java.util.*;
import java.io.*;
import java.lang.*;


public class Solution {
    public String[] processLogFile(String[] logs, int threshold) {
        Map<String, Integer> frequency = new HashMap<>();
        Set<String> result = new HashSet<String>();
        
        for(String log: logs) {
            String[] parts = log.split(" ");
            String sender = parts[0];
            String receiver = parts[1];

            if (!sender.equals(receiver)) {
                frequency.put(sender, frequency.getOrDefault(sender, 0) + 1);
                if (frequency.get(sender) >= threshold) {
                    result.add(sender);
                }
            }
            
            frequency.put(receiver, frequency.getOrDefault(receiver, 0) + 1);
            if (frequency.get(receiver) >= threshold) {
                result.add(receiver);
            }            
        

        }
    
        return result.toArray(new String[0]);    
    }
}