public class Main {
    public static void main(String[] args) {
        Todos todos = new Todos()
            .addPrimary("Task 1")
            .addPrimary("Task 3")
            .addSecondary("Task 4")
            .addPrimary("Task 2");
    

        for (String task : todos) {
            // после реализации Iterable можжем использовать foreach
            System.out.println(task);
        }
    }
}
