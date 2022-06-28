import java.util.Scanner;
public class one {

	public static void main(String[] args) {
		System.out.print("请输入利润：");
        Scanner M = new Scanner(System.in);
        
        double l = M.nextInt();
        double money = 0;//奖金
        
        if(l <= 10) {
            money = 0.1 * l;
        }
        else if(l > 10 && l <= 20){
            money = 10 * 0.1 + (l - 10) * 0.075;
        }
        else if(l > 20 && l <= 40){
            money = 10 * 0.1 + 10 * 0.075 + (l - 20) * 0.05;
        }
        else if(l > 40 && l <= 60){
            money = 10 * 0.1 + 10 * 0.075 + 20 * 0.05 + (l - 40) * 0.03;
        }
        else if(l > 60 && l <= 100){
            money = 10 * 0.1 + 10 * 0.075 + 20 * 0.05 + 20 * 0.03 + (l - 60) * 0.015;
        }
        else if(l > 100){
            money = 10 * 0.1 + 10 * 0.075 + 20 * 0.05 + 20 * 0.03 + 40 * 0.015 + (l - 100) * 0.01;
        }
        
        System.out.print("您的奖金为："+money+"万元");
	}
}
