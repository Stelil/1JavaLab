public class ComparatorSex implements InterfaceComparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getSex().compareTo(b.getSex());
    }

}