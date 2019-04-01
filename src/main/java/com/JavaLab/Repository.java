package com.JavaLab;

import com.JavaLab.annotations.AutoInjectable;
import com.JavaLab.checkers.Checker;
import com.JavaLab.sorters.Sort;

import org.apache.log4j.Logger;

import java.util.Comparator;

/**
 * Репозиторий людей
 *
 * @author Воротников Дмитрий
 */
class Repository {

    /**
     * массив людей
     */
    private Human[] array;

    @AutoInjectable()
    private Sort sort;

    private static final Logger logger = Logger.getLogger(Repository.class);

    /**
     * конструктор, который создаёт массив
     */
    Repository() {
        this.array = new Human[0];

    }

    private Human[] concat(Human[] a, Human[] b) {
        int length = a.length + b.length;
        Human[] res = new Human[length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }

    /**
     * Вставляет созданового только что человека в массив
     *
     * @param human объект человека
     */
    public void insert(Human human) {
        Human[] oneHuman = {human};
        this.array = concat(array, oneHuman);

        logger.info("person added");
    }

    /**
     * Удаляет чкеловека из массива
     *
     * @param id id под которым человек записан в массиве
     */
    public void delete(int id) {
        for (int i = 0; this.array.length > i; i++) {
            if (this.array[i].getId() == id) {
                int length = this.array.length - 1;
                Human[] newArr = new Human[length];
                System.arraycopy(this.array, 0, newArr, 0, i);
                System.arraycopy(this.array, i + 1, newArr, i, this.array.length - i - 1);
                this.array = newArr;
            }
        }
        logger.info("person deleted");
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
     * Функция для получения объекта челoвека
     *
     * @param index указывает на место в массиве
     * @return возвращает объект человека
     */
    public Human getHumanIndex(int index) {
        return array[index];
    }

    public void sortBy(Comparator comparator) {
        this.array = sort.sort(this.array, comparator);
    }

    public Human[] searchBy(Checker checker, Object value) {
        Human[] check = new Human[1];
        Human[] result = new Human[0];
        for (int i = 0; this.array.length > i; i++) {
            if (checker.check(this.array[i], value)) {
                check[0] = this.array[i];
                result = concat(check, result);
            }
        }

        return result;
    }

}