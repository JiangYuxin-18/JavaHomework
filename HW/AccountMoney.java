import java.util.Date;
import java.util.Scanner;

class Account {
    private int id;
    private double balance=0;
    private double annualInterestRate=0;
    private Date dataCreated;
    public Account() {
        super();
        this.dataCreated=new Date();
    }
    public Account(int id, double balance) {
        super();
        this.id = id;
        this.balance = balance;
        this.dataCreated=new Date();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    public Date getDataCreated() {
        return dataCreated;
    }
    public double getMonthlyInterestRate(){
        if(this.annualInterestRate==0) {
            System.out.println("no interests");
            return 0;
        }else {
            return annualInterestRate/12;
        }
    }
    public void withDraw(double MoneyNum){
        if(MoneyNum>this.balance) {
            System.out.println("余额不足");
        }else {
            this.balance-=MoneyNum;
        }
    }
    public void deposit(double MoneyNum) {
        this.balance+=MoneyNum;
    }
}

public class AccountMoney {
         public static void mainMenu(){
            System.out.println("Main menu:");
            System.out.println("1:check balance");
            System.out.println("2:withdraw");
            System.out.println("3:deposit");
            System.out.println("4:exit");
            System.out.println("Enter a choice:");
        }
        public static void  main(String[] args){
//            Account myAccount=new Account(1122, 20000);
//            myAccount.setAnnualInterestRate(0.045);
//            myAccount.withDraw(2500);
//            myAccount.deposit(3000);
//
//            System.out.println("余额："+myAccount.getBalance());
//            System.out.println("月利率："+myAccount.getMonthlyInterestRate());
//            System.out.println("开户日期："+myAccount.getDataCreated());
            Account [] accounts=new Account[10];
            for(int i=0;i<10;i++){
                accounts[i]=new Account(100+i,100);
            }
            while(true) {
                System.out.println("请输入ID：");
                Scanner input = new Scanner(System.in);
                int id = input.nextInt();
                while (id < 100 || id > 109) {
                    System.out.println("输入的ID不正确，请输入正确的ID：");
                    id = input.nextInt();
                }
                mainMenu();
                int choice = input.nextInt();
                if(choice!=1&&choice!=2&&choice!=3&&choice!=4){
                    System.out.println("请重新输入choice：");
                    choice = input.nextInt();
                }
                while (choice == 1 || choice == 2 || choice == 3) {
                    switch (choice) {
                        case 1:
                            System.out.println("当前余额为：" + accounts[id-100].getBalance());
                            break;
                        case 2:
                            System.out.println("请输入您要取钱的数目：");
                            double withdraw = input.nextDouble();
                            accounts[id-100].withDraw(withdraw);
                            break;
                        case 3:
                            System.out.println("请输入您要存钱的数目：");
                            double deposit = input.nextDouble();
                            accounts[id-100].deposit(deposit);
                            break;
                    }
                    mainMenu();
                    choice = input.nextInt();
                    if(choice!=1&&choice!=2&&choice!=3&&choice!=4){
                        System.out.println("请重新输入choice：");
                        choice = input.nextInt();
                    }
                    if (choice == 4) {
                        break;
                    }
                }
            }
        }
    }