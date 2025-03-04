package coding.subsequence;

import java.util.ArrayList;
import java.util.List;

public class AllSubSequences {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };
        List<List<Integer>> list = generateAllSubSequences(arr, 0, new ArrayList<>());
        System.out.println("Seq: " + list);

    }

    public static List<List<Integer>> generateAllSubSequences(int[] input, int index, List<Integer> accList) {
        if (index == input.length) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            for (Integer num : accList)
                list.add(num);
            result.add(list);
            System.out.println("accList_ret: " + accList);
            return result;
        }

        accList.add(input[index]);
        List<List<Integer>> list1 = generateAllSubSequences(input, index + 1, accList);
        // System.out.println("list1: " + list1);
        accList.remove(accList.size() - 1);
        List<List<Integer>> list2 = generateAllSubSequences(input, index + 1, accList);
        // System.out.println("list2: " + list2);
        for (List<Integer> list : list2)
            list1.add(list);
        return list1;

    }

    public static void generateAllSubSequences(int[] input, int index, List<Integer> accList,
            List<List<Integer>> result) {
        if (index == input.length) {
            List<Integer> list = new ArrayList<>();
            for (Integer num : accList)
                list.add(num);
            result.add(list);
            // System.out.println("accList_ret: " + accList);
            // return list;
            return;
        }

        accList.add(input[index]);
        generateAllSubSequences(input, index + 1, accList, result);
        // System.out.println("list1: " + list1);
        accList.remove(accList.size() - 1);
        generateAllSubSequences(input, index + 1, accList, result);
        // System.out.println("list2: " + list2);
        // if (list1.size() < list2.size()) {
        // for (List<Integer> list : list2)
        // list1.add(list);
        // return list1;
        // } else {
        // for (List<Integer> list : list1)
        // list2.add(list);
        // return list2;
        // }

    }
}
