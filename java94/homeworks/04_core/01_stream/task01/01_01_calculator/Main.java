public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        try {
            int a = calc.plus.apply(1, 2);
            int b = calc.minus.apply(1,1);  // тут у нас получается 0
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("На ноль делить нельзя!");
        }

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(2,1);  // тут у нас получается 0
        int c = calc.devide.apply(a, b);
        calc.println.accept(c);        

        a = calc.plus.apply(1, 2);
        b = calc.minus.apply(1,1);
        // мы можем вернуть налл или какое то значение, которое договоримся
        // считать заранее ложным
        c = calc.devideSafe.apply(a, b) != null ? calc.devideSafe.apply(a, b) : 0;
        calc.println.accept(c);            
    }
    
}
