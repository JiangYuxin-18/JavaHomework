package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import com.sun.org.apache.xml.internal.security.Init;
import common.MusicPlayer;
//�л�
public class EnemyPlane extends Plane {

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private BufferedImage image = null;
	private GamePanel panel=null;
	private HashMap imageMap=null;
	private boolean alive=true;
	private boolean canMove=false;
	private int speed=5;
	private int key=1;
	private int index = 0;
	private HashMap boomImageMap = null;
	private int count=10;
	
	public EnemyPlane(GamePanel panel) {
		this.panel=panel;
		this.imageMap=panel.imageMap;
		index = new Random().nextInt(4)+1;
		
		this.image=(BufferedImage)imageMap.get("enemy"+index);
		
		boomImageMap = (HashMap)panel.enemyListMap.get(index-1);
		
		init();
		
		move();
	}
	//�л���ʼ��
	private void init() {
		//ȡ���ɻ��Ŀ��
		height = this.image.getHeight();
		width = this.image.getWidth();
		//�ɻ��Ӵ��������
		y = - height;
		//���x 
		x = new Random().nextInt(520-width);
	}
	//����
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width,height, null);
	}
	
	//�������
	@Override
	public void clear() {
		alive=false;
		panel.enemyList.remove(this);
	}

	//�ƶ�
	@Override
	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(alive){
					y+=speed;
					hitMyPlane();
					
					if(y>panel.getHeight()){
						clear();
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	
	}
	//�л�����ӵ�
//	void shoot() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(alive && !panel.nextEnd  && !panel.nextWin){
//					//�����ӵ�
//					createBullet();
//					try {
//						Thread.sleep(200);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//			private void createBullet() {
//				Bullet bullet = new Bullet(x+width/2-10, y, 20, 30, panel);
//				panel.bulletList.add(bullet);
//				new MusicPlayer("/music/shoot.wav").play();
//			}
//		}).start();
//	}
	
	//�ɻ���ը
	@Override
	public void boom() {
		new MusicPlayer("/music/boom.wav").play();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(alive){
					changeImage();
					try {
						Thread.sleep(80);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	
	
	}
	
	//����ͼƬ
	void changeImage(){
		key++;
		if(key>boomImageMap.size()){
			clear();//����ɻ�
			return;
		}
		image=(BufferedImage)boomImageMap.get("enemy"+index+"boom"+key);
		if(image!=null){
			width = image.getWidth();
			height = image.getHeight();
		}
		
	}
	
	//�ж���ײ�һ�
	protected void hitMyPlane() {
		if(!alive) return ;
		
		MyPlane myPlane = panel.myPlane;
		if(myPlane==null){
			return ;
		}
		if(myPlane.isHitFlag()){//����ɻ��Ѿ���ײ�ˣ����ܼ����ٴ�
			return ;
		}
		if(isPoint(myPlane)){
			myPlane.setHitFlag(true);
			//��ǰ�л���ը
			boom();
			
			//�ҷɻ���ը
			myPlane.boom();
		}
	}
	
	
	//�жϷɻ����ӵ��Ƿ���ײ
	private boolean isPoint(MyPlane plane) {
		/*
		 * 
		 * �������
		 * 1.��Ҫ�жϵл���4�����Ƿ��ڷɻ���Χ�ڣ���������ʾ��ײ��
		 * 2.�������1���������򷴹������ж��һ���4�����Ƿ��ڵл��ķ�Χ�ڣ�����Ǳ�־��ײ��
		*/
		
		//��ʽ1
		
		//���Ͻ�
		int x1 = x;
		int y1 = y;
		//���Ͻ�
		int x2 = x+width;
		int y2 = y;
		//���½�
		int x3 = x+width;
		int y3 = y+height;
		//���½�
		int x4 = x;
		int y4 = y+height;
		//ֻҪ��һ�����ڷ�Χ�ڣ����ж�Ϊ��ײ
		if(comparePointMyPlane(x1,y1,plane)|| comparePointMyPlane(x2,y2,plane)||comparePointMyPlane(x3,y3,plane)||comparePointMyPlane(x4,y4,plane) ){
			return true;
		}
		
		//��ʽ1û�������÷�ʽ2�ж�
		
		//��ʽ2
		x1 = plane.getX();
		y1 = plane.getY();
		//���Ͻ�
		x2 = plane.getX()+plane.getWidth();
		y2 = plane.getY();
		//���½�
		x3 = plane.getX()+plane.getWidth();
		y3 =plane.getY()+plane.getHeight();
		//���½�
		x4 = plane.getX();
		y4 = plane.getY()+plane.getHeight();
		if(comparePoint(x1,y1)|| comparePoint(x2,y2)||comparePoint(x3,y3)||comparePoint(x4,y4) ){
			return true;
		}
		return false;
	}
	//�õл����������ж�
	private boolean comparePointMyPlane(int x,int y,MyPlane plane){
		//�������Ͻǣ�С�����½ǵ�������϶��ڷ�Χ��
		if(x>plane.getX() && y >plane.getY()
			&& x<plane.getX()+plane.getWidth() && y <plane.getY()+plane.getHeight()	){
			return  true;
		}
		return false;
	}
	//���һ����������ж�
	private boolean comparePoint(int x,int y){
		//�������Ͻǣ�С�����½ǵ�������϶��ڷ�Χ��
		if(x>this.x && y >this.y
			&& x<this.x+this.width && y <this.y+this.height){
			return  true;
		}
		return false;
	}

	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
