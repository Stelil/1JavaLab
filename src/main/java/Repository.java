/**
 * Репозиторий людей
 * @author Воротников Дмитрий
 */
public class Repository {

    /** массив людей */
    private Human[] array;

    /**
     * конструктор, который создаёт массив
     */
    Repository() {
        array = new Human[0];
    }

    /**
     * Вставляет созданового только что человека в массив
     * @param human объект человека
     */
    public void insert(Human human) {
        System.out.println("insert");
        Human[] oneHuman = {human};

        int length = array.length + 1;
        Human[] res = new Human[length];
        System.arraycopy(array, 0, res, 0, array.length);
        System.arraycopy(oneHuman, 0, res, array.length, array.length);

        this.array = oneHuman;
    }

    /**
     * Удаляет чкеловека из массива
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
     * @return возвращает длину массива
     */
    public int getLength() {
        return this.array.length;
    }

    /**
     * Функция для получения объекта человека
     * @param id id под которым человек записан в массиве
     * @return возвращет объект человека
     */
    public Human getHumanId(int id) {
        for (int i = 0; this.array.length > i; i++) {
            if(this.array[i].getId()==id){
                return array[i];
            }
        }
        return null;
    }

    /**
     * Функция для получения объекта челвека
     * @param index указывает на место в массиве
     * @return возвращает объект человека
     */
    public Human getHumanIndex(int index){
        return array[index];
    }

}
