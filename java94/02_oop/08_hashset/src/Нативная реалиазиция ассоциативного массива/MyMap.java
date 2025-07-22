import java.util.ArrayList;
import java.util.List;

public class MyMap<K, V> {
    protected List<MyMapPair<K, V>> pairs = new ArrayList<>();

    public void put(K key, V value) {
        for (MyMapPair<K, V> kv : pairs) {
            if (kv.getKey().equals(key)) {
                kv.setValue(value);
                return;
            }
        }
        MyMapPair<K, V> newKv = new MyMapPair<>(key, value);
        pairs.add(newKv);
    }

    public V get(K key) {
        for (MyMapPair<K, V> kv : pairs) {
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }
        return null;
    }
}
