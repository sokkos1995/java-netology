public class NameInputMismatchException extends RuntimeException {

    public NameInputMismatchException(String text) {
        super("В параметре надо указать имя и фамилию, но было указано: " + text);
    }
}
