public class Lecture05 {
    public static void main(String[] args) {
        Day day = Day.SAT;
        System.out.println(isDayOff(day));
    }

    public static boolean isDayOff(Day day) {
        if (day == Day.SAT || day == Day.SUN) {
            return true;
        } else {
            return false;
        }
        // return day == Day.SAT || day == Day.SUN;  // более короткая запись
    }
}
