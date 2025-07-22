class DoubleBox<T> {
    private RandomBox<T> first;
    private RandomBox<T> second;

    public boolean put(int key, T value) {
        if (first == null) {
            first = new RandomBox<>(key, value);
            return true;
        } else if (second == null) {
            second = new RandomBox<>(key, value);
            return true;
        } else {
            return false;
        }
    }

    public T get(int key) {
        if (first != null) {
            T value = first.tryUnlock(key);
            if (value != null) {
                return value;
            }
        }
        if (second != null) {
            T value = second.tryUnlock(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public int size() {
        return ((second == null) ? 0 : 1) + ((first == null) ? 0 : 1);
    }
}