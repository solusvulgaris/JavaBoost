package com.ak.collections;

import java.math.BigDecimal;
import java.util.TreeSet;

public class BigDecimalTreeSet {
    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("1.0");
        BigDecimal bd2 = new BigDecimal("1.00");

        // standard numeric value
        // NOTE: we don`t need equals & hashCode to compare object, only compare() implementation
        System.out.println(bd1.equals(bd2)); //false
        System.out.println(bd1.hashCode() == bd2.hashCode()); //false
        System.out.println(bd1 == bd2); //false
        TreeSet<BigDecimal> ts = new TreeSet<>();
        ts.add(bd1);
        ts.add(bd2);
        System.out.println(ts); //[1.0]

    }
}
