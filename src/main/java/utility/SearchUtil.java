package utility;

import java.util.Comparator;

import adt.ListInterface;
import entity.Match;

public class SearchUtil {


    /**
     * Calculates the Levenshtein distance between two strings, which is the
     * minimum number of single-character edits (i.e. insertions, deletions or
     * substitutions) required to change one word into the other.
     *
     * @param input  the string to be compared
     * @param target the string to compare with
     * @return the Levenshtein distance between the two strings
     */
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

    /**
     * Performs a fuzzy search on a string, which checks for similarity between
     * the input and target strings.
     *
     * @param input   the string to be compared
     * @param target  the string to compare with
     * @param threshold the maximum allowed Levenshtein distance
     * @return true if the strings are similar (i.e. within the given threshold),
     *         false otherwise
     */
    public static boolean fuzzySearch(String input, String target, int threshold) {
        if (input == null || target == null) {
            return false;
        }
        int distance = levenshteinDistance(input, target);
        return distance <= threshold;
    }
}