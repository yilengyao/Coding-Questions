import java.util.*;
import java.io.*;
import java.lang.*;


// class debtRecord{
//     String borrower = ""; // importer
//     String lender = ""; //exporter
//     int amount = 0;
// }


public class Solution {
    List<String> minimumDebtMembers(List<debtRecord> records){
        Map<String, Integer> deficit = new HashMap<>();
        
        for(debtRecord dr: records) {
            deficit.put(dr.borrower, deficit.getOrDefault(dr.borrower, 0) - dr.amount);
            
            deficit.put(dr.lender, deficit.getOrDefault(dr.lender, 0) + dr.amount);
        }
        
        int minValue = Collections.min(deficit.values());
        
        
        List result = new ArrayList<>();
        
        if (minValue == 0) {
            result.add("Nobody has a negative balance");
            return result;
        }

        
        for (String country: deficit.keySet()) {
            if (deficit.get(country) == minValue) {
                result.add(country);
            }
        }

        return result;
    }
}
