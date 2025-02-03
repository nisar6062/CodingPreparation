package coding;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestJava {
    public static void main(String[] args) {
        Comparator<String> comp = (s1, s2) -> {
            int compare = Integer.compare(s2.length(), s1.length());
            if (compare == 0) {
                return s1.compareTo(s2);
            }
            return compare;
        };

        TreeMap<String, Integer> treeMap = new TreeMap<>(comp);

        treeMap.put("apple", 10);
        treeMap.put("cherry", 15);
        treeMap.put("banana", 20);

        treeMap.forEach((k, v) -> {
            System.out.println("k:" + k + ", v: " + v);
        });
    }
}
