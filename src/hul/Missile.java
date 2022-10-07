package hul;



public class Missile extends GameSet {

	int x;
	int y;
	int angle;
	int speed; // �̻��� ���ǵ� ������ �߰�.
	int who;// �̻����̹߻��Ѱ��̴����������ϴº����߰�

	Missile(int x, int y, int angle, int speed, int who) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		// ��ü ������ �ӵ� ���� �߰��� �޽��ϴ�.
		this.who = who;// �߰��Ⱥ������޾ƿɴϴ�.

	}

	public void move() {
		x += Math.cos(Math.toRadians(angle)) * speed;

		// �ش�������ι̻��Ϲ߻�.

		y += Math.sin(Math.toRadians(angle)) * speed;

		// �ش�������ι̻��Ϲ߻�.
	}

	public  void Process_Missile() {
		// �̻��� ó�� �޼ҵ�
		if (GameFrame.KeySpace) { // �����̽��� Ű ���°� true �϶�
			GameFrame.player_Status = 1;// �̻��� �߻��ϸ� �÷��̾� ���� 1�κ���
			if ((GameFrame.cnt % GameFrame.fire_Speed) == 0) {// �÷��̾��� �̻��� ����ӵ��� �����Ѵ�.

				switch (GameFrame.missile_status) {
				case 0: // ������ ȹ�� ��
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					Sound t1 = new Sound("Shooting��ī������.mp3",false);
					t1.start();
					break;

				case 1: // ������ ȹ�� ��
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 100, 0, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					Sound t2 = new Sound("Shooting��ī������.mp3",false);
					t2.start();
					break;

				case 2: // ������ ȹ�� ��
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 0, 15, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 50, 0, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					GameFrame.ms = new Missile(GameFrame.x + 120, GameFrame.y + 100, 350, GameFrame.missile_Speed, 0);
					GameFrame.Missile_List.add(GameFrame.ms);
					Sound t3 = new Sound("Shooting��ī������.mp3",false);
					t3.start();
					break;

				}

			}
		}

		// �̻��ϰ� ���� �浹����
		for (int i = 0; i < GameFrame.Missile_List.size(); ++i) {
			GameFrame.ms = (Missile) GameFrame.Missile_List.get(i);// �߻�ü �߰�
			GameFrame.ms.move();// �߻�ü �̵�
			if (GameFrame.ms.x > StartGame.SCREEN_WIDTH - 20) {
				GameFrame.Missile_List.remove(i);// �̻��� ��ũ�� �Ѿ ��� ����
			}

			if (GameFrame.Missile_List.size() != 0) {

				// �̻��ϰ� �̺����� �浹����
				for (int j = 0; j < GameFrame.Enemy_List.size(); ++j) {
					GameFrame.en = (Enemy) GameFrame.Enemy_List.get(j);

					if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.en.x, GameFrame.en.y,
							GameFrame.Missile_img[0], GameFrame.Enemy_img[0]) && GameFrame.ms.who == 0) {
						// �̻����� ��ǥ �� �̹�������, ���� ��ǥ�� �̹��� ������ �޾�
						// �浹���� �޼ҵ�� �ѱ�� true,false�������� �޾� true�� �Ʒ��� �����մϴ�.
						GameFrame.Missile_List.remove(i);
						GameFrame.en.enemy_Hp--;
						if (GameFrame.en.enemy_Hp == 0) {
							GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null)-50 / 2,
									GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 0);
							// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y �� ���� ������ ���� �� ( 0 �Ǵ� 1 )�� �޽��ϴ�.
							// ���� ���� �� - 0 : ���� , 1 : �ܼ� �ǰ�
							GameFrame.Explosion_List.add(GameFrame.ex);
							GameFrame.Enemy_List.remove(j);
							GameFrame.enemy_kill += 1; // �� óġ +1
							GameFrame.score += 10;

							if ((int) (Math.round((Math.random() * 100))) > 80) {
								GameFrame.itm = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
										GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y ���� ������ �̵��ӵ�
								GameFrame.Item_List.add(GameFrame.itm);// ���ŵ� ����ġ�� ���� ����Ʈ�� �߰��մϴ�.
							} else if ((int) (Math.round((Math.random() * 100))) > 85) {
								GameFrame.itm2 = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
										GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								GameFrame.Item2_List.add(GameFrame.itm2);
							} else if ((int) (Math.round((Math.random() * 100))) > 90) {
								GameFrame.itm3 = new Item(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null) / 2,
										GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								GameFrame.Item3_List.add(GameFrame.itm3);
							}
						} else {
							GameFrame.ex = new Explosion(GameFrame.en.x + GameFrame.Enemy_img[0].getWidth(null)-50 / 2,
									GameFrame.en.y + GameFrame.Enemy_img[0].getHeight(null) / 2, 1);
							// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y �� ���� ������ ���� �� ( 0 �Ǵ� 1 )�� �޽��ϴ�.
							// ���� ���� �� - 0 : ���� , 1 : �ܼ� �ǰ�
							GameFrame.Explosion_List.add(GameFrame.ex);
						}
					}
					if (GameFrame.Enemy_List.isEmpty()) {
						GameFrame.EMissile_List.clear();
					}
				}

				// �÷��̾� �̻��� ���� ����
				for (int j = 0; j < GameFrame.Boss_List.size(); ++j) {
					GameFrame.bs = (Boss) GameFrame.Boss_List.get(j);

					if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bs.x, GameFrame.bs.y,
							GameFrame.Missile_img[0], GameFrame.EnemyBoss_img[0]) && GameFrame.ms.who == 0) {
						GameFrame.Missile_List.remove(i);
						GameFrame.bs.enemy_Hp--;
						if (GameFrame.bs.enemy_Hp == 0) {
							GameFrame.Boss_List.remove(j);
							GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null)-450 / 2,
									GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 0);
							GameFrame.Explosion_List.add(GameFrame.ex);
							GameFrame.enemy_kill += 1; // �� óġ +1
							GameFrame.score += 10;
							if ((int) (Math.round((Math.random() * 100))) > 75) {
								GameFrame.itm = new Item(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
										GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y ���� ������ �̵��ӵ�
								GameFrame.Item_List.add(GameFrame.itm);// ���ŵ� ����ġ�� ������ �߰�
							} else if ((int) (Math.round((Math.random() * 100))) > 85) {
								GameFrame.itm2 = new Item(
										GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
										GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								GameFrame.Item2_List.add(GameFrame.itm2);
							} else if ((int) (Math.round((Math.random() * 100))) > 80) {
								GameFrame.itm3 = new Item(
										GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null) / 2,
										GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2,
										GameFrame.item_speed);
								GameFrame.Item3_List.add(GameFrame.itm3);
							}
						} else {
							GameFrame.ex = new Explosion(GameFrame.bs.x + GameFrame.EnemyBoss_img[0].getWidth(null)-450 / 2,
									GameFrame.bs.y + GameFrame.EnemyBoss_img[0].getHeight(null) / 2, 1);
							// ���� ��ġ���ִ� ���� �߽� ��ǥ x,y �� ���� ������ ���� �� ( 0 �Ǵ� 1 )�� �޽��ϴ�.
							// ���� ���� �� - 0 : ���� , 1 : �ܼ� �ǰ�
							GameFrame.Explosion_List.add(GameFrame.ex);
						}
					}
					if (GameFrame.Boss_List.isEmpty()) {
						GameFrame.BMissile_List.clear();
						
					}
				}
			}

			// �� �� �� �� ��
			// �� ��
			for (int k = 0; k < GameFrame.EMissile_List.size(); ++k) {
				GameFrame.ems = (Missile) GameFrame.EMissile_List.get(k);// �߻�ü �߰�
				if (GameFrame.EMissile_List.size() != 0) {
					if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.ems.x, GameFrame.ems.y,
							GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0) {

						if ((GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.ems.x, GameFrame.ems.y,
								GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0)
								&& (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.en.x, GameFrame.en.y,
										GameFrame.Missile_img[0], GameFrame.Enemy_img[0]) && GameFrame.ms.who == 0)) {
							GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
									GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
							// �÷��̾��ڸ����浹����������Ʈ��ü����
							GameFrame.Explosion_List.add(GameFrame.ex);
							// �����Ѱ�ü���迭������
							if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
								try {
									GameFrame.score += 1;
									GameFrame.Missile_List.remove(i);
									GameFrame.EMissile_List.remove(k);
								} catch (IndexOutOfBoundsException e) {
									System.out.println(e);
								}
							}
						}
						GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
								GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
						// �÷��̾��ڸ����浹����������Ʈ��ü����
						GameFrame.Explosion_List.add(GameFrame.ex);
						// �����Ѱ�ü���迭������
						if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
							try {GameFrame.score += 1;
								GameFrame.Missile_List.remove(i);
								GameFrame.EMissile_List.remove(k);
							} catch (IndexOutOfBoundsException e) {
								System.out.println(e);
							}

						}

						// �ش�Ǵ����̻��ϻ���

						// �̻��ϰ� ���� ����������
					}
				}
			}

			// �� �� �� �� ��
			// �� ��
			for (int k = 0; k < GameFrame.BMissile_List.size(); ++k) {
				GameFrame.bms = (Missile) GameFrame.BMissile_List.get(k);// �߻�ü �߰�
				if (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bms.x, GameFrame.bms.y,
						GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0) {
					// ���̹߻��ѹ̻������÷��̾���浹�ϴ������θ�Ȯ��

					if ((GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bms.x, GameFrame.bms.y,
							GameFrame.Missile_img[0], GameFrame.EMissile_img[0]) && GameFrame.ms.who == 0)
							&& (GameFrame.Crash(GameFrame.ms.x, GameFrame.ms.y, GameFrame.bs.x, GameFrame.bs.y,
									GameFrame.Missile_img[0], GameFrame.EnemyBoss_img[0]) && GameFrame.ms.who == 0)) {
						GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
								GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
						// �÷��̾��ڸ����浹����������Ʈ��ü����
						GameFrame.Explosion_List.add(GameFrame.ex);
						// �����Ѱ�ü���迭������
						if (GameFrame.Missile_List.size() != 0 && GameFrame.EMissile_List.size() != 0) {
							try {GameFrame.score += 1;
								GameFrame.Missile_List.remove(i);
								GameFrame.EMissile_List.remove(k);
							} catch (IndexOutOfBoundsException e) {
								System.out.println(e);
							}

						}
					}

					GameFrame.ex = new Explosion(GameFrame.ms.x + GameFrame.Missile_img[0].getWidth(null)+20,
							GameFrame.ms.y + GameFrame.Missile_img[0].getHeight(null) / 2, 1);
					// �÷��̾��ڸ����浹����������Ʈ��ü����
					GameFrame.Explosion_List.add(GameFrame.ex);
					// �����Ѱ�ü���迭������
					if (GameFrame.Missile_List.size() != 0 && GameFrame.BMissile_List.size() != 0) {
						try {GameFrame.score += 1;
							GameFrame.Missile_List.remove(i);
							GameFrame.BMissile_List.remove(k);
						} catch (IndexOutOfBoundsException e) {
							System.out.println(e);
						}
					}
					// �ش�Ǵ����̻��ϻ���
				}
			}

		}

		// �� �� �� �� �� ��
		// �� �� �� ��
		for (int i = 0; i < GameFrame.EMissile_List.size(); ++i) {
			GameFrame.ems = (Missile) GameFrame.EMissile_List.get(i);// �߻�ü �߰�
			GameFrame.ems.move();// �߻�ü �̵�
			if (GameFrame.ems.x < -200) {
				GameFrame.EMissile_List.remove(i);// �߻�ü ����
			}
			if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.ems.x, GameFrame.ems.y, GameFrame.Player_img[0],
					GameFrame.EMissile_img[0]) && GameFrame.ems.who == 1) {
				// ���̹߻��ѹ̻������÷��̾���浹�ϴ������θ�Ȯ��
				GameFrame.player_Hp--;
				Sound t1 = new Sound("Shooting��ī���ǰ�.mp3",false);
				t1.start();
				// �÷��̾�ü������Ʈ��1�谨
				GameFrame.ex = new Explosion(GameFrame.x, GameFrame.y, 1);
				// �÷��̾��ڸ����浹����������Ʈ��ü����
				GameFrame.Explosion_List.add(GameFrame.ex);
				// �����Ѱ�ü���迭������
				GameFrame.EMissile_List.remove(i);
				// �ش�Ǵ����̻��ϻ���
			}
		}

		// �� �� �� �� �� ��
		// �� �� �� ��
		for (int i = 0; i < GameFrame.BMissile_List.size(); ++i) {
			GameFrame.bms = (Missile) GameFrame.BMissile_List.get(i);// �߻�ü �߰�
			GameFrame.bms.move();// �߻�ü �̵�
			if (GameFrame.bms.x < -200) {
				GameFrame.BMissile_List.remove(i);// �߻�ü ����
			}
			if (GameFrame.Crash(GameFrame.x, GameFrame.y, GameFrame.bms.x, GameFrame.bms.y, GameFrame.Player_img[0],
					GameFrame.EMissile_img[0]) && GameFrame.bms.who == 2) {
				// ���̹߻��ѹ̻������÷��̾���浹�ϴ������θ�Ȯ��
				GameFrame.player_Hp--;
				Sound t1 = new Sound("Shooting��ī���ǰ�.mp3",false);
				t1.start();
				// �÷��̾�ü������Ʈ��1�谨
				GameFrame.ex = new Explosion(GameFrame.x, GameFrame.y, 1);
				// �÷��̾��ڸ����浹����������Ʈ��ü����
				GameFrame.Explosion_List.add(GameFrame.ex);
				// �����Ѱ�ü���迭������
				GameFrame.BMissile_List.remove(i);
				// �ش�Ǵ����̻��ϻ���
			}
		}

	}

}