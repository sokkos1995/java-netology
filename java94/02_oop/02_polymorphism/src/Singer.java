public class Singer extends Person {
    public int rating;

    public Singer(String name, int age, int rating) {
        super(name, age);
        this.rating = rating;
    }

    public void sing(String verse) {
        System.out.println("Я пою: " + verse);
    }

    @Override
    public void happyBirthday() {
        age++;
        System.out.println("Я пою себе с др!");
    }

    @Override
    public String toString() {
        return "[" + rating + "] " + name + " (" + age + " лет)";
    }
}
