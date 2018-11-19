public class Repository {

    private Human[] array;

    Repository() {
        array = new Human[0];
    }

    private Human[] concatArray(Human[] sourceArr, Human[] destArr) {
        int length = sourceArr.length + 1;
        Human[] res = new Human[length];
        System.arraycopy(sourceArr, 0, res, 0, sourceArr.length);
        System.arraycopy(destArr, 0, res, sourceArr.length, destArr.length);
        return res;
    }

    public void insert(Human human) {
        System.out.println("insert");
        Human[] oneHuman = {human};
        this.array = concatArray(array, oneHuman);
    }

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

    public int getLength() {
        return this.array.length;
    }

    public Human getHumanId(int id) {
        for (int i = 0; this.array.length > i; i++) {
            if(this.array[i].getId()==id){
                return array[i];
            }
        }
        return null;
    }

    public Human getHumanIndex(int index){
        return array[index];
    }

}
