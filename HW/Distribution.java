
public class Distribution {

	public static void main(String[] args) {
		int firstPlaceNum;
        int secondPlaceNum;
        int thirdPlaceNum;
        int awardNum = 50;
        int personNum = 30;
        //一等奖3件，二等奖2件，三等奖1件
        for(firstPlaceNum = 1;firstPlaceNum * 3 < awardNum ;firstPlaceNum++){
            for(secondPlaceNum = 1; secondPlaceNum *2 < awardNum - firstPlaceNum * 3; secondPlaceNum++){
                thirdPlaceNum = awardNum - firstPlaceNum * 3 - secondPlaceNum *2;
                if(firstPlaceNum + secondPlaceNum + thirdPlaceNum == personNum){
                    System.out.println(firstPlaceNum+" "+secondPlaceNum+" "+thirdPlaceNum);
                }
            }
        }
	}
}