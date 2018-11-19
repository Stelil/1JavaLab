import org.joda.time.LocalDate;

public class Human {

    private String name;
    private LocalDate brd;
    private String sex;
    private int id;
    private static int idHuman = 1;

    Human(String name, LocalDate brd, String sex) {
        this.name = name;
        this.brd = brd;
        this.sex = sex;
        this.id = idHuman;
        idHuman++;
    }

    public Integer getAge() {
        Integer year = brd.getYear(),
                month = brd.getMonthOfYear() - 1,
                day = brd.getDayOfMonth() - 1;
        LocalDate tempDate = new LocalDate().minusDays(day).minusMonths(month).minusYears(year);

        return tempDate.getYear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBrd() {
        return brd;
    }

    public void setBrd(LocalDate brd) {
        this.brd = brd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
