package hul;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Enemy extends GameSet implements Runnable {
	// int x;
	// int y;
	int speed; // ��ü ������ �ӵ� ���� ���� �� �ִ�.
	static Runnable Etrd; // ������
	GameFrame gf;
	int enemy_Hp;
	Enemy en;
	// �ټ��� ���� ���� ���Ѿ� �ϹǷ� �迭�� �̿�.
	// ���ʹ� Ŭ���� ����

	Enemy(int x, int y, int speed, int enemy_Hp) { // ����ǥ�� �޾� ��üȭ ��Ű�� ���� �޼ҵ�
		super(x, y);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.enemy_Hp = 3;
		if (GameFrame.enemy_kill > 21) {
			this.enemy_Hp = 6;
		}
	}

	public void move() { // x��ǥ -���� �ӵ� ��ŭ �̵� ��Ű�� ��� �޼ҵ�
		x -= speed;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Process_Enemy();
				Thread.sleep(17);// 20 milli sec �� ������ ������
				GameFrame.cnt++;
			}
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}

	}

	public void Process_Enemy() {
		if (GameFrame.cnt % 200 == 0) { // ���� ī��Ʈ 200ȸ ����

			if (GameFrame.enemy_kill != 20 && GameFrame.enemy_kill != 40) {
				if (GameFrame.enemy_kill > 40) {
					GameFrame.Enemy_List.clear();
				} else {
					if (GameFrame.enemy_kill < 20) {
						Sound t1 = new Sound("���ǽ��.mp3", false);
						t1.start();
					}
					if (GameFrame.enemy_kill > 20 && GameFrame.enemy_kill < 40) {
						Sound t1 = new Sound("�ս���.mp3", false);
						t1.start();
					}
					int num = (int) (Math.random() * 3) + 1;
					if (num == 1) {// �� ������ �ӵ��� �߰��� �޾� ���� �����Ѵ�. 3, 4 ,5����
						int zen = (int) ((Math.random() * 140) + 80);
						int zen1 = (int) ((Math.random() * 60) + 260);
						int zen2 = (int) ((Math.random() * 60) + 380);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen1,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), zen2,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
					} else if (num == 2) {
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 140,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 260,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 380,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 500,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
					} else {
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 80,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 200,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 320,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 440,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
						GameFrame.en = new Enemy(StartGame.SCREEN_WIDTH + (int) ((Math.random() * 400) + 50), 560,
								GameFrame.enemy_speed, getEnemy_Hp());
						GameFrame.Enemy_List.add(GameFrame.en);
					}
				}
			}
			if (GameFrame.enemy_kill == 20 && GameFrame.enemy_kill == 40) {
				GameFrame.Enemy_List.clear();
			}

		}

		// �Ϲ� �� �ൿ
		for (int i = 0; i < GameFrame.Enemy_List.size(); ++i) {
			GameFrame.en = (Enemy) (GameFrame.Enemy_List.get(i));// �迭�� ���� �����Ǿ����� �� �ش�Ǵ� ���� �Ǻ�
			GameFrame.en.move(); // �ش� ���� �̵���Ų��.
			if (GameFrame.en.x < -200) { // ���� ��ǥ�� ȭ�� ������ �Ѿ��
				GameFrame.Enemy_List.remove(i);
			}

			if (GameFrame.cnt % 150 == 0) {// Ȯ�ε�������ġ���̻��ϻ���
				GameFrame.ems = new Missile(GameFrame.en.x, GameFrame.en.y + 25, 180, GameFrame.Emissile_Speed, 1);
				// ���ʺ��͹̻���x��ǥ, y��ǥ, �̻����������,
				// �̻��ϼӵ�, �̻�������
				// �̻�������0 : �÷��̾�߻��ϴ¹̻���,
				// 1 : ���̹߻��ϴ¹̻���

				GameFrame.EMissile_List.add(GameFrame.ems);
				// �����ȹ̻�������ü�ι迭���߰�
			}
			if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.en.x, GameFrame.en.y, GameFrame.Player_img[0],
					GameFrame.Enemy_img[0])) {// �÷��̾�� ���� �浹�� �����Ͽ�
				// boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�.
				GameFrame.player_Hp--;// �÷��̾� ü���� 1����ϴ�.

				Sound t1 = new Sound("Shooting��ī���ǰ�.mp3", false);
				t1.start();
				/*
				 * ThirdReport t1 = new
				 * ThirdReport("C:\\Users\\newjm\\Downloads\\���ð���ȿ����\\Shooting��ī���ǰ�.mp3");
				 * t1.run();
				 */
				/*
				 * ThirdReport mp3 = new
				 * ThirdReport("C:\\Users\\newjm\\Downloads\\���ð���ȿ����\\Shooting��ī���ǰ�.mp3");
				 * mp3.Play();
				 */
				GameFrame.Enemy_List.remove(i); // ���� �����մϴ�.
				// game_Score += 10;
				// ���ŵ� ������ ���ӽ��ھ 10 ������ŵ�ϴ�.

				GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
						GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 0);
				// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y ����
				// ���� ������ ���� �� ( 0 �Ǵ� 1 )�� �޽��ϴ�.
				// ���� ���� �� - 0 : ���� , 1 : �ܼ� �ǰ�
				GameFrame.Explosion_List.add(GameFrame.ex);// ���ŵ� ����ġ�� ���� ����Ʈ�� �߰��մϴ�.
			}
		}

	}

	public int getEnemy_Hp() {
		return enemy_Hp;
	}

	public void setEnemy_Hp(int enemy_Hp) {
		this.enemy_Hp = enemy_Hp;
	}

}
