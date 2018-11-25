public class ComparatorBrd implements InterfaceComparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getBrd().compareTo(b.getBrd());
    }

}