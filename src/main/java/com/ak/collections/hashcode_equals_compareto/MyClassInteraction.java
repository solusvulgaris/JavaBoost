package com.ak.collections.hashcode_equals_compareto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyClassInteraction {
    public static void main(String[] args) {
        int[] initialArray = new int[] {23, 8, 42, 5};

        // all elements
        List<MyClass> myList = new ArrayList<>();

        // unique, sorted by natural ordering, i.e. by compareTo()
        SortedSet<MyClass> mySortedSet = new TreeSet<>();

        // keys comparing by equals()
        HashMap<MyClass, Integer> myHashMap = new HashMap<>();

        // keys comparing by == instead of equals
        IdentityHashMap<MyClass, Integer> myIdentityHashMap = new IdentityHashMap<>();

        // compare based on overridden compareTo()
        TreeSet<MyClass> myTreeSetDefaultComparison = new TreeSet<>();

        // compare based on comparator
        TreeSet<MyClass> myTreeSetComparator = new TreeSet<>(new MyClassHashCodeComparator());

        int indexA = 0;
        int indexB = 0;
        for (int i = 0; i < 4; i++) {
            int num = initialArray[i];

            MyClass mC1 = new MyClass(num);
            MyClass mC2 = new MyClass(num);

            myList.add(mC1);
            myList.add(mC1);
            myList.add(mC2);

            mySortedSet.add(mC1);
            mySortedSet.add(mC1);//the same object = ref to object - will not be added
            mySortedSet.add(mC2);//the same value <- compared by value (by compareTo()) - will not be added

            myHashMap.put(mC1, ++indexA);
            myHashMap.put(mC1, ++indexA);//key is the same object = ref to object - map value will be changed
            myHashMap.put(mC2, ++indexA);//key has the same value <- compared by equals() - only map value will be changed

            myIdentityHashMap.put(mC1, ++indexB);
            myIdentityHashMap.put(mC1, ++indexB);//key is the same object = ref to object - map value will be changed
            myIdentityHashMap.put(mC2, ++indexB);//key has different reference, despite the same value - new key-value pair will be added

            myTreeSetDefaultComparison.add(mC1);
            myTreeSetDefaultComparison.add(mC1);//the same object = ref to object - will not be added
            myTreeSetDefaultComparison.add(mC2);//the same value <- compared by value (by compareTo()) - will not be added

            myTreeSetComparator.add(mC1);
            myTreeSetComparator.add(mC1);//the same object = the same value = the same hash code - will not be added
            myTreeSetComparator.add(mC2);//the same value = the same hash code - will not be added
        }
                                   //[0,  7,  5,  0, 3, 4, 0,  1, 8,  0,  6,  2 ] -> the order in which it was added to IdentityHashMap
                                   //[1,  2,  3,  4, 5, 6, 7,  8,  9, 10, 11, 12] -> indexes = values in IdentityHashMap
        System.out.println(myList);//[23, 23, 23, 8, 8, 8, 42, 42, 42, 5, 5, 5] - all values in order of addition
        Collections.sort(myList);
        System.out.println(myList);//[5, 5, 5, 8, 8, 8, 23, 23, 23, 42, 42, 42] - all values sorted by compareTo()
        System.out.println(mySortedSet);//[5, 8, 23, 42] - unique sorted values (by compareTo())
        System.out.println(myHashMap);//8=6, 42=9, 23=12
        //map values for the first unique key objects were replaced by map values of the key objects duplicates added later
        //i.e. firstly 23-1 key-value pair was added to HashMap,
        // then it map value was replaced by "2" from the 23-2 key-value pair (the key value "23" is the same, key object MyClass is the same)
        // then it map value was replaced by "3" from the 23-3 key-value pair (the key value "23" is still the same, despite the fact that it is a different MyClass object)
        // then it map value was replaced by "10" from the 5-10 key-value pair (as HashMap compares keys across equals() method implementation, and equals() method in MyClass compares instances by digits sum in value number, and 2+3=5)
        // then it map value was replaced by "11" from the 5-10 key-value pair (same reason)
        // and finally we get 23-12 key-value pair, as key (23) remind from the first addition, and value (12) - from the last 5-12 key-value pair

        System.out.println(myIdentityHashMap);//42=8, 5=12, 8=5, 8=6, 23=3, 5=11, 23=2, 42=9
        //map values for the first unique key objects were replaced by map values of the key objects duplicates added later
        //sorted by objects memory location, i.e. by ==
        //! each time you start the application, the order can change, as the addresses of cells in memory can change

        System.out.println(myTreeSetDefaultComparison); // [5, 8, 23, 42] - unique sorted values
        System.out.println(myTreeSetComparator); //[8, 23] - unique sorted by hashCode(),
        // i.e. by remainder of division by 2, viz. first even and first odd in order of addition
    }

    protected static class MyClassHashCodeComparator implements Comparator<MyClass> {

        @Override
        public int compare(MyClass o1, MyClass o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    }
}
