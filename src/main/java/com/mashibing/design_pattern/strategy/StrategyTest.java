package com.mashibing.design_pattern.strategy;

import java.util.Arrays;
import java.util.Collections;

public class StrategyTest {

    public static void main(String[] args) {

        Cat cat1 = new Cat(1, 2);
        Cat cat2 = new Cat(3, 3);
        Cat cat3 = new Cat(2, 1);

        Cat[] cats = {cat1, cat2, cat3};

        Sort.sort(cats, new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.weight - o2.weight;
            }
        });

        System.out.println(Arrays.toString(cats));
    }
}
