package de.github.henrik_dvlpr;

import de.github.henrik_dvlpr.algorithm.BinarySearch;
import de.github.henrik_dvlpr.test_data.FindUniversities;
import de.github.henrik_dvlpr.test_data.University;

import java.util.*;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class Main {
    public static void main(String[] args) throws Exception {
        // loading test data
        final var universities = new FindUniversities().find();

        testBinarySearch(universities);
    }

    private static void testBinarySearch(List<University> universities) {
        // sorting list
        final var sortedListOfUniversities = universities.stream()
                .map(University::getName).sorted(Comparator.naturalOrder()).toList();
        // using binary search
        final var indexOfSearchedValue = new BinarySearch<String>()
                .indexOf(sortedListOfUniversities, "Massachusetts Institute of Technology");

        System.out.println("Total items: " + sortedListOfUniversities.size());

        if (indexOfSearchedValue.isPresent()) {
            System.out.println("Value at index " + indexOfSearchedValue.get() + " is " +
                    sortedListOfUniversities.get(indexOfSearchedValue.get()));
        } else {
            System.out.println("Not found in list!");
        }
    }
}
