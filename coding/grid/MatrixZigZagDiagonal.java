package coding.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 
 */
public class MatrixZigZagDiagonal {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printDiagonal(matrix);
    }

    public static void printDiagonal(int[][] matrix) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                List<Integer> list = map.getOrDefault(i + j, new ArrayList<>());
                list.add(matrix[i][j]);
                map.put(i + j, list);
            }
        }
        int count = 0;
        System.out.println(map);
        for (int key : map.keySet()) {
            System.out.println("key:" + key);
            List<Integer> list = map.get(key);
            if (count % 2 == 0) {
                Collections.reverse(list);
                System.out.println(list);
            } else {
                System.out.println(list);
            }

        }

    }
}
