import java.util.Comparator;

public class PersonsNameLengthComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.name.length(), o2.name.length());
    }
}
