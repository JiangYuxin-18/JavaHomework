package homework9;
import java.util.Scanner;
public class Main {
public static void main(String[]args){
	Triangle r = new Triangle(8.0,15.0,17.0);
    System.out.println(r.getArea());
    System.out.println(r.getPerimeter());
    System.out.println(r.isRightTriangle());
    System.out.println(r.toString());
    
    Triangle b = new Triangle(2.0,3.0,4.0);
    System.out.println(b.getArea());
    System.out.println(b.getPerimeter());
    System.out.println(b.isRightTriangle());
    System.out.println(b.toString());
    
    Triangle c = new Triangle(12.0,8.0,13.0);
    System.out.println(c.getArea());
    System.out.println(c.getPerimeter());
    System.out.println(c.isRightTriangle());
    System.out.println(c.toString());
    
    Triangle a = new Triangle(3.0,4.0,5.0);
    System.out.println(a.getArea());
    System.out.println(a.getPerimeter());
    System.out.println(a.isRightTriangle());
    System.out.println(a.toString());
    }
}