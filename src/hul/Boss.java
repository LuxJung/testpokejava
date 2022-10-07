package hul;

import java.util.Timer;
import java.util.TimerTask;

public class Boss extends GameSet implements Runnable {
	// int x;
	// int y;
	// int speed; // ��ü ������ �ӵ� ���� ���� �� �ִ�.
	int count;
	// int boss_Hp;
	int speed;
	int enemy_Hp;
	Boss bs;
	Enemy en;
	GameFrame gf;

	public int getEnemy_Hp() {
		return enemy_Hp;
	}

	public void setEnemy_Hp(int enemy_Hp) {
		this.enemy_Hp = enemy_Hp;
	}
	/*
	 * public int getBoss_Hp() { return boss_Hp; }
	 * 
	 * public void setBoss_Hp(int boss_Hp) { this.boss_Hp = boss_Hp; }
	 */

	Boss(int x, int y, int speed, int enemy_Hp) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.enemy_Hp = 30;

		if (GameFrame.enemy_kill == 40) {
			this.enemy_Hp = 50;
		}
	}

	public void move() { // x��ǥ -���� �ӵ� ��ŭ �̵� ��Ű�� ��� �޼ҵ�
		x = 1000;
		x -= speed;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void appear_Boss() {
		if (GameFrame.enemy_kill == 20) {
			GameFrame.Enemy_List.clear();
			GameFrame.EMissile_List.clear();
			boss_timer.schedule(boss_task, 3000);
		}
		if (GameFrame.enemy_kill == 40) {
			GameFrame.Enemy_List.clear();
			GameFrame.EMissile_List.clear();
			boss_timer.schedule(boss_task, 3000);
		}
	}

	Timer boss_timer = new Timer();
	TimerTask boss_task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			process_Boss();
		}

	};

	public void process_Boss() {

		if (GameFrame.cnt % 30 == 0) {
			if (GameFrame.enemy_kill == 20 || GameFrame.enemy_kill == 40) {

				GameFrame.bs = new Boss(1200, 250, GameFrame.enemy_speed, getEnemy_Hp());
				GameFrame.bs.move();
				if (GameFrame.Boss_List.size() == 0) {

					GameFrame.Boss_List.add(GameFrame.bs);
				} else {
					enemy_Hp = 0;
				}
			} else {
				GameFrame.Boss_List.clear();
			}
			// ���� �ൿ
			for (int i = 0; i < GameFrame.Boss_List.size(); ++i) {
				GameFrame.bs = (Boss) (GameFrame.Boss_List.get(i));// �迭�� ���� �����Ǿ����� �� �ش�Ǵ� ���� �Ǻ�
				if (GameFrame.enemy_kill == 20) {
					Sound t1 = new Sound("������.mp3", false);
					t1.start();
				}
				if (GameFrame.enemy_kill == 40) {
					Sound t1 = new Sound("���ڸ�.mp3", false);
					t1.start();
				}
				if (GameFrame.cnt % 10 == 0) {// Ȯ�ε�������ġ���̻��ϻ���
					GameFrame.bms = new Missile(GameFrame.bs.x, GameFrame.bs.y + 75, 180, GameFrame.Emissile_Speed, 2);
					// ���ʺ��͹̻���x��ǥ, y��ǥ, �̻����������,
					// �̻��ϼӵ�, �̻�������
					// �̻�������0 : �÷��̾�߻��ϴ¹̻���,
					// 1 : ���̹߻��ϴ¹̻���
					GameFrame.BMissile_List.add(GameFrame.bms);
					// try {
					boss_atk.schedule(boss_atktask, 3000);
					boss_atk2.schedule(boss_atktask2, 6000);
					boss_atk3.schedule(boss_atktask3, 9000);
					if (GameFrame.Boss_List.size() == 0) {
						boss_atk.cancel();
						boss_atk2.cancel();
						boss_atk3.cancel();
					}
					// }catch(Exception e) {}// �����ȹ̻�������ü�ι迭���߰�
				}
				if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.bs.x, GameFrame.bs.y, GameFrame.Player_img[0],
						GameFrame.EnemyBoss_img[0])) {// �÷��̾�� ���� �浹�� �����Ͽ�
					// boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�.
					GameFrame.player_Hp--; // �÷��̾� ü���� 1����ϴ�.
					// game_Score += 10;
					// ���ŵ� ������ ���ӽ��ھ 10 ������ŵ�ϴ�.

					GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
							GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 0);
					// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y ����
					// ���� ������ ���� �� ( 0 �Ǵ� 1 )�� �޽��ϴ�.
					// ���� ���� �� - 0 : ���� , 1 : �ܼ� �ǰ�
					GameFrame.Explosion_List.add(GameFrame.ex);// ���ŵ� ����ġ�� ���� ����Ʈ�� �߰��մϴ�.
				}

			}
		}
	}

	Timer boss_atk = new Timer();
	TimerTask boss_atktask = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (GameFrame.BMissile_List != null) {

				GameFrame.BMissile_List
						.add(new Missile(GameFrame.bs.x, GameFrame.bs.y + 100, 180, GameFrame.Emissile_Speed, 2));

			}
			if (GameFrame.Boss_List.size() == 0) {
				GameFrame.BMissile_List.clear();
			}

		}

	};
	Timer boss_atk2 = new Timer();
	TimerTask boss_atktask2 = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (GameFrame.BMissile_List != null) {

				GameFrame.BMissile_List
						.add(new Missile(GameFrame.bs.x, GameFrame.bs.y + 120, 170, GameFrame.Emissile_Speed, 2));
			}
			if (GameFrame.Boss_List.size() == 0) {
				GameFrame.BMissile_List.clear();
			}
		}

	};
	Timer boss_atk3 = new Timer();
	TimerTask boss_atktask3 = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (GameFrame.BMissile_List != null) {

				GameFrame.BMissile_List
						.add(new Missile(GameFrame.bs.x, GameFrame.bs.y + 140, 190, GameFrame.Emissile_Speed, 2));
			}
			if (GameFrame.Boss_List.size() == 0) {
				GameFrame.BMissile_List.clear();
			}
		}

	};
}
