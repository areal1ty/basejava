package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, 3};
        System.out.println(minValue(arr));

        List<Integer> integers1 = List.of(1, 2, 3);
        List<Integer> integers2 = List.of(1, 2, 3, 1);
        System.out.println(oddOrEven(integers1));
        System.out.println(oddOrEven(integers2));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0 ,(a, b) -> a * 10 + b);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream().filter(n -> (sum % 2 == 0) == (n % 2 != 0))
                .collect(Collectors.toList());
    }
}
