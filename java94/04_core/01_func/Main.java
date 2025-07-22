import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Consumer<String> sayable = main::saySomething;
        sayable.accept("Hello, this is non-static method");
    }

    private void saySomething(String message) {
        System.out.println(message);
    }
}
