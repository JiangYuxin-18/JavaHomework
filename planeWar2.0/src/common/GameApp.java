package common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameApp {
    public static BufferedImage getImg(String path){
        //��try���������쳣
        try {
            //io�����������ݵĹܵ�
            BufferedImage img = ImageIO.read(GameApp.class.getResource(path));
            return img;
        }
        //�쳣������ӡ�쳣
        catch (IOException e) {
            e.printStackTrace();
        }
        //û�ҵ��򷵻ؿ�
        return null;
    }

    public static ImageIcon getImg2(String path){
        
        //��try���������쳣
        try {
        	BufferedImage img = ImageIO.read(GameApp.class.getResource(path));
            return new ImageIcon(img);
        }
        //�쳣������ӡ�쳣
        catch (IOException e) {
            e.printStackTrace();
        }
        //û�ҵ��򷵻ؿ�
        return null;
    }
    
    //��ָ����ͼƬ���س�map����ʽ����(BufferedImage)
    public static HashMap getImageMapByIcon(String path,List nameList) {
    	HashMap target = new HashMap();
    	BufferedImage img= null;
    	String name="";
    	String key="";
    		for(int i=0;i<nameList.size();i++){
    			name = (String)nameList.get(i);
    			key = name.split("\\.")[0];
    			img = GameApp.getImg(path+nameList.get(i));
    			target.put(key, img);
    	}
    	return target;
	}
    
    //��ָ����ͼƬ���س�map����ʽ����(ImageIcon)
    public static HashMap getImageMapByIcon2(String path,List nameList) {
    	HashMap target = new HashMap();
    	ImageIcon img= null;
    	String name="";
    	String key="";
    		for(int i=0;i<nameList.size();i++){
    			name = (String)nameList.get(i);
    			key = name.split("\\.")[0];
    			img = GameApp.getImg2(path+nameList.get(i));
    			target.put(key, img);
    	}
    	return target;
	}
}
