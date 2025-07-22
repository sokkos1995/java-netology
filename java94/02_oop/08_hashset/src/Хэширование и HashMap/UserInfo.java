import java.util.Objects;

public class UserInfo {
    protected String name;
    protected String surname;
    protected int age;

    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object obj) {
        UserInfo o = (UserInfo) obj;
        return name.equals(o.name) && surname.equals(o.surname);
    }
}
