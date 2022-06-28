package homework9;
interface Comparable{
    public abstract double getArea();
    public abstract double getPerimeter(); 
    public abstract boolean isRightTriangle();
    }
class Triangle implements Comparable
{
 private double a,b,c;
 public Triangle(double a1,double b1,double c1){
     a=a1;
     b=b1;
     c=c1;
 } 
    
    public double getArea() {
    	double s=1.0/2*(a+b+c);
    	double area;
    	area=Math.sqrt(s*(s-a)*(s-b)*(s-c));
    	return area;
    }

    
    public double getPerimeter() {
        return a+b+c;
    }
    
    public boolean isRightTriangle(){
    	if(a*a+b*b ==c*c) {
    		return true;
    	}
    	else return false;
    }
    
    public String toString() {
    	return "toString" +a+" "+b+" "+c;
    }
}