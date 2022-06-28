package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import common.GameApp;
import common.MusicPlayer;

/*
 * ������
 */
public class GamePanel extends JPanel implements ActionListener {
	GamePanel gamePanel=this;
	private JFrame mainFrame=null;
	JMenuBar jmb = null;
	BufferedImage bg=null;//����ͼƬ
	
	MusicPlayer musicBg=null;
	
	private int gameHeight=0;
	private int gameWidth=0;
	
	public List tempImages = new ArrayList();//��ʱͼƬ
	public BufferedImage  tempBufferedImage = null;//��ʱͼƬ����
	
	public HashMap imageMap = new HashMap();//ͼƬMap����
	private HashMap enemy1boomImageMap = new HashMap();//�л���ըͼƬMap����
	private HashMap enemy2boomImageMap = new HashMap();//�л���ըͼƬMap����
	private HashMap enemy3boomImageMap = new HashMap();//�л���ըͼƬMap����
	private HashMap enemy4boomImageMap = new HashMap();//�л���ըͼƬMap����
	public List enemyListMap = new ArrayList();
	
	public HashMap mypalneBoomImageMap= new HashMap();//�ҷ��ɻ���ըͼƬMap����
	
	public MyPlane myPlane=null;//�ҵķɻ�
	public List enemyList = new ArrayList();
	public List bulletList = new ArrayList();
	
	public static boolean startFlag=true;
	public static boolean nextEnd=false;
	public static boolean nextWin=false;
	
	public int totalCount=1000;
	public int curCount = 0;
	
	//���������ʼ����ز���
	public GamePanel(JFrame frame){
		this.setLayout(null);
		mainFrame = frame;
		//������ť
		initMenu();
		//��ʼ��ͼƬ
		initImage();
		//��ʼ���Լ��ķɻ�
		initMyPlane();
		//��ʼ���л�
		initEnemyPlane();
		//�������¼����
		createMouseListener();
		
		mainFrame.setVisible(true);
		 //���߳�����
		new Thread(new RefreshThread()).start();
		
		//������������
		musicBg=new MusicPlayer("/music/bg.wav");
		musicBg.loop(-1);
		
		ready();
	}
	//��ʱ�����л�
	private void initEnemyPlane() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (startFlag) {
					createEnemyPlane();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	//�����л�
	private void createEnemyPlane() {
		EnemyPlane enemyPlane = new  EnemyPlane(this);
		enemyList.add(enemyPlane);
	}
	//�����Լ��ɻ�
	private void initMyPlane() {
		myPlane = new MyPlane(300, 530, 132, 86, this);
	}

	//׼��
	private void ready() {
		tempBufferedImage = (BufferedImage)imageMap.get("ready");
		tempImages.add(tempBufferedImage);
		
		//1���ر�ͼƬ
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tempImages.remove(tempBufferedImage);
			}
		}).start();
	}
	
	
	
	//����¼��Ĵ���
	private void createMouseListener() {
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(myPlane==null) return ;
				
				if(myPlane.isCanMove()){
					myPlane.move(x,y);
					return;
				}
				//
				if(myPlane.isPoint(x,y)){
					myPlane.setCanMove(true);
				}
				
				
			}
		};
		addMouseMotionListener(mouseAdapter);
		addMouseListener(mouseAdapter);
	}
	

	//��Ϸ����
	public void gameOver() {
		nextEnd = true;//��ʾ��һ��ִ���̺߳�Ҫȫ��ֹͣ
		tempBufferedImage = (BufferedImage)imageMap.get("lost");
		tempImages.add(tempBufferedImage);
	}
	public void gameWin(){
		nextWin = true;//��ʾ��һ��ִ���̺߳�Ҫȫ��ֹͣ
		tempBufferedImage = (BufferedImage)imageMap.get("win");
		tempImages.add(tempBufferedImage);
	}
	//��Ϸ����
	public void realGameEnd(int type) {
		startFlag=false;//ֹͣ�߳�
		
		musicBg.stop();
		
		EnemyPlane enemyPlane=null;
		for (int i = 0; i < enemyList.size(); i++) {
			enemyPlane = (EnemyPlane)enemyList.get(i);
			if(enemyPlane!=null){
				enemyPlane.setAlive(false);
				enemyPlane=null;
			}
		}
		enemyList.clear();
		
		Bullet bullet=null;
		for (int i = 0; i < bulletList.size(); i++) {
			bullet = (Bullet)bulletList.get(i);
			if(bullet!=null){
				bullet.setAlive(false);
				bullet=null;
			}
		}
		
		
		//type=1 ����Ҫ��ʾ
	/*	if(type!=1){
			if(nextWin){
				musicWin=new MusicPlayer("/music/win.wav");
				musicWin.play();
			}else {
				musicLost=new MusicPlayer("/music/lost.wav");
				musicLost.play();
			}
		}*/
	}
	
	//���¿�ʼ��Ϸ
	private void restart() {
		realGameEnd(1);
		
		//��������
		startFlag=true;
		nextEnd=false;
		nextWin=false;
		
		curCount=0;
		
		if(myPlane!=null){
			myPlane.clear();
			myPlane=null;//�ҵķɻ�
		}
		EnemyPlane enemyPlane=null;
		for (int i = 0; i < enemyList.size(); i++) {
			enemyPlane = (EnemyPlane)enemyList.get(i);
			if(enemyPlane!=null){
				enemyPlane.clear();
			}
		}
		enemyList.clear();
		
		Bullet bullet=null;
		for (int i = 0; i < bulletList.size(); i++) {
			bullet = (Bullet)bulletList.get(i);
			if(bullet!=null){
				bullet.clear();
			}
		}
		bulletList.clear();
		
		tempImages.clear();//��ʱͼƬ���
		tempBufferedImage=null;//��ʱͼƬ�����
		
		//��ʼ���Լ��ķɻ�
		initMyPlane();
		//��ʼ���л�
		initEnemyPlane();
		
		new Thread(new RefreshThread()).start(); // �߳�����
		
		//������������
		musicBg=new MusicPlayer("/music/bg.wav");
		musicBg.loop(-1);
		
		ready();
	}
	
	//��ʼͼƬ
	private void initImage(){
		List commonList = new ArrayList();
		commonList.add("bg.jpg");
		commonList.add("myplane1.png");
		commonList.add("enemy1.png");
		commonList.add("enemy2.png");
		commonList.add("enemy3.png");
		commonList.add("enemy4.png");
		commonList.add("bullet.png");
		commonList.add("ready.png");
		commonList.add("win.png");
		commonList.add("lost.png");
		
		imageMap = GameApp.getImageMapByIcon("/images/",commonList);//������ͨͼƬ
		
		List enemy1List = new ArrayList();
		for (int i = 1; i <= 6; i++) {
			enemy1List.add("enemy1boom"+i+".png");
		}
		enemy1boomImageMap = GameApp.getImageMapByIcon("/images/enemy1boom/",enemy1List);//���طɻ���ը��ͼƬ
		enemyListMap.add(enemy1boomImageMap);
		
		List enemy2List = new ArrayList();
		for (int i = 1; i <= 6; i++) {
			enemy2List.add("enemy2boom"+i+".png");
		}
		enemy2boomImageMap = GameApp.getImageMapByIcon("/images/enemy2boom/",enemy2List);//���طɻ���ը��ͼƬ
		enemyListMap.add(enemy2boomImageMap);
		
		List enemy3List = new ArrayList();
		for (int i = 1; i <= 6; i++) {
			enemy3List.add("enemy3boom"+i+".png");
		}
		enemy3boomImageMap = GameApp.getImageMapByIcon("/images/enemy3boom/",enemy3List);//���طɻ���ը��ͼƬ
		enemyListMap.add(enemy3boomImageMap);
		
		List enemy4List = new ArrayList();
		for (int i = 1; i <= 6; i++) {
			enemy4List.add("enemy4boom"+i+".png");
		}
		enemy4boomImageMap = GameApp.getImageMapByIcon("/images/enemy4boom/",enemy4List);//���طɻ���ը��ͼƬ
		
		enemyListMap.add(enemy4boomImageMap);
		
		
		List mypalneBoomList = new ArrayList();
		for (int i = 1; i <= 6; i++) {
			mypalneBoomList.add("myplane1boom"+i+".png");
		}
		mypalneBoomImageMap = GameApp.getImageMapByIcon("/images/myplane1boom/",mypalneBoomList);//�����ҷ��ɻ���ը��ͼƬ
	}
	
	private void  initMenu(){
		// �����˵����˵�ѡ��
		jmb = new JMenuBar();
		JMenu jm1 = new JMenu("��Ϸ");
		jm1.setFont(new Font("΢���ź�", Font.BOLD, 15));// ���ò˵���ʾ������
		JMenu jm2 = new JMenu("����");
		jm2.setFont(new Font("΢���ź�", Font.BOLD, 15));// ���ò˵���ʾ������
		
		JMenuItem jmi1 = new JMenuItem("��ʼ����Ϸ");
		JMenuItem jmi2 = new JMenuItem("�˳�");
		jmi1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		jmi2.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		JMenuItem jmi3 = new JMenuItem("����˵��");
		jmi3.setFont(new Font("΢���ź�", Font.BOLD, 15));
		JMenuItem jmi4 = new JMenuItem("ʤ������");
		jmi4.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		jm2.add(jmi3);
		jm2.add(jmi4);
		
		jmb.add(jm1);
		jmb.add(jm2);
		mainFrame.setJMenuBar(jmb);// �˵�Bar�ŵ�JFrame��
		jmi1.addActionListener(this);
		jmi1.setActionCommand("Restart");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Exit");
		
		jmi3.addActionListener(this);
		jmi3.setActionCommand("help");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("win");
	}
	
	//ˢ���̣߳��������»���ҳ��
	private class RefreshThread implements Runnable {
		@Override
		public void run() {
			while (startFlag) {
				repaint();
				if(nextEnd||nextWin){
					realGameEnd(0);
					return ;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	@Override
	public void paint(Graphics g) {
		gameHeight = this.getHeight();
		gameWidth = this.getWidth();
		//���Ʊ���
		g.drawImage((BufferedImage)imageMap.get("bg"), 0, -150, null);
		
		//���Ʒɻ�
		if(myPlane!=null){
			myPlane.draw(g);
		}
		//���Ƶл�
		EnemyPlane enemyPlane=null;
		for (int i = 0; i < enemyList.size(); i++) {
			enemyPlane = (EnemyPlane)enemyList.get(i);
			enemyPlane.draw(g);
		}
		//�����ӵ�
		Bullet bullet=null;
		for (int i = 0; i < bulletList.size(); i++) {
			bullet = (Bullet)bulletList.get(i);
			bullet.draw(g);
		}
		
		//��ʱͼƬ
		BufferedImage tempImage = null;
		int x =0;
		int y =0;
		for (int i = 0; i < tempImages.size(); i++) {
			tempImage = (BufferedImage)tempImages.get(i);
			x = gameWidth/2-tempImage.getWidth()/2;
			y = gameHeight/2-tempImage.getHeight()/2;
			g.drawImage(tempImage,x,y, null);
		}
		
		//�÷�
		Color oColor = g.getColor();
		oColor = g.getColor();
		g.setColor(Color.white);
		g.setFont(new Font("΢���ź�", Font.BOLD, 16));
		g.drawString("�÷֣�"+curCount+"",400, 24);
		g.setColor(oColor);
	}
	
	//�����¼��Ĵ�������
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("����", Font.ITALIC, 18)));
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("����", Font.ITALIC, 18)));
		if ("Exit".equals(command)) {
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				System.exit(0);
			} 
		}else if("Restart".equals(command)){
			if(startFlag){
				Object[] options = { "ȷ��", "ȡ��" };
				int response = JOptionPane.showOptionDialog(this, "��Ϸ�У���ȷ��Ҫ���¿�ʼ��", "",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);
				if (response == 0) {
					//��Ҫ�Ƚ�����Ϸ
					realGameEnd(1);
					restart();
				} 
			}else{
				restart();
			}
		}else if("help".equals(command)){
			JOptionPane.showMessageDialog(null, "��Ϸ��ʼ��Ҫ�ȶ���굽�ɻ����������ƶ�Ч����Ȼ��ɻ��ͻ��������ƶ���",
					"��ʾ��", JOptionPane.INFORMATION_MESSAGE);
		}else if("win".equals(command)){
			JOptionPane.showMessageDialog(null, "�÷�1000�����ʤ����",
					"��ʾ��", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}

}
