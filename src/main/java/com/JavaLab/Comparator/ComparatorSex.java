package com.JavaLab.Comparator;

import com.JavaLab.Human;

import java.util.Comparator;

public class ComparatorSex implements Comparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getSex().compareTo(b.getSex());
    }

}