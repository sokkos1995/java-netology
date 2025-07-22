import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Petya");
        set.add("Olya");
        set.add("Petya");
        System.out.println(set);
        System.out.println(set.contains("Olya"));
    }
}
