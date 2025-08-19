import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public boolean hasAge() { 
        if (getAge().isPresent()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean hasAddress() {
        if (getAddress() != null) {
            return true;
        } else {
            return false;
        }        
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public OptionalInt getAge() {
        return OptionalInt.of(age);
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { /*...*/ }
    public void happyBirthday() {
        if (getAge().isPresent()) {
            setAge(getAge().getAsInt() + 1);
        }
    }

    @Override
    public String toString() { /*...*/ }

    @Override
    public int hashCode() { /*...*/ }
}