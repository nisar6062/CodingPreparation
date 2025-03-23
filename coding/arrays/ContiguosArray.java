package coding.arrays;

import java.util.ArrayList;
import java.util.List;

/* Create a Contiguos sub array of length k
 * for eg: arr = [1,2,3] & k=2 gives output => [1,2], [1,3], [2,3]
 * 
 * solution - 
 */
public class ContiguosArray {

    public static void main(String args[]) {
        System.out.println("---test---");
        int arr[] = { 4, 5, 1, };
        // ArrayList<ArrayList<Integer>> list = allContiguosArray(arr, 5);
        // System.out.println("list: " + list);
        createArrays(arr, 0, new ArrayList<>(), 2);
    }

    public static void createArrays(int nums[], int index, List<Integer> result, int k) {
        if (index == nums.length) {
            if (result.size() == k) {
                System.out.println(result);
            }
            return;
        }
        result.add(nums[index]);
        createArrays(nums, index + 1, result, k);
        result.remove(result.size() - 1);
        createArrays(nums, index + 1, result, k);
    }

    public static ArrayList<ArrayList<Integer>> allContiguosArray(int arr[], int subArrayLength) {
        ArrayList<Integer> result = new ArrayList<>();
        return createContiguosArray(arr, subArrayLength, 0, result);
    }

    public static ArrayList<ArrayList<Integer>> createContiguosArray(int arr[], int subArrayLength, int index,
            ArrayList<Integer> result) {
        if (index >= arr.length) {
            if (result.size() >= subArrayLength) {
                ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
                list.add(result);
                // System.out.println("list>>>>>>>: " + list);
                return list;
            }
            return new ArrayList<ArrayList<Integer>>();
        }

        result.add(arr[index]);
        ArrayList<ArrayList<Integer>> list1 = createContiguosArray(arr, subArrayLength, index + 1, result);

        result.remove(result.size() - 1);
        ArrayList<ArrayList<Integer>> list2 = createContiguosArray(arr, subArrayLength, index + 1, result);

        for (ArrayList<Integer> list : list2) {
            if (list.size() > 0)
                list1.add(list);
        }
        // System.out.println("list1: " + list1);
        // System.out.println("list2: " + list2);
        return list1;
    }

}