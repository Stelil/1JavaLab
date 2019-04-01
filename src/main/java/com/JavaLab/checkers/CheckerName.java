package com.JavaLab.checkers;

import com.JavaLab.Human;

public class CheckerName implements Checker {

    @Override
    public boolean check(Human human, Object object) {
        String str = (String) object;
        return human.getName().equals(str);
    }
}