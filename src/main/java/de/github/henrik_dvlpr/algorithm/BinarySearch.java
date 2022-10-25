package de.github.henrik_dvlpr.algorithm;

import java.util.List;
import java.util.Optional;

public class BinarySearch<T extends Comparable<T>> {
    public Optional<Integer> indexOf(final List<T> values, T value) {
        // counter for how often we have to iterate over the list
        var attempts = 0;

        // binary search
        var low = 0;
        var high = values.size() - 1;
        var mid = low + high;

        Optional<Integer> result = Optional.empty();

        while (low <= high) {
            attempts++;
            var guess = values.get(mid);

            if (guess.equals(value)) {
                result = Optional.of(mid);
                break;
            }

            if (guess.compareTo(value) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = (high + low) / 2;
        }

        System.out.println("Attempts: " + attempts);
        return result;
    }
}
