package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/*
 * С�л��࣬�̳�FlyingObject,ʵ��С�л����ƶ�����������
 */
public class Airplane extends FlyingObject implements Enemy {

	private int speed;
	
	Airplane(){
		super(48,50,1);
		speed = 2;
	}
	
	public void step() {
		y += speed;
	}

	/*
	 * ��ȡС�л���ͼƬ��״̬ΪLIFEʱ����С�л�ͼƬ
	 * ״̬ΪDEADʱ����4�ű���ͼƬ��ȫ�����غ�״̬��ΪREMOVE������null
	 */
	int index = 1;
	public BufferedImage getImage() {
		if(isLife()){
			return Images.airplanes[0];
		}else if(isDead()){
			if(index == 5){
				state = REMOVE;
				return null;
			}
			return Images.airplanes[index++];
		}
		return null;
	}
	
	//����һ���ӵ����ӵ������ӵ��ĳ�ʼ����Ϊ��ǰС�л������棬�ӵ���������
	public Bullet[] shoot(){
		Bullet[] res = new Bullet[1];
		res[0] = new Bullet(x+this.width/2,y+this.height+10,"down");
		return res;
	}
	
	//С�л���y������ڴ��ڵĸ߷���ture
	public boolean outBround() {
		return y>=World.HEIGHT;
	}

	//����С�л��ķ���
	public int getScore() {
		return 1;
	}

}