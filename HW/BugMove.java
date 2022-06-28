class Bug{
    //向右为1，向左为0
    private int director = 1;
    private int position = 0;
    public void turn(){
        if(director==1){
            director=0;
        }else if(director==0){
            director=1;
        }
    }
    public void move(){
        if(director==0){
            position--;
        }
        if(director==1){
            position++;
        }
    }
    public int getPosition(){
        System.out.println(position);
        return position;
    }
}
public class BugMove {
    public static void main(String[]  args) {
        Bug b = new Bug();
        b.getPosition();
        b.move();
        b.move();
        b.getPosition();
        b.turn();
        b.move();
        b.getPosition();
    }
}