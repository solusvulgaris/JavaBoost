package com.ak.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

  public static final String INPUT_MATRIX_HAS_INCORRECT_FORMAT = "Input matrix has incorrect format.";

  public static void main(String[] args) {
    int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    Logger.getAnonymousLogger().info("Initial matrix:");
    String matrixStringValue = Arrays.deepToString(matrix);
    Logger.getAnonymousLogger().info(matrixStringValue);

    int[][] rotated = rotate(matrix);
    Logger.getAnonymousLogger().info("Simple rotation result:");
    String rotatedStringValue = Arrays.deepToString(rotated);
    Logger.getAnonymousLogger().info(rotatedStringValue);

    MatrixRotation instance = new MatrixRotation();
    int[][] rotatedWithRotationMatrix = instance.rorateWithRotationMatrix(matrix);
    Logger.getAnonymousLogger().info("Rotation with RotationMatrix result:");
    String rotatedWithRotationMatrixStringValue = Arrays.deepToString(rotatedWithRotationMatrix);
    Logger.getAnonymousLogger().info(rotatedWithRotationMatrixStringValue);
  }

  public static int[][] rotate(@NonNull int[][] inputMatrix) {
    int rowsCount = inputMatrix.length;
    if (rowsCount == 0) {
      return new int[][]{{}};
    }
    int columnsCount = inputMatrix[0].length;
    if (columnsCount == 0) {
      throw new RuntimeException(INPUT_MATRIX_HAS_INCORRECT_FORMAT);
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

  /*
   * Rotation of the image is performed relative to the central element, which has coordinates x = 0 and h = 0
   * All other elements are assigned coordinates accordingly with a step of 1 along the x and y axis:
   *
   * 1(-1:1)  2(0:1)  3(1:1)
   * 4(-1:0)  5(0:0)  6(1:0)
   * 7(-1:-1) 8(0:-1) 9(-1:-1)
   *
   * To rotate we can use Rotation Matrix and final coordinates would be calculated by formula:
   *
   * xR =  x*Cos(-90) - y*Sin(-90);
   * yR = x*Sin(-90) + y*Cos(-90);
   *
   * as Cos(-90) = 0 and Sin(-90) = -1 formula can be simplified to
   *
   * xR =  y;
   * yR = -x;
   *
   * https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D1%82%D1%80%D0%B8%D1%86%D0%B0_%D0%BF%D0%BE%D0%B2%D0%BE%D1%80%D0%BE%D1%82%D0%B0
   * https://www.cuemath.com/algebra/rotation-matrix/
   * https://www.cuemath.com/matrix-formula/
   * https://www.cuemath.com/algebra/determinant-of-matrix/
   * https://byjus.com/jee/matrix-operations/
   * https://www.cuemath.com/algebra/matrix-operations/
   *
   * */
  private int[][] rorateWithRotationMatrix(@NonNull int[][] inputMatrix) throws RuntimeException {

    int rowsCount = inputMatrix.length;
    if (rowsCount == 0) {
      return new int[][]{{}};
    }
    if (rowsCount % 2 == 0) {
      throw new RuntimeException(INPUT_MATRIX_HAS_INCORRECT_FORMAT);
    }
    int columnsCount = inputMatrix[0].length;
    if (columnsCount % 2 == 0) {
      throw new RuntimeException(INPUT_MATRIX_HAS_INCORRECT_FORMAT);
    }

    List<Element> elements = extractElements(inputMatrix, inputMatrix.length / 2,
        inputMatrix[0].length / 2);

    List<Element> rotatedElements = recalculateCoordinates(elements);

    rotatedElements.sort(Comparator.comparingInt(o -> o.x));
    rotatedElements.sort((o1, o2) -> -Integer.compare(o1.y, o2.y));

    return getResultMatrix(rotatedElements, columnsCount, rowsCount);
  }

  @AllArgsConstructor
  @Getter
  static class Element {

    int value;
    int x;
    int y;

    @Override
    public String toString() {
      return value + " (" + x + " : " + y + " )";
    }
  }

  private List<Element> extractElements(@NonNull int[][] inputMatrix, int sentralRow,
      int sentralColumn) {
    List<Element> elements = new ArrayList<>();

    for (int row = 0; row < inputMatrix.length; row++) {
      for (int column = 0; column < inputMatrix[0].length; column++) {
        int x = column - sentralColumn;
        int y = sentralRow - row;
        Element element = new Element(inputMatrix[row][column], x, y);
        elements.add(element);
      }
    }
    return elements;
  }

  private List<Element> recalculateCoordinates(List<Element> elements) {
    List<Element> rotatedElements = new ArrayList<>();
    for (Element e : elements) {
      int xR = e.y;
      int yR = -e.x;
      Element rotateElement = new Element(e.value, xR, yR);
      rotatedElements.add(rotateElement);
    }
    return rotatedElements;
  }

  private int[][] getResultMatrix(List<Element> rotatedElements, int columnsCount, int rowsCount) {
    int[][] result = new int[columnsCount][rowsCount];
    int n = 0;
    for (int j = 0; j < columnsCount; j++) {
      for (int i = 0; i < rowsCount; i++) {
        result[j][i] = rotatedElements.get(i + rowsCount * n).value;
      }
      ++n;
    }
    return result;
  }
}
