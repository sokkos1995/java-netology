import java.util.Comparator;

class SpacePersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getExperience() != o2.getExperience()) {
            return -Integer.compare(o1.getExperience(), o2.getExperience());
        }
        int cnt1 = countSpaceLetters(o1.getName());
        int cnt2 = countSpaceLetters(o2.getName());
        if (cnt1 != cnt2) {
            return -Integer.compare(cnt1, cnt2);
        }
        return Integer.compare(o1.getName().length(), o2.getName().length());
    }

    private int countSpaceLetters(String name) {
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 's' || name.charAt(i) == 'S') {
                count++;
            }
        }
        return count;
    }
}