package ru.anhimov.SpringBootWithSecurity.security;

import java.util.HashSet;
import java.util.LinkedList;

public class File2 {
    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();
        HashSet<Integer> integersSet = new HashSet<>(integers);
        integersSet.forEach(integer -> integer = integer * integer);
    }
}
