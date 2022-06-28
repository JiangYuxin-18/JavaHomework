public class stairs {
	public static void main(String[] args) {
		int a=0,b=0,c=0,d=0,e=0,f=0,num;      
        for(num=0;num<=400;num++){
            a=num%2;              
            b=num%3;              
            c=num%4;              
            d=num%5;
            e=num%6;
            f=num%7;
            if(a==1&&b==2&&c==3&&d==3&&e==5&&f==5){     //都符合条件时
                System.out.println("楼房台阶数为："+num);
            }
        }
	}
}
