package com.JavaLab.comparator;

import com.JavaLab.Human;

import java.util.Comparator;

public class ComparatorBrd implements Comparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getBrd().compareTo(b.getBrd());
    }

}