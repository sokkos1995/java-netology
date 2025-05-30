public class Main {
    
    public static void main(String[] args) {
        Author author1 = new Author("John", "Tolkien", 10);
        Book book1 = new Book("The Lord of the Rings", 1954, author1, 1178);

        System.out.println("Book1 is big " + book1.isBig());
        System.out.println("Book1 contains Lord " + book1.matches("Lord"));
        System.out.println("Book1 contains Tolkien " + book1.matches("Tolkien"));
        System.out.println("Book1 contains Knight " + book1.matches("Knight"));
        System.out.println("Book1 estimate price " + book1.estimatePrice());

        // тестовая книга для проверки на маленькую книгу и маленькую цену
        Author author2 = new Author("test author");
        Book book2 = new Book("test book", 2025, author2, 3);
        
        System.out.println("Book2 is big " + book2.isBig());
        System.out.println("Book2 estimate price " + book2.estimatePrice());        
    }
}
