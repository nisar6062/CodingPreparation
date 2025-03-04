package coding.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetect {
    public static void main(String[] args) {
        int courses[][] = { { 0, 1 }, { 2, 1 } };
        System.out.println("detect: " + detectCourses(3, courses));
        System.out.println("canFinish: " + canFinish(3, courses));
    }

    public static boolean detectCourses(int numCourses, int preReq[][]) {
        int indegree[] = new int[numCourses];
        int graph[][] = new int[numCourses][numCourses];

        for (int i = 0; i < preReq.length; i++) {
            graph[preReq[i][0]][preReq[i][1]] = 1;
            indegree[preReq[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            int curr = queue.poll();
            for (int i = 0; i < numCourses; i++) {
                if (graph[curr][i] == 1) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        System.out.println("visited: " + visited + ", ");
        return visited == numCourses;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int visited = 0;
        while (!q.isEmpty()) {
            int n = q.poll();
            visited++;
            for (int neighbour : graph.get(n)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    q.add(neighbour);
                }
            }
        }
        return visited == numCourses;
    }
}
