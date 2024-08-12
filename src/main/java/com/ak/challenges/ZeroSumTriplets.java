package com.ak.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import java.util.logging.Logger;

/*
 * Given an integer array nums,
 * return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k,
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * In the array shouldn't be duplicate values.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,0,1]]
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 *
 * This solution should be O(n^2) time complexity.
 *
 * */
public class ZeroSumTriplets {

  public static void main(String[] args) {
    Set<int[]> triplets = getTriplets(new int[]{-1, 0, 1, 2, -1, -4});
    for (var v : triplets) {
      Logger.getAnonymousLogger().info(Arrays.toString(v));
    }
  }

  private static Set<int[]> getTriplets(int[] inputArray) {
    List<Integer> uniqueInput =
        Arrays.stream(inputArray).boxed().collect(Collectors.toSet())
            .stream().toList();

    @AllArgsConstructor
    class SumOfTwoIndexedValues {

      final int i;
      final int j;
      final int sum;
    }

    List<SumOfTwoIndexedValues> sumInstances = new ArrayList<>();
    for (int i = 0; i < uniqueInput.size() - 1; ++i) {
      for (int j = i + 1; j < uniqueInput.size(); ++j) {
        sumInstances.add(
            new SumOfTwoIndexedValues(i, j, uniqueInput.get(i) + uniqueInput.get(j)));
      }
    }

    Set<int[]> resultTriplets = new HashSet<>();
    for (SumOfTwoIndexedValues sumInstance : sumInstances) {
      for (int k = 0; k < uniqueInput.size(); ++k) {
        if (k > sumInstance.i && k > sumInstance.j) {
          int tripletSum = sumInstance.sum + uniqueInput.get(k);
          if (tripletSum == 0) {
            int[] triplet = {uniqueInput.get(sumInstance.i), uniqueInput.get(sumInstance.j),
                uniqueInput.get(k)};
            resultTriplets.add(triplet);
          }
        }
      }
    }

    return resultTriplets;
  }

}
