class Lecture02 {

    public static String name;
  
    public static void printDelim(String title) {
      System.out.println();
      System.out.println();
      System.out.println("^^^^^^^^^^^^^^^^^^^");
      System.out.println(title);
      System.out.println("___________________");
    }
  
    public static void printMult(int a, int b) {
      int result = a * b;
      System.out.println(a + " x " + b + " = " + result);
    }
  
    public static int calcCredit(int amount, int months, double rate) {
    //   System.out.println("Кредит: " + amount);
    //   System.out.println("Срок: " + months);
    //   System.out.println("Процент: " + rate);
      int monthlyPayment = amount / months; // переменная для ежемесячного платежа
    //   System.out.println("Платёж: " + monthlyPayment);
      return monthlyPayment;
    }
  
    public static void main(String[] args) {
    //   printMult(11, 54); // 11 x 54 = 594
      int p1 = calcCredit(1000000, 12, 9.9);
      int p2 = calcCredit(10000, 12, 29);
      System.out.println("Общая сумма: " + (p1 + p2));
    }
}