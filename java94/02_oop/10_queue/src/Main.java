import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Deque<String> s = new ArrayDeque<>();
        s.push("Petya");
        s.push("Olya");
        s.push("Tanya");

        while (!s.isEmpty()) {
            String name = s.pop();
            System.out.println(name);
        }
    }
}
