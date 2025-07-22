import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Petya");
        list.add("Olya");
        list.add("Tanya");
        System.out.println(list);  // [Petya, Olya, Tanya]
        System.out.println(list.size());  // 3
        list.set(0, "Kolya");
        System.out.println(list);  // [Kolya, Olya, Tanya]
        System.out.println(list.get(1));  // Olya
        list.add(1, "Pasha");
        System.out.println(list);  // [Kolya, Pasha, Olya, Tanya]

        System.out.println(list.contains("Olya"));
    }
}
