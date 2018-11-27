package com.JavaLab.Sorters;

import com.JavaLab.Human;

import java.util.Comparator;

public class BubbleSort implements Sort {

    @Override
    public Human[] sort(Human[] human, Comparator comparator) {

        for (int i = human.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int compare = comparator.compare(human[i], human[j]);
                if (compare < 0) {
                    Human tmp = human[j];
                    human[j] = human[j + 1];
                    human[j + 1] = tmp;
                }
            }
        }

        return human;
    }
}
