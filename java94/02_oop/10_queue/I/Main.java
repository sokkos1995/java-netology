import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();
        queue.offer("Petya");
        queue.offer("Olya");
        queue.offer("Tanya");

        queue.remove("Olya");

        while (!queue.isEmpty()) {
            String s = queue.poll();
            System.out.println(s);
        }
    }
}
