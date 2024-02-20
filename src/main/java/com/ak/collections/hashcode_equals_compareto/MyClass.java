package com.ak.collections.hashcode_equals_compareto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class with overridden methods: CompareTo(), Equals(), hashCode()
 */
public class MyClass implements Comparable<MyClass> {
    @Getter
    @Setter
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    /**
     * Two {@code MyClass} objects that are equal in value are considered equal by this method.
     *
     * @param o {@code MyClass} to which this {@code MyClass} is to be compared.
     * @return -1, 0, or 1 as this {@code BigDecimal} is numerically
     * less than, equal to, or greater than {@code val}.
     * @apiNote Note: this class has a natural ordering that is inconsistent with equals.
     */
    @Override
    public int compareTo(MyClass o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // ? compares ref to objects?
        if (o == null || getClass() != o.getClass()) return false;

        MyClass myClass = (MyClass) o;

        return digitsSum(this) == digitsSum(myClass);
    }

    private static int digitsSum(MyClass o) {
        int number = o.getValue();
        int sum = 0;
        while (number % 10 > 0) {
            int n = number % 10;
            sum += n;
            number = (number - n) / 10;
        }
        return sum;
    }

    /**
     * Returns the hash code for this {@code MyClass}.
     * The hash code is computed as a remainder of the value division by 2
     *
     * @return hash code for this {@code MyClass}.
     * @apiNote All even numbers will get identical hashcode = 0;
     * All odd numbers will get identical hashcode = 1;
     */
    @Override
    public int hashCode() {
        return getValue() % 2;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
