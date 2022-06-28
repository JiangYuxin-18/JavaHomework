import java.util.Scanner;
public class HappyNum {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个数：");
        int[] num = new int[1000];//因为范围是1~2^32-1,所以循环到变成1或者开始重复的次数一定小于1000
        int i = 0;
        int n = input.nextInt();

        while(n != 1){
            num[i] = n;//将数存入数组中
            int x = n;
            n = 0;     //将n清空
            int y;
            //计算每一位数的平方和
            while(x != 0){
                y = x % 10;
                x = x / 10;
                n += y * y;
            }
            i++;
            //判断是否开始重复
            for(int j = 0; j < i; j++){
                if(num[j] == n){
                    System.out.println("false");
                    System.exit(1);
                }
            }
        }
        System.out.print("true");
    }
}