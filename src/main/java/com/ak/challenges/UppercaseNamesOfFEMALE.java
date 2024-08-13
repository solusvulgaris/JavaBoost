package com.ak.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/*
 * Get the list of uppercase names of all the “FEMALE” people in the list of persons.
 * */
public class UppercaseNamesOfFEMALE {

  enum Gender {MALE, FEMALE, OTHER}

  @Getter
  @AllArgsConstructor
  static class Person {

    Gender gender;
    String name;
  }

  public static void main(String[] args) {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person(Gender.FEMALE, "Black"));
    persons.add(new Person(Gender.MALE, "Red"));
    persons.add(new Person(Gender.FEMALE, "White"));
    persons.add(new Person(Gender.OTHER, "Yellow"));
    persons.add(null);

    List<String> result = getUppercaseNames(persons);
    Logger.getAnonymousLogger().info(Arrays.toString(result.toArray()));
  }

  private static List<String> getUppercaseNames(@NonNull List<Person> persons) {
    return persons.stream()
        .filter(Objects::nonNull)
        .filter(person -> Objects.nonNull(person.gender))
        .filter(person -> Objects.nonNull(person.name))
        .filter(person -> !person.name.isBlank())
        .filter(person -> person.gender.equals(Gender.FEMALE))
        .map(person -> person.getName().toUpperCase())
        .peek(x -> Logger.getAnonymousLogger().info(x))
        .collect(Collectors.toList());
  }

}
