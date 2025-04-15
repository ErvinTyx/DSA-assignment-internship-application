package utility;

public class SearchUtil {
    // Merge sort for any comparable type
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        Object[] temp = new Object[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

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