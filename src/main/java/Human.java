import org.joda.time.LocalDate;

/**
 * Класс со свойствами name, brd, sex, id, idHuman
 *
 * @author Воротников Дмитрий
 */
public class Human {
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
     * поле id
     */
    private int id;
    /**
     * поле возраста
     */
    private int age;
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
    Human(String name, LocalDate brd, String sex) {
        this.name = name;
        this.brd = brd;
        this.sex = sex;
        this.id = idHuman;
        idHuman++;
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

    public void setAge(int age) {
        this.age = age;
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
