package coding.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {
    public static void main(String[] args) {
        System.out.println("Result: " + getAlienDictionary(new String[] { "wrt", "wrf", "er", "ett", "rftt" }));
    }

    public static String getAlienDictionary(String[] words) {
        // 1. create adjacency list
        Map<Character, Set<Character>> adjList = new HashMap<>();
        Map<Character, Integer> countMap = new HashMap<>();
        createAdjList(words, adjList, countMap);
        // 2. do BFS
        bfs(adjList, countMap);
        return null;
    }

    public static void createAdjList(String[] words, Map<Character, Set<Character>> adjList,
            Map<Character, Integer> countMap) {
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                adjList.put(c, new HashSet<>());
                countMap.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    countMap.put(word2.charAt(j), countMap.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
    }

    public static String bfs(Map<Character, Set<Character>> adjList, Map<Character, Integer> countMap) {
        Queue<Character> queue = new LinkedList<>();
        System.out.println("countMap: " + countMap);
        for (char c : countMap.keySet()) {
            if (countMap.get(c) == 0) {
                queue.add(c);
            }
        }

        List<Character> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            list.add(c);

            for (Character n : adjList.get(c)) {
                // if (!isVisited.contains(n)) {
                countMap.put(n, countMap.get(n) - 1);
                System.out.println(n + "---" + countMap.get(n));
                if (countMap.get(n) == 0) {
                    queue.add(n);
                }
                // }
            }
        }
        System.out.println("list:" + list);
        if (list.size() < countMap.size())
            return "";

        return list.toString();
    }
}
