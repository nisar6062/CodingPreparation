package coding.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NoOf3Cycles {
    public static void main(String[] args) {
        int arr[][] = { { 1, 2 }, { 5, 2 }, { 4, 1 }, { 2, 4 }, { 3, 1 }, { 3, 4 } };
        int arr2[][] = { { 1, 2 }, { 3, 4 } };
        System.out.println("Cycles: " + noOf3Cycles(2, arr2));
    }

    public static int noOf3Cycles(int n, int arr[][]) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (arr[i][0] < arr[i][1]) {
                map.putIfAbsent(arr[i][0], new HashSet<>());
                map.get(arr[i][0]).add(arr[i][1]);
            } else {
                map.putIfAbsent(arr[i][1], new HashSet<>());
                map.get(arr[i][1]).add(arr[i][0]);
            }
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set1 = map.getOrDefault(arr[i][0], new HashSet<>());
            Set<Integer> set2 = map.getOrDefault(arr[i][1], new HashSet<>());
            for (int num : set1) {
                if (set2.contains(num))
                    count++;
            }
        }
        return count;
    }

}
