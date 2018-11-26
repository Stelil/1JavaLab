package com.JavaLab;

import java.util.Comparator;

class ComparatorAge implements Comparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getAge().compareTo(b.getAge());
    }

}