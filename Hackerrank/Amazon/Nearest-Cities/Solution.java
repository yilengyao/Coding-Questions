import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.List;

//If no other cities share an x or y coordinate, return null
public class Solution {
    static class City {
        String name;
        int x;
        int y;
        
        public City(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
        
        public int getManhattenDistance(City otherCity) {
            return Math.abs(this.x - otherCity.x) + Math.abs(this.y - otherCity.y);
        }
    }

    public String[] findNearestCities(int numOfPoints,
                                 String[] points,
                                 int[] xCoordinates,
                                 int[] yCoordinates,
                                 int numOfQueriedPoints,
                                 String[] queriedPoints) {
        String[] result = new String[numOfQueriedPoints];                             
        Map<Integer, Set<String>> xCoordinatesMap = new HashMap<>();
        Map<Integer, Set<String>> yCoordinatesMap = new HashMap<>();
        Map<String, City> cities = new HashMap<>();
        
        for (int i = 0; i < numOfPoints; i++) {
            Set<String> xPoints = xCoordinatesMap.getOrDefault(xCoordinates[i], new HashSet<>());
            xPoints.add(points[i]);
            xCoordinatesMap.put(xCoordinates[i], xPoints);
            
            Set<String> yPoints = yCoordinatesMap.getOrDefault(yCoordinates[i], new HashSet<>());
            yPoints.add(points[i]);
            yCoordinatesMap.put(yCoordinates[i], yPoints);
            cities.put(points[i], new City(points[i], xCoordinates[i], yCoordinates[i]));
        }

        for (int i = 0; i < numOfQueriedPoints; i++) {
            int distance = Integer.MAX_VALUE;
            City queriedCity = cities.get(queriedPoints[i]); 
            String candidate = null;
            
            Set<String> xCandidates = new HashSet<>(xCoordinatesMap.get(queriedCity.x));
            xCandidates.remove(queriedCity.name);
            
            for (String xCandidate: xCandidates) {
                int newDistance = queriedCity.getManhattenDistance(cities.get(xCandidate));
                if (newDistance < distance) {
                    candidate = xCandidate;
                    distance = newDistance;
                }
            }   
            
            Set<String> yCandidates = new HashSet<>(yCoordinatesMap.get(queriedCity.y));
            yCandidates.remove(queriedCity.name);

            for (String yCandidate: yCandidates) {
                int newDistance = queriedCity.getManhattenDistance(cities.get(yCandidate));
                if (newDistance < distance) {
                    candidate = yCandidate;
                    distance = newDistance;
                }
            }
            
            result[i] = candidate;
            
        }
                                    
                                     
        return result;
    }
}