import java.util.Comparator;

class ComparatorName implements InterfaceComparator<Human>{

    @Override
    public int compare(Human a, Human b) {
        return a.getName().compareTo(b.getName());
    }

}