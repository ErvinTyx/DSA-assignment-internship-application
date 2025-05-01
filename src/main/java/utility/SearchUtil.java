package utility;

public class SearchUtil {


    // Fuzzy search utilities
    public static int levenshteinDistance(String input, String target) {
        if (input == null || target == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int[][] dp = new int[input.length() + 1][target.length() + 1];

        for (int i = 0; i <= input.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= target.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= input.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {
                int cost = (input.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(
                        dp[i - 1][j] + 1,
                        dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }

        return dp[input.length()][target.length()];
    }

    public static boolean fuzzySearch(String input, String target, int threshold) {
        if (input == null || target == null) {
            return false;
        }
        int distance = levenshteinDistance(input, target);
        return distance <= threshold;
    }
}