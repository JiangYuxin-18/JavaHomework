import java.util.Scanner;
public class HappyNum {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("������һ������");
        int[] num = new int[1000];//��Ϊ��Χ��1~2^32-1,����ѭ�������1���߿�ʼ�ظ��Ĵ���һ��С��1000
        int i = 0;
        int n = input.nextInt();

        while(n != 1){
            num[i] = n;//��������������
            int x = n;
            n = 0;     //��n���
            int y;
            //����ÿһλ����ƽ����
            while(x != 0){
                y = x % 10;
                x = x / 10;
                n += y * y;
            }
            i++;
            //�ж��Ƿ�ʼ�ظ�
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