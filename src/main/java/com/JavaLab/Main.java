package com.JavaLab;

import com.JavaLab.Checkers.CheckerBrd;
import com.JavaLab.Checkers.CheckerName;
import com.JavaLab.Checkers.CheckerSex;
import com.JavaLab.Comparator.ComparatorBrd;
import com.JavaLab.Comparator.ComparatorName;
import com.JavaLab.Comparator.ComparatorSex;
import com.JavaLab.Injectors.Injector;
import com.JavaLab.Sorters.BubbleSort;
import com.JavaLab.Sorters.SelectionSort;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Класс для открывания консоли пользователю, выбора из списка действий
 *
 * @author Воротников Дмитрий
 */
public class Main {
    /**
     * статическая переменная котроая определяет класс Repository
     */
    static Repository repository;

    private static final Logger logger = Logger.getLogger(Main.class);
    /**
     * главный метод для запуска
     *
     * @param args
     */
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Выберите сортировку:");
        //System.out.println("1 - Пузырек");
        //System.out.println("2 - Выбором");
        //int sortType = scanner.nextInt();
        //scanner.nextLine();

        //logger.info("select sort number: " + sortType);

        //switch (sortType) {
        //    case 1:
        //        repository = new Repository();
        //        break;
        //    case 2:
        //        repository = new Repository();
        //        break;
        //}

        Injector inj = new Injector();
        inj.inject(new Repository());
        repository = new Repository();

        addPersons();

        boolean bool = true;
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
            logger.info("select item number : " + number);
            switch (number) {
                case 1:
                    newPerson();
                    viewPersons();
                    break;
                case 2:
                    viewPersons();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    viewPerson();
                    break;
                case 5:
                    sort();
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

    /**
     * Создание стандартных людей
     */
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

        viewPersons();

    }

    /**
     * Создание нового человека
     */
    private static void newPerson() {
        System.out.println("Введите имя:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        logger.info("new man, name: " + name);

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

        logger.info("new man, brd: " + brd);

        System.out.println("Введите пол (female или male):");
        scanner = new Scanner(System.in);
        String sex = scanner.nextLine();

        logger.info("new man, sex: " + sex);

        Human human = new Human(name, inputDate, sex);
        repository.insert(human);

    }

    /**
     * Просмотр всех людей в репозитории
     */
    private static void viewPersons() {
        int length = repository.getLength();
        if (length == 0) {
            System.out.println("пусто");
            logger.info("an empty repository");
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
    private static void deletePerson() {
        System.out.println("Введите id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        logger.info("id to delete a person number : " + id);
        repository.delete(id);
    }

    /**
     * Просмотр человека из репозитория по id
     */
    private static void viewPerson() {
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

    /**
     * Вызов сортировки
     */
    public static void sort() {
        System.out.println("Отсортирoвать по : \n " +
                "1. Имени \n " +
                "2. Году рождения \n " +
                "3. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                logger.info("sorting by name is selected");
                repository.sortBy(new ComparatorName());
                viewPersons();
                break;
            case 2:
                logger.info("sorting by brd is selected");
                repository.sortBy(new ComparatorBrd());
                viewPersons();
                break;
            case 3:
                logger.info("sorting by sex is selected");
                repository.sortBy(new ComparatorSex());
                viewPersons();
                break;
        }
    }

    /**
     * Поиск
     */
    private static void SearchValue() {
        System.out.println("Поиск по : \n " +
            "1. Имени \n " +
            "2. Году рождения \n " +
            "3. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        Human[] forSearch = new Human[0];
        switch (number) {
            case 1:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                String value = scanner.next();
                forSearch = repository.searchBy(new CheckerName(), value);
                logger.info("selected search by name with the value of :" + value);
                break;
            case 2:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                value = scanner.next();
                forSearch = repository.searchBy(new CheckerBrd(), value);
                logger.info("selected search by brd with the value of :" + value);
                break;
            case 3:
                System.out.println("Введите значение для поиска ");
                scanner = new Scanner(System.in);
                value = scanner.next();
                forSearch = repository.searchBy(new CheckerSex(), value);
                logger.info("selected search by sex with the value of :" + value);
                break;
        }

        for (int i = 0; forSearch.length>i; i++){
            System.out.println(" id = " + forSearch[i].getId() +
                    " Полное имя " + forSearch[i].getName() +
                    " Дата рождения " + forSearch[i].getBrd() +
                    " возраст " + forSearch[i].getAge() +
                    " Пол " + forSearch[i].getSex());
        }
    }

}