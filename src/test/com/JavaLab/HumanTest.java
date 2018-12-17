package com.JavaLab;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanTest {

    @Test
    public void getId() {
    }

    @Test
    public void getAge() {
        Repository repository = new Repository();
        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);
        Assert.assertEquals(h.getAge(), Integer.valueOf("24"));
    }

    @Test
    public void getName() {
        Repository repository = new Repository();

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        Assert.assertEquals(h.getName(), "adam");
    }

    @Test
    public void getBrd() {
        Repository repository = new Repository();
        Human h = new Human("adam", LocalDate.now().minusYears(10), "male");
        repository.insert(h);
        Assert.assertEquals(h.getBrd(), LocalDate.now().minusYears(10));
    }

    @Test
    public void getSex() {
        Repository repository = new Repository();
        Human h = new Human("adam", LocalDate.now().minusYears(10), "male");
        repository.insert(h);
        Assert.assertEquals(h.getSex(), "male");
    }
}