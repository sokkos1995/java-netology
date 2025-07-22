class RandomBox<T> {
    private int key;
    private T value;

    public RandomBox(int key, T value) {
        this.key = key;
        this.value = value;
    }

    public T tryUnlock(int key) {
        if (this.key == key) {
            return value;
        } else {
            return null;
        }
    }

    public int getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
