package com.ak.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import lombok.NonNull;

/*
* Imagine you're working on an image processing application.
* One common operation in image processing is to apply transformations like rotation, scaling, or shearing.
* Matrices are used extensively in these operations.
* Your task is to implement a simple image transformation using matrix operations.
* Rotate Image: Write a Java program that takes an input matrix representing an image and rotates it by 90 degrees clockwise.
For example:
Input:
1 2 3
4 5 6
7 8 9

Output:
7 4 1
8 5 2
9 6 3
Implement the rotation function using matrix operations and print the rotated matrix
* */
public class MatrixRotation {

  public static void main(String[] args) {
    int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    String matrixStringValue = Arrays.deepToString(matrix);
    Logger.getAnonymousLogger().info(matrixStringValue);

    int[][] rotated = rotate(matrix);
    String rotatedStringValue = Arrays.deepToString(rotated);
    Logger.getAnonymousLogger().info(rotatedStringValue);
  }

  public static int[][] rotate(@NonNull int[][] inputMatrix) {
    int rowsCount = inputMatrix.length;
    if (rowsCount == 0) {
      return new int[][]{{}};
    }
    int columnsCount = inputMatrix[0].length;
    if (columnsCount == 0) {
      //TODO: what shall we do, if first row or any other row is empty? Shall it be exception thrown?
      return new int[][]{{}};
    }

    List<int[]> inputArrays = new ArrayList<>(Arrays.asList(inputMatrix));
    List<int[]> rotatedArray = new ArrayList<>();

    for (int i = 0; i < columnsCount; ++i) {
      int[] rotatedRow = new int[rowsCount];

      for (int j = 0; j < rowsCount; j++) {
        rotatedRow[j] = inputArrays.get(rowsCount - j - 1)[i];
      }

      rotatedArray.add(rotatedRow);
    }

    return rotatedArray.toArray(int[][]::new);
  }
}
