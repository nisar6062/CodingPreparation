package coding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sample usages of the core Java data structures, all driven from main().
 * Run with:
 *   javac DataStructuresDemo.java
 *   java DataStructuresDemo
 */
public class DataStructuresDemo {

    public static void main(String[] args) throws InterruptedException {

        // =====================================================================
        // 1. ARRAYS
        // =====================================================================
        System.out.println("=== Arrays ===");

        int[] intArray = {5, 3, 8, 1, 9, 2};
        System.out.println("Original array: " + Arrays.toString(intArray));

        Arrays.sort(intArray); // ascending, in place
        System.out.println("Sorted array:   " + Arrays.toString(intArray));

        int index = Arrays.binarySearch(intArray, 8);
        System.out.println("Index of 8 (binary search on sorted array): " + index);

        int[] copy = Arrays.copyOf(intArray, intArray.length);
        int[] range = Arrays.copyOfRange(intArray, 1, 4);
        System.out.println("Copy: " + Arrays.toString(copy) + ", Range[1,4): " + Arrays.toString(range));

        Arrays.fill(copy, 0);
        System.out.println("After fill(0): " + Arrays.toString(copy));

        // Array of objects sorted with a Comparator (descending)
        Integer[] boxedArray = {5, 3, 8, 1, 9, 2};
        Arrays.sort(boxedArray, Comparator.reverseOrder());
        System.out.println("Boxed array sorted descending: " + Arrays.toString(boxedArray));

        // Array -> fixed-size List view
        List<Integer> arrayAsList = Arrays.asList(boxedArray);
        System.out.println("Array as List (fixed-size, backed by array): " + arrayAsList);

        // =====================================================================
        // 2. LISTS (ArrayList) + List.sort / Collections.sort
        // =====================================================================
        System.out.println("\n=== Lists ===");

        List<Integer> list = new ArrayList<>(List.of(5, 3, 8, 1, 9, 2));
        System.out.println("Original list: " + list);

        Collections.sort(list); // ascending, in place
        System.out.println("Collections.sort (ascending): " + list);

        list.sort(Comparator.reverseOrder()); // List.sort with a Comparator
        System.out.println("list.sort(reverseOrder): " + list);

        Collections.shuffle(list);
        System.out.println("Shuffled: " + list);

        Collections.reverse(list);
        System.out.println("Reversed: " + list);

        System.out.println("Max: " + Collections.max(list) + ", Min: " + Collections.min(list));

        // =====================================================================
        // 3. COMPARABLE vs COMPARATOR (using a custom Person class below)
        // =====================================================================
        System.out.println("\n=== Comparable vs Comparator ===");

        List<Person> people = new ArrayList<>(List.of(
                new Person("Charlie", 35),
                new Person("Alice", 28),
                new Person("Bob", 28)
        ));
        System.out.println("Original: " + people);

        // Comparable: uses Person.compareTo() (natural ordering - by name)
        Collections.sort(people);
        System.out.println("Sorted by Comparable (name): " + people);

        // Comparator: sort by age, then by name as a tiebreaker
        people.sort(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName));
        System.out.println("Sorted by Comparator (age, then name): " + people);

        // Comparator: sort by name descending using a lambda
        people.sort((p1, p2) -> p2.getName().compareTo(p1.getName()));
        System.out.println("Sorted by lambda Comparator (name desc): " + people);

        // =====================================================================
        // 4. LINKEDLIST (List + Deque)
        // =====================================================================
        System.out.println("\n=== LinkedList ===");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("b");
        linkedList.addFirst("a");
        linkedList.addLast("c");
        System.out.println("LinkedList after adds: " + linkedList);
        System.out.println("First: " + linkedList.getFirst() + ", Last: " + linkedList.getLast());

        linkedList.removeFirst();
        System.out.println("After removeFirst: " + linkedList);

        // LinkedList also implements Deque, so it can act as a queue or a stack
        Deque<String> asQueue = linkedList;
        asQueue.offer("d"); // enqueue at tail
        System.out.println("As queue after offer('d'): " + asQueue + ", poll(): " + asQueue.poll());

        // =====================================================================
        // 5. STACK (LIFO)
        // =====================================================================
        System.out.println("\n=== Stack ===");

        // java.util.Stack (legacy, synchronized) - shown for completeness
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after pushes: " + stack);
        System.out.println("peek(): " + stack.peek());
        System.out.println("pop(): " + stack.pop() + ", stack now: " + stack);

        // Preferred modern approach: ArrayDeque as a stack (faster, not synchronized)
        Deque<Integer> modernStack = new ArrayDeque<>();
        modernStack.push(10);
        modernStack.push(20);
        modernStack.push(30);
        System.out.println("ArrayDeque-as-stack: " + modernStack + ", pop(): " + modernStack.pop());

        // =====================================================================
        // 6. PRIORITY QUEUE (heap)
        // =====================================================================
        System.out.println("\n=== PriorityQueue ===");

        // Min-heap by natural ordering (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(List.of(5, 3, 8, 1, 9, 2));
        System.out.print("Min-heap poll order: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // Max-heap using Comparator.reverseOrder()
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(5, 3, 8, 1, 9, 2));
        System.out.print("Max-heap poll order: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();

        // PriorityQueue of custom objects, ordered by age (youngest first)
        PriorityQueue<Person> peopleQueue = new PriorityQueue<>(Comparator.comparingInt(Person::getAge));
        peopleQueue.addAll(List.of(new Person("Dave", 40), new Person("Eve", 25)));
        System.out.println("Youngest in queue: " + peopleQueue.poll());

        // =====================================================================
        // 7. HASHMAP
        // =====================================================================
        System.out.println("\n=== HashMap ===");

        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("apple", 3);
        wordCounts.put("banana", 5);
        wordCounts.putIfAbsent("apple", 100); // no-op, key already present
        System.out.println("After puts: " + wordCounts);

        wordCounts.merge("apple", 2, Integer::sum); // apple: 3 + 2 = 5
        wordCounts.computeIfAbsent("cherry", k -> 0);
        wordCounts.compute("banana", (k, v) -> v + 1);
        System.out.println("After merge/computeIfAbsent/compute: " + wordCounts);

        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("getOrDefault('kiwi', 0): " + wordCounts.getOrDefault("kiwi", 0));

        // =====================================================================
        // 8. CONCURRENTHASHMAP
        // =====================================================================
        System.out.println("\n=== ConcurrentHashMap ===");

        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("counter", 0);

        // Atomic, thread-safe increment - no external synchronization needed
        int threadCount = 8;
        int incrementsPerThread = 1000;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            pool.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    concurrentMap.merge("counter", 1, Integer::sum); // atomic read-modify-write
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdown();

        System.out.println("Expected: " + (threadCount * incrementsPerThread)
                + ", Actual (ConcurrentHashMap, no external lock): " + concurrentMap.get("counter"));

        // =====================================================================
        // 9. LOCK (ReentrantLock) - protecting a plain HashMap/counter by hand
        // =====================================================================
        System.out.println("\n=== ReentrantLock ===");

        int[] plainCounter = {0}; // effectively-final holder so the lambda can mutate it
        Lock lock = new ReentrantLock();
        ExecutorService pool2 = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch2 = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            pool2.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    lock.lock();
                    try {
                        plainCounter[0]++; // critical section - only one thread at a time
                    } finally {
                        lock.unlock(); // always unlock in finally, even if an exception is thrown
                    }
                }
                latch2.countDown();
            });
        }
        latch2.await();
        pool2.shutdown();

        System.out.println("Expected: " + (threadCount * incrementsPerThread)
                + ", Actual (plain counter guarded by ReentrantLock): " + plainCounter[0]);

        System.out.println("\nDone.");
    }

    /**
     * Implements Comparable to define this class's natural ordering (by name).
     * Comparable is a single, built-in ordering owned by the class itself.
     * Comparator (used above) lets you define any number of external, swappable
     * orderings without changing the class.
     */
    static class Person implements Comparable<Person> {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.name); // natural ordering: by name
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
