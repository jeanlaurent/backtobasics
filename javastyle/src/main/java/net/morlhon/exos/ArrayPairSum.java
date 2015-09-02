package net.morlhon.exos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayPairSum {


    public List<Pair> find(int[] input, int target) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                if ((i !=j) && (input[i] + input[j] == target)) {
                    pairs.add(new Pair(input[i], input[j]));
                }
            }
        }
        return pairs;
    }

    public List<Pair> find2(int[] input, int target) {
        List<Pair> pairs = new ArrayList<>();
        Arrays.sort(input);
        int fromStart = 0;
        int fromFinish = input.length - 1;
        while(fromStart < fromFinish) {
            int sum = input[fromStart] + input[fromFinish];
            if (sum < target) {
                fromStart++;
            }
            if (sum > target) {
                fromFinish--;
            }
            if (sum == target) {
                pairs.add(new Pair(input[fromStart] ,input[fromFinish]));
                fromStart++;
            }
        }
        return pairs;
    }

    public List<Pair> find3(int[] input, int target) {
        List<Pair> pairs = new ArrayList<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (int number : input) {
            int matching = target - number;
            if (visited.containsKey(matching)) {
                pairs.add(new Pair(number, matching));
            } else {
                visited.put(number, true);
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        ArrayPairSum arrayPairSum = new ArrayPairSum();
        System.out.println(arrayPairSum.find(new int[]{10, 2, 8, 7, 5, 0, 4, 9, 1, 6}, 10));
        System.out.println(arrayPairSum.find2(new int[]{10, 2, 8, 7, 5, 0, 4, 9, 1, 6}, 10));
        System.out.println(arrayPairSum.find3(new int[]{10, 2, 8, 7, 5, 0, 4, 9, 1, 6}, 10));
    }
}
