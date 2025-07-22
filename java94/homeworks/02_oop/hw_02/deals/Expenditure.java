package deals;

public class Expenditure extends Deal {
    public Expenditure(int creditChange, String comment) {
        super(0, creditChange, "Покупка " + comment + " на " + creditChange + " руб.");
        if (creditChange <= 0) {
            throw new IllegalArgumentException("Сумма покупки не может быть меньше или равна нулю");
        }             
    }    
}