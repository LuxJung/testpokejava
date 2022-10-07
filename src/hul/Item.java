package hul;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Item  extends GameSet{
	int item_speed; // ��ü ������ �ӵ� ���� ���� �� �ִ�.
	int x;
	int y;
	Missile ms;
	GameFrame gf;
	
	Item(int x, int y, int speed) { // ����ǥ�� �޾� ��üȭ ��Ű�� ���� �޼ҵ�
		super(x, y);
		this.x = x;
		this.y = y;
		this.item_speed = speed;

	}

	public void moveitem() { // x��ǥ enemy_speed ��ŭ �̵� ��Ű�� ��� �޼ҵ�
		x -= item_speed;
	}

	public void firespeed() { // x��ǥ enemy_speed ��ŭ �̵� ��Ű�� ��� �޼ҵ�
		if (GameFrame.fire_Speed == 3) {
			GameFrame.fire_Speed = 3;
		} else {
			GameFrame.fire_Speed -= 1;
		}
	}
	
}
