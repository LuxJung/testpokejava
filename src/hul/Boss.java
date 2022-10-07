package hul;

import java.util.Timer;
import java.util.TimerTask;

public class Boss extends GameSet implements Runnable {
	// int x;
	// int y;
	// int speed; // 객체 생성시 속도 값을 받을 수 있다.
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

	public void move() { // x좌표 -적의 속도 만큼 이동 시키는 명령 메소드
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
			// 보스 행동
			for (int i = 0; i < GameFrame.Boss_List.size(); ++i) {
				GameFrame.bs = (Boss) (GameFrame.Boss_List.get(i));// 배열에 적이 생성되어있을 때 해당되는 적을 판별
				if (GameFrame.enemy_kill == 20) {
					Sound t1 = new Sound("프리저.mp3", false);
					t1.start();
				}
				if (GameFrame.enemy_kill == 40) {
					Sound t1 = new Sound("리자몽.mp3", false);
					t1.start();
				}
				if (GameFrame.cnt % 10 == 0) {// 확인된적의위치에미사일생성
					GameFrame.bms = new Missile(GameFrame.bs.x, GameFrame.bs.y + 75, 180, GameFrame.Emissile_Speed, 2);
					// 왼쪽부터미사일x좌표, y좌표, 미사일진행방향,
					// 미사일속도, 미사일종류
					// 미사일종류0 : 플레이어가발사하는미사일,
					// 1 : 적이발사하는미사일
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
					// }catch(Exception e) {}// 생성된미사일을객체로배열에추가
				}
				if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.bs.x, GameFrame.bs.y, GameFrame.Player_img[0],
						GameFrame.EnemyBoss_img[0])) {// 플레이어와 적의 충돌을 판정하여
					// boolean값을 리턴 받아 true면 아래를 실행합니다.
					GameFrame.player_Hp--; // 플레이어 체력을 1깍습니다.
					// game_Score += 10;
					// 제거된 적으로 게임스코어를 10 증가시킵니다.

					GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
							GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 0);
					// 적이 위치해있는 곳의 중심 좌표 x,y 값과
					// 폭발 설정을 받은 값 ( 0 또는 1 )을 받습니다.
					// 폭발 설정 값 - 0 : 폭발 , 1 : 단순 피격
					GameFrame.Explosion_List.add(GameFrame.ex);// 제거된 적위치에 폭발 이펙트를 추가합니다.
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
