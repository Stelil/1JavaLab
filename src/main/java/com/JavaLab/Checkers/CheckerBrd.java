package com.JavaLab.Checkers;

import com.JavaLab.Human;

public class CheckerBrd implements Checker {
    @Override
    public boolean check(Human human, Object object) {
        String str = (String) object;
        return human.getBrd().equals(str);
    }
}