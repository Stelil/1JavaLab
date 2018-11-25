import org.joda.time.format.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Репозиторий людей
 *
 * @author Воротников Дмитрий
 */
public class Repository implements InterafaceSort, InterfaceSearch {

    /**
     * массив людей
     */
    private Human[] array;

    /**
     * конструктор, который создаёт массив
     */
    Repository() {
        array = new Human[0];
    }

    /**
     * Вставляет созданового только что человека в массив
     *
     * @param human объект человека
     */
    public void insert(Human human) {
        Human[] oneHuman = {human};

        int length = array.length + 1;
        Human[] res = new Human[length];
        System.arraycopy(array, 0, res, 0, array.length);
        System.arraycopy(oneHuman, 0, res, array.length, oneHuman.length);

        this.array = res;
    }

    /**
     * Удаляет чкеловека из массива
     *
     * @param id id под которым человек записан в массиве
     */
    public void delete(int id) {
        for (int i = 0; this.array.length > i; ) {
            if (this.array[i].getId() == id) {
                int length = this.array.length - 1;
                Human[] newArr = new Human[length];
                System.arraycopy(this.array, 0, newArr, 0, i);
                System.arraycopy(this.array, i + 1, newArr, i, this.array.length - i - 1);
                this.array = newArr;
                System.out.println("Человек удалён");
            }
        }
    }

    /**
     * Функция для получение длины массива
     *
     * @return возвращает длину массива
     */
    public int getLength() {
        return this.array.length;
    }

    /**
     * Функция для получения объекта человека
     *
     * @param id id под которым человек записан в массиве
     * @return возвращет объект человека
     */
    public Human getHumanId(int id) {
        for (int i = 0; this.array.length > i; i++) {
            if (this.array[i].getId() == id) {
                return array[i];
            }
        }
        return null;
    }

    /**
     * Функция для получения объекта челвека
     *
     * @param index указывает на место в массиве
     * @return возвращает объект человека
     */
    public Human getHumanIndex(int index) {
        return array[index];
    }

    @Override
    public void bubbleSort() {
        System.out.println("Отсортирoвать по : \n " +
                "1. Имени \n " +
                "2. Году рождения \n " +
                "3. Возрасту \n " +
                "4. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        long timeSpent;
        switch (number) {
            case 1:
                ComparatorName comparatorName = new ComparatorName();
                for (int i = array.length - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        int compare = comparatorName.compare(array[i], array[j]);
                        if (compare < 0) {
                            Human tmp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = tmp;
                        }
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 2:
                ComparatorBrd comparatorBrd = new ComparatorBrd();
                for (int i = array.length - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        int compare = comparatorBrd.compare(array[i], array[j]);
                        if (compare < 0) {
                            Human tmp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = tmp;
                        }
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 3:
                ComparatorAge comparatorAge = new ComparatorAge();
                for (int i = array.length - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        int compare = comparatorAge.compare(array[i], array[j]);
                        if (compare < 0) {
                            Human tmp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = tmp;
                        }
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 4:
                ComparatorSex comparatorSex = new ComparatorSex();
                for (int i = array.length - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        int compare = comparatorSex.compare(array[i], array[j]);
                        if (compare < 0) {
                            Human tmp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = tmp;
                        }
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
        }
    }

    @Override
    public void selectionSort() {
        System.out.println("Отсортирoвать по : \n " +
                "1. Имени \n " +
                "2. Году рождения \n " +
                "3. Возрасту \n " +
                "4. Полу");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long startTime = System.currentTimeMillis();
        long timeSpent;
        switch (number) {
            case 1:
                ComparatorName comparatorName = new ComparatorName();

                for (int i = 0; i < array.length; i++) {
                    Human min = array[i];
                    int min_i = i;
                    for (int j = i + 1; j < array.length; j++) {
                        if (comparatorName.compare(min, array[j]) > 0) {
                            min = array[j];
                            min_i = j;
                        }
                    }
                    if (i != min_i) {
                        Human tmp = array[i];
                        array[i] = array[min_i];
                        array[min_i] = tmp;
                    }
                }

                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 2:
                ComparatorBrd comparatorBrd = new ComparatorBrd();
                for (int i = 0; i < array.length; i++) {
                    Human min = array[i];
                    int min_i = i;
                    for (int j = i + 1; j < array.length; j++) {
                        if (comparatorBrd.compare(min, array[j]) > 0) {
                            min = array[j];
                            min_i = j;
                        }
                    }
                    if (i != min_i) {
                        Human tmp = array[i];
                        array[i] = array[min_i];
                        array[min_i] = tmp;
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 3:
                ComparatorAge comparatorAge = new ComparatorAge();
                for (int i = 0; i < array.length; i++) {
                    Human min = array[i];
                    int min_i = i;
                    for (int j = i + 1; j < array.length; j++) {
                        if (comparatorAge.compare(min, array[j]) > 0) {
                            min = array[j];
                            min_i = j;
                        }
                    }
                    if (i != min_i) {
                        Human tmp = array[i];
                        array[i] = array[min_i];
                        array[min_i] = tmp;
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 4:
                ComparatorSex comparatorSex = new ComparatorSex();
                for (int i = 0; i < array.length; i++) {
                    Human min = array[i];
                    int min_i = i;
                    for (int j = i + 1; j < array.length; j++) {
                        if (comparatorSex.compare(min, array[j]) > 0) {
                            min = array[j];
                            min_i = j;
                        }
                    }
                    if (i != min_i) {
                        Human tmp = array[i];
                        array[i] = array[min_i];
                        array[min_i] = tmp;
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
        }
    }

    @Override
    public void searchValue() {
        System.out.println("Поиск по : \n " +
                "1. Фамилии \n " +
                "2. Возрасту \n " +
                "3. Дате рождения \n " +
                "4. Полу \n ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long timeSpent;

        Human value = null;
        long startTime = System.currentTimeMillis();
        switch (number) {
            case 1:
                System.out.println("Введите имя для поиска:");
                scanner = new Scanner(System.in);
                value = new Human(scanner.next(), null, null);

                ComparatorName comparatorName = new ComparatorName();
                for (int i = 0; array.length > i; i++) {
                    if (comparatorName.compare(array[i], value) == 0) {

                        System.out.println(" id = " + array[i].getId() +
                                " Полное имя " + array[i].getName() +
                                " Дата рождения " + array[i].getBrd() +
                                " возраст " + array[i].getAge() +
                                " Пол " + array[i].getSex());
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 2:

                System.out.println("Введите возраст для поиска:");
                scanner = new Scanner(System.in);
                value = new Human(null, null, null);
                value.setAge(scanner.nextInt());

                ComparatorAge comparatorAge = new ComparatorAge();
                for (int i = 0; array.length > i; i++) {
                    if (comparatorAge.compare(array[i], value) == 0) {

                        System.out.println(" id = " + array[i].getId() +
                                " Полное имя " + array[i].getName() +
                                " Дата рождения " + array[i].getBrd() +
                                " возраст " + array[i].getAge() +
                                " Пол " + array[i].getSex());
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 3:

                System.out.println("Введите дату рождения для поиска (dd.mm.yyyy):");
                scanner = new Scanner(System.in);

                org.joda.time.LocalDate inputDate;
                inputDate = org.joda.time.LocalDate.parse(scanner.next(), DateTimeFormat.forPattern("dd.MM.yyyy"));

                value = new Human(null, inputDate, null);

                ComparatorBrd comparatorBrd = new ComparatorBrd();
                for (int i = 0; array.length > i; i++) {
                    if (comparatorBrd.compare(array[i], value) == 0) {

                        System.out.println(" id = " + array[i].getId() +
                                " Полное имя " + array[i].getName() +
                                " Дата рождения " + array[i].getBrd() +
                                " возраст " + array[i].getAge() +
                                " Пол " + array[i].getSex());
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
            case 4:

                System.out.println("Введите дату рождения для поиска (dd.mm.yyyy):");
                scanner = new Scanner(System.in);
                value = new Human(null, null, scanner.next());

                ComparatorSex comparatorSex = new ComparatorSex();
                for (int i = 0; array.length > i; i++) {
                    if (comparatorSex.compare(array[i], value) == 0) {

                        System.out.println(" id = " + array[i].getId() +
                                " Полное имя " + array[i].getName() +
                                " Дата рождения " + array[i].getBrd() +
                                " возраст " + array[i].getAge() +
                                " Пол " + array[i].getSex());
                    }
                }
                timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("*программа выполнялась " + timeSpent + " миллисекунд*");
                break;
        }
    }
}