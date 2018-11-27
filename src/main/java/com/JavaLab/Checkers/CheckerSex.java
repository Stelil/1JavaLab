package com.JavaLab.Checkers;

import com.JavaLab.Human;

public class CheckerSex implements Checker {
    @Override
    public boolean check(Human human, Object object) {
        String str = (String) object;
        return human.getSex().equals(str);
    }
}
