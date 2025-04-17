public class Main {
    public static void main(String[] args) {
        Singer singer = new Singer();
        singer.name = "Petya";
        singer.age = 8;
        singer.rating = 3;
        singer.sing("Good morning!");
        System.out.println(singer);

        Singer singer2 = new Singer();
        singer2.name = "Anya";
        singer2.age = 15;
        singer2.rating = 4;
        System.out.println(singer2);
    }
}
