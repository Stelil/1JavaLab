package com.JavaLab;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс со свойствами name, brd, sex, id, idHuman
 *
 * @author Воротников Дмитрий
 */
@XmlType(propOrder = {"id", "name", "brd", "sex"})
public class Human {
    /**
     * поле id
     */
    private int id;
    /**
     * поле имени
     */
    private String name;
    /**
     * поле дня рождения
     */
    private LocalDate brd;
    /**
     * поле пола
     */
    private String sex;
    /**
     * поле id, которое копится
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
    @XmlElement()
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
    @XmlElement()
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
    @XmlElement()
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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
    @XmlElement()
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

    static public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
        public LocalDate unmarshal(String v) throws Exception {
            System.out.println(v);
            LocalDate inputDate;
            inputDate = LocalDate.parse(v, DateTimeFormat.forPattern("yyyy-MM-dd"));
            System.out.println(inputDate);
            return inputDate;
        }

        public String marshal(LocalDate v) throws Exception {
            return v.toString();
        }
    }
}
