import taxes.TaxEarnings;
import taxes.TaxEarningsMinusSpendings;
import taxes.TaxSystem;
import deals.Deal;
import deals.Expenditure;
import deals.Sale;

public class Main {
    public static void main(String[] args) {

        Company firstCompany = new Company("Первая компания", new TaxEarningsMinusSpendings());
        Company secondCompany = new Company("Вторая компания", new TaxEarnings());  

        firstCompany.shiftMoney(20);
        firstCompany.shiftMoney(100);
        firstCompany.shiftMoney(-40);

        secondCompany.shiftMoney(20);
        secondCompany.shiftMoney(100);
        secondCompany.shiftMoney(-40);        

        firstCompany.payTaxes();
        secondCompany.payTaxes();

        System.out.println("Меняем систему налогообложения");

        TaxSystem anotherTaxSystem = new TaxEarnings();
        firstCompany.setTaxSystem(anotherTaxSystem);

        firstCompany.shiftMoney(20);
        firstCompany.shiftMoney(100);
        firstCompany.shiftMoney(-40);

        secondCompany.shiftMoney(20);
        secondCompany.shiftMoney(100);
        secondCompany.shiftMoney(-40);       
        
        firstCompany.payTaxes();
        secondCompany.payTaxes();

        System.out.println("Работаем с сделками");

        Deal[] deals = new Deal[3];
        deals[0] = new Sale(20, "телефон");
        deals[1] = new Sale(100, "компьютер");
        deals[2] = new Expenditure(40, "планшет");

        int delta = firstCompany.applyDeals(deals);
        System.out.println("Разницa доходов и расходов на момент старта уплаты налогов " + delta);

        System.out.println("Проверка на некорректный ввод");
        try {
            deals[0] = new Sale(-20, "телефон");
            delta = firstCompany.applyDeals(deals);     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        try {
            deals[0] = new Expenditure(0, "телефон");
            delta = firstCompany.applyDeals(deals);     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }           
    }
}