package com.JavaLab.sorters;

import com.JavaLab.Human;

import java.util.Comparator;

public class SelectionSort implements Sort {

    @Override
    public Human[] sort(Human[] human, Comparator comparator) {

        for (int i = 0; i < human.length; i++) {
            Human min = human[i];
            int min_i = i;
            for (int j = i + 1; j < human.length; j++) {
                if (comparator.compare(min, human[j]) > 0) {
                    min = human[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                Human tmp = human[i];
                human[i] = human[min_i];
                human[min_i] = tmp;
            }
        }

        return human;
    }
}
