package main;

import java.util.List;


public class Main {
	//����
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		GamePanel panel = new GamePanel(frame);
		frame.add(panel);
		frame.setVisible(true);//�趨��ʾ
	}
}