import java.util.*;
public class Solution {
  public List<String> findLargestGroup(List<List<String>> items){
      int n = items.size();
      List<Set<String>> itemsSets = toListSet(items);
      
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n && j != i; j++) {
              if (!intersection(itemsSets.get(i), itemsSets.get(j)).isEmpty()) {
                  itemsSets.get(i).addAll(itemsSets.get(j));
              }
          }
      }
      
      int size = 0;
      Set<String> result = new HashSet<>();
      for (Set<String> itemsSet: itemsSets) {
          if (itemsSet.size() > size) {
              size = itemsSet.size();
              result = itemsSet;
          }
      }
      
      return new ArrayList<>(result);
  }
  
  public List<Set<String>> toListSet(List<List<String>> items) {
      List<Set<String>> listSet = new ArrayList<>(new HashSet<>());
      for (List<String> item: items) {
         listSet.add(new HashSet<>(item));
      }
      return listSet;
  }
  
  public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
      Set<T> intersection = new HashSet<>(set1);
      intersection.retainAll(set2);
      return intersection;
  }
}