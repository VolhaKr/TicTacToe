package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UtilsSource {
    public static String tableString1(char[][] table) {
        return Arrays.stream(table)
                .map(String::new)
                .collect(Collectors.joining("\n"));
    }
}

