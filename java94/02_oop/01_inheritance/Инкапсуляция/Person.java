public class Person {
    private String name;
    private int age;

    public void setAge(int age) {
        if (age < 0 || age > 200) {
            return;
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isTooYoung() {
        if (age < 18) {
            return true;
        } else {
            return false;
        }
    }
}
