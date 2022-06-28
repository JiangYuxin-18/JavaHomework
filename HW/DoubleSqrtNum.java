public class DoubleSqrtNum {
    public static void main(String args[]){
        int i;
        int j;
        int k;
        for(i = 1; i < 10000; i++){
            for(j = 10; j < 100; j++){
                for(k = 13; k < 100; k++){
                    if(i + 100 == j * j && i + 368 == k * k){
                        System.out.println(i);
                    }
                }
            }
        }
    }
}