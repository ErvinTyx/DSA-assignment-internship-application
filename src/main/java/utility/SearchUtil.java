package utility;

public class SearchUtil {
    // merge sort any data type
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    public static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[arr.length];
        int leftIndex = left;
        int rightIndex = mid + 1;
        int index = left;
        
        while (leftIndex <= mid && rightIndex <= right) {
            if (arr[leftIndex].compareTo(arr[rightIndex]) <= 0) {
                temp[index] = arr[leftIndex];
                leftIndex++;
            } else {
                temp[index] = arr[rightIndex];
                rightIndex++;
            }
            index++;
        }
        
        while (leftIndex <= mid) {
            temp[index] = arr[leftIndex];
            leftIndex++;
            index++;
        }
        
        while (rightIndex <= right) {
            temp[index] = arr[rightIndex];
            rightIndex++;
            index++;
        }
        
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
    

    // fuzzy search string
    public static int levenshteinDistance(String input, String target) {
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
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
    
        return dp[input.length()][target.length()];
    }
    
    public static boolean fuzzySearch(String input, String target, int threshold) { 
        int distance = levenshteinDistance(input, target);
        return distance <= threshold;
    }
    


}
