package com.ak.collections.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Task: Iterating and Manipulating a Java Map
 * Objective:
 *   Write a Java program that iterates over a Map of employee IDs and their corresponding salaries.
 *   Perform the following operations during the iteration:
 *   1) Print all entries in the format: "Employee ID: [ID], Salary: [Salary]".
 *   2) Calculate and print the total sum of all salaries.
 *   3) Identify and print the employee ID(s) with the highest salary.
 *   4) Increase the salary of each employee by 10% and print the updated Map.
 * Instructions:
 * Create a Map (e.g., HashMap) with at least 5 entries,
 * where the key is an Integer (employee ID) and the value is a Double (salary).
 * Iterate over the Map to perform the specified operations.
 * */

public class EmployeeSalaryUtil {

  public static void main(String[] args) {
    Map<Integer, Double> employeeSalaries = new HashMap<>();
    employeeSalaries.put(100, 70000.0);
    employeeSalaries.put(101, 50000.0);
    employeeSalaries.put(102, 60000.0);
    employeeSalaries.put(103, 55000.0);
    employeeSalaries.put(104, 70000.0);
    employeeSalaries.put(105, 65000.0);

    printAll(employeeSalaries);

    System.out.println();
    System.out.println("Total sum of salaries: " + totalSum(employeeSalaries));

    List<Integer> employeeIds = employeeIdsWithHighestSalary(employeeSalaries);

    System.out.println();
    System.out.println("Employee ID(s) with the highest salary: "
        + employeeIdsToString(employeeIds));

    Map<Integer, Double> increasedSalaries = increaseSalary(employeeSalaries, 10);
    System.out.println();
    printAll(increasedSalaries);
  }

  private static String employeeIdsToString(List<Integer> employeeIds) {
    List<String> employeeIdsStringValue = employeeIds
        .stream()
        .distinct()
        .map(Object::toString)
        .toList();
    return String.join(",", employeeIdsStringValue);
  }

  private static void printAll(Map<Integer, Double> employeeSalaries) {
    employeeSalaries
        .entrySet()
        .stream()
        .map(entity -> "Employee ID: " + entity.getKey().toString()
            + ", Salary: " + String.format("%.1f", entity.getValue()))
        .forEach(System.out::println);
  }

  private static Double totalSum(Map<Integer, Double> employeeSalaries) {
    return employeeSalaries.values().stream()
        .filter(Objects::nonNull)
        .reduce(0d, Double::sum);
  }

  private static List<Integer> employeeIdsWithHighestSalary(Map<Integer, Double> employeeSalaries) {
    Double highestSalary = employeeSalaries.values().stream()
        .filter(Objects::nonNull)
        .reduce((salary1, salary2) -> salary1 > salary2 ? salary1 : salary2)
        .orElse(0d);

    return employeeSalaries.entrySet().stream()
        .filter(e -> e.getValue().equals(highestSalary))
        .map(Map.Entry::getKey)
        .toList();
  }

  private static HashMap<Integer, Double> increaseSalary(Map<Integer, Double> employeeSalaries,
      int percent) {
    double percentD = (100.0 + percent) / 100.0;
    return employeeSalaries.entrySet()
        .stream()
        .collect(Collectors.toMap(
            Entry::getKey,
            entry -> entry.getValue() * percentD,
            (oldValue, newValue) -> newValue,
            HashMap::new));
  }

}
