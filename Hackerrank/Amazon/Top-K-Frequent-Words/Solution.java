import java.util.*;

public class Solution {
    static class GameReview implements Comparable<GameReview> {
        String word;
        int occurance;
        int numReviews;

        public GameReview(String word, int occurance, int numReviews) {
            this.word = word;
            this.occurance = occurance;
            this.numReviews = numReviews;
        }
        
        @Override
        public int compareTo(GameReview other) {
            if (this.occurance < other.occurance) {
                return 1;
            } else if (this.occurance > other.occurance) {
                return -1;
            } else {
                if (this.numReviews < other.numReviews) {
                    return 1;
                } else if (this.numReviews > other.numReviews) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    
    public List<String> getTopGames(int num, int topKGames, String[] games, int numReviews, String[] reviews) {
        PriorityQueue<GameReview> popularGames = new PriorityQueue<>();
        List<String> result = new ArrayList<>();
        List<List<String>> tokenizedReviews = new ArrayList<>();
        
        for (int i = 0; i < numReviews; i++) {
            tokenizedReviews.add(new ArrayList<>(Arrays.asList(reviews[i].split(" "))));
        }
        
        for (String game: games) {
            int occurance = 0;
            int numGameReviews = 0;
            for (List<String> review: tokenizedReviews) {
                int numTokens = review.size();
                List<String> target = Collections.singletonList(game);
                review.removeAll(target);
                int occuranceInReview = numTokens - review.size();
                occurance += occuranceInReview;
                numGameReviews += Math.min(1, occuranceInReview);
            }
            
            popularGames.add(new GameReview(game, occurance, numGameReviews));
        }
        
        for (int i = 0; i < topKGames; i++) {
           result.add(popularGames.poll().word);
        }
        
        return result;
	}
}
