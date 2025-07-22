public class UserInfo {
    protected int age;

    public UserInfo(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "" + age;
    }
}
