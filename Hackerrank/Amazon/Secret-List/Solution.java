import java.util.*;

public class Solution {
    public static String WILDCARD = "anything";
    
    public boolean matchSecretLists(List<List<String>> secretFruitList, List<String> customerPurchasedList) {
        if (secretFruitList.size() == 1
        && secretFruitList.get(0).size() == 1 
        && secretFruitList.get(0).get(0).equals(WILDCARD)) {
            return false;
        }
        for(List<String> secretBookList: secretFruitList) {
            int index = indexOf(secretBookList, customerPurchasedList);
            if (index < 0) {
                return false;
            }
        
            customerPurchasedList = customerPurchasedList.subList(index + secretBookList.size(), customerPurchasedList.size());
        }
		return true;
    }
    
   public int indexOf(List<String> secretBookList, List<String> purchasedList) {
        for(int i = 0; i <= purchasedList.size() - secretBookList.size(); i++) {
            if (checkBookListMatch(secretBookList, purchasedList, i)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean checkBookListMatch(List<String> secretBookList, List<String> purchasedList, int start) {
        for (int i = 0; i < secretBookList.size(); i++) {
            String requiredBookTitle = secretBookList.get(i);
            String purchasedBookTitle = purchasedList.get(start + i);
            if (!requiredBookTitle.equals(purchasedBookTitle) && !requiredBookTitle.equals(WILDCARD) ) {
                return false;
            }
        }
        return true;
    }
}

// List<List<String>> secretFruitList = new ArrayList<>(Arrays.asList(Arrays.asList("apple", "apple")));

// List<String> customerPurchasedList = new ArrayList<>(Arrays.asList("apple", "apple"));

// Solution s = new Solution();
// s.matchSecretLists(secretFruitList, customerPurchasedList);