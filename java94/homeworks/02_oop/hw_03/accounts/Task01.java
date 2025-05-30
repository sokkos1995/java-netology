package accounts;

public class Task01 {
    public static void main(String[] args) {
        Account account1 = new SimpleAccount(1000);

        System.out.println(account1.getBalance());
        if (account1.add(300)) {
            System.out.println(account1.getBalance());
        }
        if (account1.add(300)) {
            System.out.println(account1.getBalance());
        }        
        if (account1.pay(200)) {
            System.out.println(account1.getBalance());
        }   

        Account account2 = new CreditAccount(0, -10000);
    
        System.out.println(account2.getBalance());
        if (account2.pay(300)) {
            System.out.println(account2.getBalance());
        }
        if (account2.pay(300)) {
            System.out.println(account2.getBalance());
        }        
        if (account2.pay(2000)) {
            System.out.println(account2.getBalance());
        }        

        System.out.println("проверим трансфер");
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());        
        if (account1.transfer(account2, 1400)) {
            System.out.println(account1.getBalance());
            System.out.println(account2.getBalance());
        } 
    }
}
