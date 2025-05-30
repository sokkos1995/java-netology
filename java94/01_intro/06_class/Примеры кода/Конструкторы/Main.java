import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Макс рейтинг: " + Singer.maxRating);

        Singer singer = new Singer("Petya", 8);
        System.out.println(singer);
        System.out.println("Макс рейтинг: " + Singer.maxRating);

        Singer singer2 = new Singer("Anya", 15, 4);
        System.out.println(singer2);
        System.out.println("Макс рейтинг: " + Singer.maxRating);

        Album album = new Album("Ой весна", 2021, singer2);
    }
}