package common;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	private Clip clip ;
	
	public MusicPlayer(String path){
		AudioInputStream audio;
		try {
			URL url = MusicPlayer.class.getResource(path);
			audio = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audio);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//type  -1ѭ������  0����һ��  ��������������ֵ +1 �Σ����紫��2 �򲥷�3�� 
	public void loop(int type) {
		clip.loop(type);
	}
	//����һ��
	public void play() {
		clip.start();
	}
	//ֹͣ
	public void stop() {
		clip.stop();
	}
}
