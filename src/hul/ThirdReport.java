package hul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.Player;

public class ThirdReport extends Thread{
	private String filename;
	private Player player;

//Shooting��ī���ǰ�.mp3
	// ���� ��� �޼���
	public void Play() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {

			System.out.println(e);
		}

	}

	// ������
	public ThirdReport(String filename) {
		this.filename = filename;
	}

	public void run() {
		ThirdReport mp3 = new ThirdReport("C:\\Users\\newjm\\Downloads\\���ð���ȿ����\\Shooting��ī���ǰ�.mp3");
		mp3.Play();
	}
	/*public static void main(String[] args) {
		ThirdReport mp3 = new ThirdReport("C:\\Users\\newjm\\Downloads\\���ð���ȿ����\\Shooting��ī���ǰ�.mp3");
		mp3.Play();

	}*/



}