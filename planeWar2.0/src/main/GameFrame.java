package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
/*
 * ��Ϸ������
 */
public class GameFrame extends JFrame {
	
	public GameFrame() {
		setTitle("�ɻ���ս");//���ñ���
		setSize(526, 685);//�趨�ߴ�
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����رհ�ť�ǹرճ���
        setLocationRelativeTo(null);   //���þ���
    	setResizable(false); //�������޸Ľ����С
	}
}
