class ComparatorAge implements InterfaceComparator<Human> {

    @Override
    public int compare(Human a, Human b) {
        return a.getAge().compareTo(b.getAge());
    }

}