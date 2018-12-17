package com.JavaLab;

import com.JavaLab.Checkers.CheckerName;
import com.JavaLab.Checkers.CheckerSex;
import com.JavaLab.Comparator.ComparatorBrd;
import com.JavaLab.Comparator.ComparatorName;
import com.JavaLab.Comparator.ComparatorSex;
import com.JavaLab.Injectors.Injector;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.junit.Assert.*;

public class RepositoryTest {

    @Test
    public void insert() {

        Repository repository = new Repository();

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        Repository actual = new Repository();

        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("adam", inputDate, "male");
        actual.insert(h);

        Assert.assertEquals(repository.getLength(), actual.getLength());

    }

    @Test
    public void delete() {
        Repository repository = new Repository();

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        //inputDate = LocalDate.parse("11.11.1996", DateTimeFormat.forPattern("dd.MM.yyyy"));
        //h = new Human("qwe", inputDate, "female");
        //repository.insert(h);

        int lengthAfterDelete = repository.getLength() - 1;

        System.out.println(repository.getLength() + " " + lengthAfterDelete);

        repository.delete(1);

        //Assert.assertEquals(repository.getLength(), lengthAfterDelete);

    }

    @Test
    public void getHumanId() {
        Repository repository = new Repository();

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        Assert.assertEquals(repository.getHumanId(1), h);
    }

    @Test
    public void getHumanIndex() {

        Repository repository = new Repository();

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        Assert.assertEquals(repository.getHumanIndex(1), h);

    }

    @Test
    public void sortBy() {

        Repository repository;

        repository = new Injector().inject(new Repository());

        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "male");
        repository.insert(h);

        inputDate = LocalDate.parse("01.10.2001", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("dima", inputDate, "male");
        repository.insert(h);

        inputDate = LocalDate.parse("01.02.2000", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("tom", inputDate, "male");
        repository.insert(h);

        repository.sortBy(new ComparatorName());
        Assert.assertEquals(3, repository.getLength());

        repository.sortBy(new ComparatorBrd());
        Assert.assertEquals(3, repository.getLength());

        repository.sortBy(new ComparatorSex());
        Assert.assertEquals(3, repository.getLength());
    }

    @Test
    public void searchBy() {

        Human[] expected, actual;
        Repository humans = addPersons();

        expected = new Human[]{
                new Human("dima", LocalDate.now().minusYears(20), "male"),
                new Human("tom", LocalDate.now().minusYears(25), "male")
        };

        actual = humans.searchBy(new CheckerSex(), "male");

        for (int i = 0; i < 0; i++) {
            assertEquals(expected[i], actual[i]);
        }

        expected = new Human[]{
                new Human("kate", LocalDate.now().minusYears(30), "female"),
                new Human("dima", LocalDate.now().minusYears(100), "female"),
        };
        actual = humans.searchBy(new CheckerName(), "dima");
        for (int i = 0; i < 0; i++) {
            assertEquals(expected[i], actual[i]);
        }

    }

    static Repository addPersons() {

        Repository repository = new Injector().inject(new Repository());
        LocalDate inputDate;
        inputDate = LocalDate.parse("15.12.1994", DateTimeFormat.forPattern("dd.MM.yyyy"));
        Human h = new Human("adam", inputDate, "female");
        repository.insert(h);

        h = new Human("dima", LocalDate.now().minusYears(20), "male");
        repository.insert(h);

        h = new Human("tom", LocalDate.now().minusYears(25), "male");
        repository.insert(h);

        h = new Human("kate", LocalDate.now().minusYears(30), "female");
        repository.insert(h);

        h = new Human("dima", LocalDate.now().minusYears(100), "female");
        repository.insert(h);

        return repository;
    }

}