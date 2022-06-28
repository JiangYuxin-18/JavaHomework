package common;

import java.awt.Color;
import java.util.Random;

public class RandomColor {
	/** ��ȡָ�����ȵ�16�����ַ���. */
    public static String randomHexStr(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<len;i++) {
                //�������0-15����ֵ��ת����16����
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println("��ȡ16�����ַ����쳣������Ĭ��...");
            return "00CCCC";
        }
    }
 
    public static Color getRandomColor() {
        int color = Integer.valueOf(randomHexStr(6), 16);
        return new Color(color);
    }
}
