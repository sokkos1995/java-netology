public class Main {
    public static final String TEXT = "aaababaabaaaabaabaabaabaaababaabaaababaabaaaabaabaabaabbabaabaaababaababaabaabaabaaabbaab";
    public static final String PATTERN = "aab";

    public static void main(String[] args) {
        int count = 0;
        String substring = "";

        for (int i = 0; i < TEXT.length(); i++) {
            // Вставьте ваш код сюда
            substring = TEXT.substring(i, TEXT.length());

            if (substring.startsWith(PATTERN)) {
                count++;
            }
        }

        System.out.println("Строка " + PATTERN + " встретилась в тексте " + count + " раз");
    }
}