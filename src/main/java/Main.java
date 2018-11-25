import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Scanner;

/**
 * Класс для открывания консоли пользователю, выбора из списка действий
 *
 * @author Воротников Дмитрий
 */
public class Main {
    /**
     * статическая переменная котроая определяет класс Repository
     */
    static Repository repository = new Repository();

    /**
     * главный метод для запуска
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean bool = true;
        addPersons();
        while (bool) {
            System.out.println("Возможные действия : \n " +
                    "1. Добавить человека \n " +
                    "2. Показать весь список \n " +
                    "3. Удалить человека из списка по id \n " +
                    "4. Показать человека по id \n " +
                    "5. Отсортировать репозиторий \n " +
                    "6. Поиск в репозитории \n " +
                    "7. Выход");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    NewPerson();
                    ViewPersons();
                    break;
                case 2:
                    ViewPersons();
                    break;
                case 3:
                    DeletePerson();
                    break;
                case 4:
                    ViewPerson();
                    break;
                case 5:
                    SortPerson();
                    break;
                case 6:
                    SearchValue();
                    break;
                case 7:
                    bool = false;
                    break;
            }
        }
    }

    static void addPersons() {

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

        inputDate = LocalDate.parse("25.05.1999", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("kate", inputDate, "female");
        repository.insert(h);

        inputDate = LocalDate.parse("11.11.1995", DateTimeFormat.forPattern("dd.MM.yyyy"));
        h = new Human("dima", inputDate, "male");
        repository.insert(h);

        ViewPersons();

    }

    /**
     * Создание нового человека
     */
    private static void NewPerson() {
        System.out.println("Введите имя:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Введите дату рождения (dd.mm.yyyy):");
        scanner = new Scanner(System.in);
        String brd = scanner.nextLine();

        DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
        LocalDate inputDate;

        try {
            inputDate = LocalDate.parse(brd, dateFormat);
        } catch (Throwable t) {
            System.out.println("Неверный формат даты!");
            return;
        }

        System.out.println("Введите пол (female или male):");
        scanner = new Scanner(System.in);
        String sex = scanner.nextLine();

        Human human = new Human(name, inputDate, sex);
        repository.insert(human);

    }

    /**
     * Просмотр всех людей в репозитории
     */
    private static void ViewPersons() {
        int length = repository.getLength();
        if (length == 0) {
            System.out.println("пусто");
        } else {
            for (int i = 0; length > i; i++) {
                System.out.println(" id = " + repository.getHumanIndex(i).getId() +
                        " Полное имя = " + repository.getHumanIndex(i).getName() +
                        " Дата рождения = " + repository.getHumanIndex(i).getBrd() +
                        " Возраст " + repository.getHumanIndex(i).getAge() +
                        " Пол = " + repository.getHumanIndex(i).getSex());
            }
        }

    }

    /**
     * Удаления человека из репозитория по id
     */
    private static void DeletePerson() {
        System.out.println("Введите id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        repository.delete(id);
    }

    /**
     * Просмотр человека из репозитория по id
     */
    private static void ViewPerson() {
        System.out.println("Введите id:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Human human = repository.getHumanId(id);

        DateTime dateTime = DateTime.now();
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd.MM.yyyy");
        System.out.println(dtfOut.print(dateTime));

        System.out.println(" id = " + human.getId() +
                " Полное имя " + human.getName() +
                " Дата рождения " + human.getBrd() +
                " возраст " + human.getAge() +
                " Пол " + human.getSex());

    }

    private static void SortPerson() {
        System.out.println("Тип сортировки : \n " +
                "1. Пузырьком \n " +
                "2. Быстрой \n ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                repository.bubbleSort();
                ViewPersons();
                break;
            case 2:
                repository.selectionSort();
                ViewPersons();
                break;
        }
    }

    private static void SearchValue(){
        repository.searchValue();
    }

}