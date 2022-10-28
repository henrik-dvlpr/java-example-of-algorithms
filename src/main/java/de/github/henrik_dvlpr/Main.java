package de.github.henrik_dvlpr;

import de.github.henrik_dvlpr.algorithm.BinarySearch;
import de.github.henrik_dvlpr.test_data.FindUniversities;
import de.github.henrik_dvlpr.test_data.University;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class Main {
    public static void main(String[] args) throws Exception {
        // loading test data
        final var universities = new FindUniversities().find();

        // test binary search
        runTestBinarySearch(universities);
    }

    private static void runTestBinarySearch(final List<University> universities) {
        // convert to sorted university names list
        final var sortedListOfUniversities = universities.stream()
                .map(University::getName).sorted(Comparator.naturalOrder()).toList();
        // binary search and track runtime
        trackRuntime("binary search", () -> testBinarySearch(sortedListOfUniversities));
    }

    private static void testBinarySearch(final List<String> universityNames) {
        // using binary search
        final var indexOfSearchedValue = new BinarySearch<String>()
                .indexOf(universityNames, "Massachusetts Institute of Technology");

        System.out.println("Total items: " + universityNames.size());

        if (indexOfSearchedValue.isPresent()) {
            System.out.println("Value at index " + indexOfSearchedValue.get() + " is " + universityNames.get(indexOfSearchedValue.get()));
        } else {
            System.out.println("Not found in list!");
        }
    }

    private static void trackRuntime(final String name, final Runnable runnable) {
        final var startTimeMillis = System.currentTimeMillis();
        runnable.run();
        final var endTimeMillis = System.currentTimeMillis();

        long runtimeMillis = endTimeMillis - startTimeMillis;
        System.out.println("Total runtime of '" + name + "': " + runtimeMillis + "ms");
    }
}
