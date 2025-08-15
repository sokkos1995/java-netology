
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Todos implements Iterable<String> {
    private List<String> primary = new ArrayList<>();
    private List<String> secondary = new ArrayList<>();

    public Todos addPrimary(String task) {
        primary.add(task);
        return this;
    }

    public Todos addSecondary(String task) {
        secondary.add(task);
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        // возвращаем анонимный класс
        return new Iterator<>() {
            boolean isPrimary = true;  // основной ли список
            int next = 0;  // номер следующего элемента, который мы должны показывать

            @Override
            public boolean hasNext() {
                if (isPrimary) {
                    // если мы в основном списке и если в нем есть что нибудь - вернуть true
                    if (next < primary.size()) {
                        return true;
                    } else {
                        // если первая закончилась - спрашиваем, не пуста ли вторая
                        return !secondary.isEmpty();
                    }
                } else {
                    return next < secondary.size();
                }
            }

            @Override
            public String next() {
                // также рассматриваем все случаи
                if (isPrimary) {
                    if (next < primary.size()) {
                        String task = primary.get(next);
                        next++;
                        return task;
                    } else {
                        isPrimary = false;
                        next = 1;  // запомним, что в следующий раз мы покажем элемент
                        // по индексу 1, а сейчас вернем по индексу 0
                        return secondary.get(0);
                    }
                } else {
                    String task = secondary.get(next);
                    next++;
                    return task;
                }
            }
        };
    }
}
