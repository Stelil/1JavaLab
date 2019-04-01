package com.JavaLab;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "students")
@XmlRootElement
class Students {
    @XmlElementWrapper(name = "student", nillable = true)
    public List student = new ArrayList();
}

/**
 * Класс со свойствами name, brd, sex, id, idHuman
 *
 * @author Воротников Дмитрий
 */
@XmlType(name = "student")
public class Human {
    /**
     * поле id
     */
    @XmlElement(name = "id")
    private int id;
    /**
     * поле имени
     */
    @XmlElement(name = "name")
    private String name;
    /**
     * поле дня рождения
     */
    @XmlElement(name = "brd")
    private LocalDate brd;
    /**
     * поле пола
     */
    @XmlElement(name = "sex")
    private String sex;
    /**
     * поле idб которое копится
     */
    private static int idHuman = 1;

    /**
     * Конструктор
     *
     * @param name поле имени
     * @param brd  поле дня рождения
     * @param sex  поле пола
     */
    public Human(String name, LocalDate brd, String sex) {
        this.name = name;
        this.brd = brd;
        this.sex = sex;
        this.id = idHuman;
        idHuman++;
    }

    public Human() {
    }

    /**
     * функция для получения возраста
     *
     * @return возвращает возраст
     */
    public Integer getAge() {
        Integer year = brd.getYear(),
                month = brd.getMonthOfYear() - 1,
                day = brd.getDayOfMonth() - 1;
        LocalDate tempDate = new LocalDate().minusDays(day).minusMonths(month).minusYears(year);

        return tempDate.getYear();
    }

    /**
     * функция получения id
     *
     * @return возврщает id
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * функция получения имени
     *
     * @return возвращает имя
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения имени
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * функция получения года рождения
     *
     * @return возвращает год рождения
     */
    public LocalDate getBrd() {
        return brd;
    }

    /**
     * Процедура определения года рождения
     *
     * @param brd
     */
    public void setBrd(LocalDate brd) {
        this.brd = brd;
    }

    /**
     * функция получения пола человека
     *
     * @return возвращает пол человека
     */
    public String getSex() {
        return sex;
    }

    /**
     * Процедура определения пола человека
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
}
