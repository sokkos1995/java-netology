import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("Petya", 8);
        map.put("Tanya", 15);
        int age = map.get("Petya");
        System.out.println(age);
    }
}
