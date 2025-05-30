public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 200) {
            return;
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void happyBirthday() {
        age++;
        System.out.println("С др!");
    }

    public boolean isTooYoung() {
        if (age < 18) {
            return true;
        } else {
            return false;
        }
    }
}
