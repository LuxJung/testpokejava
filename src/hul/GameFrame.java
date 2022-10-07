package hul;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

//�������� ����� ���� Ŭ���� �Դϴ�.
@SuppressWarnings("serial")
// 						              ������ ����                         Ű���� �̺�Ʈ ó��    ������ ����
public class GameFrame extends JFrame implements KeyListener, Runnable, ActionListener {
	// private boolean stop;
	Image BackGround_img; // �������� 1 �̹���
	Image BackGround2_img; // ��������2 �̹���
	Image GameClear_img; // ����Ŭ���� �̹���
	Image GameOver_img; // ���ӿ��� �̹���
	Image Bosstext_img; // �������� �ؽ�Ʈ �̹���
	Image buffImage; // ���� ���۸���(ȭ���� ��¦�Ÿ��� �ʵ���)
	Graphics buffg; // ���� ���۸���(ȭ���� ��¦�Ÿ��� �ʵ���)
	Toolkit tk = Toolkit.getDefaultToolkit();
	JInternalFrame option;
	JDesktopPane desktop;
	JButton confirm, cancel;

	Image[] iteminfo_img; // ������ �̹����� �޾Ƶ��� �̹��� ����
	Image[] iteminfo2_img; // ������ �̹����� �޾Ƶ��� �̹��� ����
	Image[] iteminfo3_img; // ������ �̹����� �޾Ƶ��� �̹��� ����
	static Image[] Player_img; // �÷��̾� �̹��� ����
	static Image[] Player_imgatk;
	static Image[] Enemy_img; // �� �̹����� �޾Ƶ��� �̹��� ����
	static Image[] EnemyBoss_img;
	static Image[] Logstone_img; // �� �̹����� �޾Ƶ��� �̹��� ����
	static Image[] Lizamong_img; // �� �̹����� �޾Ƶ��� �̹��� ����
	static Image[] Explo_img; // ��������Ʈ�� �̹����迭
	static Image[] Missile_img; // �̻��� �̹��� ����
	static Image[] EMissile_img; // �� �̻��� �̹��� ����
	static Image[] item_img; // ������ �̹����� �޾Ƶ��� �̹��� ����
	static Image[] item2_img; // ������ �̹����� �޾Ƶ��� �̹��� ����
	static Image[] item3_img; // ������ �̹����� �޾Ƶ��� �̹��� ����

	static int x, y; // ��ǥ ����
	// Ű���� �Է�
	Keyevent key = new Keyevent();
	public static boolean KeyEnter = false;
	public static boolean KeyUp = false;
	public static boolean KeyDown = false;
	public static boolean KeyLeft = false;
	public static boolean KeyRight = false;
	public static boolean KeySpace = false; // �̻��� �߻縦 ���� Ű���� �����̽�Ű �Է��� �߰��մϴ�.

	static int cnt; // ���� Ÿ�̹� ������ ���� ������ ī����

	// int boss_Hp; // ������ �����̴� �ӵ�

	static Missile ms; // �̻��� Ŭ���� ���� Ű
	static Missile ems;
	static Missile bms;
	static Enemy en; // �� ���� Ű
	static Boss bs; // ���� ���� Ű
	static Explosion ex; // ���� ����Ʈ�� Ŭ���� ���� Ű
	static Item itm;
	static Item itm2;
	static Item itm3;

	static int missile_status = 0;
	static int missile_Speed; // �̻��� �ӵ�
	static int Emissile_Speed; // ���̻��� ��

	static int fire_Speed; // �̻��� ���� �ӵ� ����

	static int player_Speed; // ������ �����̴� �ӵ�
	static int player_Hp; // ������ �����̴� �ӵ�
	static int player_Status = 0;// ���� ĳ���� ���� üũ ���� 0 : ����, 1: �̻��Ϲ߻�, 2: �浹

	static int item_speed; // ������ �ӵ� ����
	static int enemy_speed; // �� �ӵ� ����
	public static int enemy_kill;
	public static int score;

	int enemy_Hp; // ������ �����̴� �ӵ�

	int[] cx = { 0, 0, 0 }; // ��� ��ũ�� �ӵ� ����� ����
	int bx = 0; // ��ü ��� ��ũ�� �� ����

	static Thread trd; // ������
	static Thread trd2; // ������

	static ArrayList<Missile> BMissile_List = new ArrayList<Missile>();
	static ArrayList<Missile> EMissile_List = new ArrayList<Missile>();
	static ArrayList<Missile> Missile_List = new ArrayList<Missile>();

	static ArrayList<Explosion> Explosion_List = new ArrayList<Explosion>();

	static ArrayList<Boss> Boss_List = new ArrayList<Boss>();
	static ArrayList<Enemy> Enemy_List = new ArrayList<Enemy>();

	static ArrayList<Item> Item_List = new ArrayList<Item>();
	static ArrayList<Item> Item2_List = new ArrayList<Item>();
	static ArrayList<Item> Item3_List = new ArrayList<Item>();

	// Sound introSound2 = new Sound("Shooting�Ϲݸ����.mp3", true);
	// Sound GameNomalSound = new Sound("Shooting�Ϲݸ����.mp3", true);
	// Sound GameBossSound = new Sound("Shooting���������.mp3", true);

	/*
	 * ArrayList<BGM> BGM_List = new ArrayList<BGM>(); Sound sleectedSound; int
	 * nowSleected = 0;
	 */
	Sound t1 = new Sound("Shooting�Ϲݸ����.mp3", true);
	Sound introSound3 = new Sound("Shooting���������.mp3", true);
	SoundEffect bgm = new SoundEffect(introSound3);

	GameFrame() throws Exception {// ������ ����
		init();

		start();
		setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);

		setTitle("�����װ�");// ������ �̸�
		setSize(StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);// ������ ũ�⼳��

		// �������� �����쿡 ǥ�õɶ� ��ġ�� �����ϱ� ���� ���� ������� �ػ� ���� �޾ƿ�.
		Dimension screen = tk.getScreenSize();

		// �������� ����� ȭ�� ���߾ӿ� ��ġ��Ű�� ���� ��ǥ ���� ����մϴ�.
		int f_xpos = (int) (screen.getWidth() / 2 - StartGame.SCREEN_WIDTH / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - StartGame.SCREEN_HEIGHT / 2);

		setLocation(f_xpos, f_ypos);// �������� ȭ�鿡 ��ġ
		setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		setVisible(true); // �������� ���� ���̰� ���ϴ�.
		// this.stop = false;
	}

	public void init() {

		x = 100;// ĳ������ ���� ��ǥ
		y = 400;// ĳ������ ���� ��ǥ

		Player_img = new Image[4];// �÷��̾� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Player_img.length; ++i) {
			Player_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\��ī��resize_" + i + ".png")
							.getImage();
		}
		Player_imgatk = new Image[4];// �÷��̾� ���� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Player_imgatk.length; ++i) {
			Player_imgatk[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\new��ī��atk_" + i + ".png")
							.getImage();
		}
		Missile_img = new Image[4];// ���ⱸü �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Missile_img.length; ++i) {
			Missile_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\Missile_" + i + ".png")
							.getImage();
		}
		EMissile_img = new Image[4];// �� �̻��� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < EMissile_img.length; ++i) {
			EMissile_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\Missl_" + i + ".png")
							.getImage();
		}
		BackGround_img = new ImageIcon(
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\��׶���.png").getImage();
		BackGround2_img = new ImageIcon(
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\��׶���2.png").getImage();
		Bosstext_img = new ImageIcon(
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\bosstext.png").getImage();
		GameClear_img = new ImageIcon(
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\gameclear.png").getImage();
		GameOver_img = new ImageIcon(
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\gameover.png").getImage();

		Enemy_img = new Image[4];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Enemy_img.length; ++i) {
			Enemy_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\���_" + i + ".png")
							.getImage();
		}
		Logstone_img = new Image[4];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Logstone_img.length; ++i) {
			Logstone_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ս���_" + i + ".png")
							.getImage();
		}
		EnemyBoss_img = new Image[9];// ���� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < EnemyBoss_img.length; ++i) {
			EnemyBoss_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\������_" + i + ".png")
							.getImage();
		}
		Lizamong_img = new Image[5];// ���� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Lizamong_img.length; ++i) {
			Lizamong_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\���ڸ�_" + i + ".png")
							.getImage();
		}

		Explo_img = new Image[4];// ���� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < Explo_img.length; ++i) {
			Explo_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\explo_" + i + ".png")
							.getImage();
		}

		item_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < item_img.length; ++i) {
			item_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\item_" + i + ".png")
							.getImage();
		}
		item2_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < item2_img.length; ++i) {
			item2_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\2item_" + i + ".png")
							.getImage();
		}
		item3_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < item3_img.length; ++i) {
			item3_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\3item_" + i + ".png")
							.getImage();
		}

		iteminfo_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < iteminfo_img.length; ++i) {
			iteminfo_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\iteminfo_" + i + ".png")
							.getImage();
		}
		iteminfo2_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < iteminfo2_img.length; ++i) {
			iteminfo2_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\2iteminfo_" + i + ".png")
							.getImage();
		}
		iteminfo3_img = new Image[6];// �� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
		for (int i = 0; i < iteminfo3_img.length; ++i) {
			iteminfo3_img[i] = new ImageIcon(
					"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\3iteminfo_" + i + ".png")
							.getImage();
		}

		/*
		 * BGM_List.add(new BGM("Shooting�Ϲݸ����.mp3")); BGM_List.add(new
		 * BGM("Shooting���������.mp3"));
		 */

		player_Hp = 15;
		player_Speed = 10; // ���� ĳ���� �����̴� �ӵ� ����
		missile_Speed = 11; // �̻��� ������ �ӵ� ����
		Emissile_Speed = 11; // �̻��� ������ �ӵ� ����
		fire_Speed = 20; // �̻��� ���� �ӵ� ����
		enemy_speed = 7;// �� �ӵ� ����
		item_speed = 8;
		enemy_kill = 0;// 0���ιٲܰ�

	}

	public void start() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrameâ ����� ���α׷� ����
		addKeyListener(this); // Ű���� �̺�Ʈ

		trd = new Thread(this);
		trd.setPriority(6);
		trd.start();
		// changeSound(enemy_kill);

		//t1.start();
		// if(bgm.getState() == Thread.State.NEW) {
		//bgm.start();
		// }
		/*
		 * if (enemy_kill == 20) { Sound t2 = new Sound("Shooting���������.mp3", false);
		 * t2.start(); }
		 */
		// Runnable EnemyEtrd = new Enemy(x,y,enemy_speed);
		// Thread thread3 = new Thread(EnemyEtrd);
		// thread3.start();

	}

	@Override
	public void run() {// ������ ���ѷ���
		try {
			while (true) {
				synchronized (bgm) {
					Enemy en = new Enemy(0, 0, 0, 0);
					Boss bs = new Boss(0, 0, 0, 0);
					Missile ms = new Missile(0, 0, 0, 0, 0);
					key.KeyProcess();// Ű���� �Է�ó���� �Ͽ� x,y ����
					en.Process_Enemy(); // �� �ൿ �޼ҵ� ����
					// bs.process_Boss();
					bs.appear_Boss();
					ms.Process_Missile(); // �̻��� ó�� �޼ҵ� ����
					Process_Item();
					Process_Explosion();// ����ó�� �޼ҵ� ����
					// Process_Player();
					repaint(); // ���ŵ� x,y������ �̹��� ���� �׸���
					Thread.sleep(17);// 20 milli sec �� ������ ������

					cnt++;

					if (KeyEnter) {
						GameStatus gs = new GameStatus();
						trd.suspend();
					}
					if (enemy_kill > 20) {
						t1.close();
					}
					if (player_Hp < 0) {
						bgm.stop();
					}

				}
			}
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		buffImage = createImage(StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);// ������۸� ���� ũ�⸦ ȭ�� ũ��� ���� ����
		buffg = buffImage.getGraphics(); // ������ �׷��� ��ü�� ���

		screenDraw(g);
	}

	public void screenDraw(Graphics g) {

		Draw_Background();
		Draw_Bosstext();
		Draw_ItemInfo();
		Draw_Score2();
		Draw_Score();
		Draw_Player();// ������ �׷��� �׸��� �����´�
		Draw_Enemy(); // ���� �׸���
		Draw_Missile(); // �׷��� �̻��� ������ ����
		Draw_Item();
		Draw_Explosion(); // ��������Ʈ�׸��� �޼ҵ� ����
		Draw_GamePlayState();
		g.drawImage(buffImage, 0, 0, this);
		// ȭ�鿡 ���ۿ� �׸� �׸��� ������ �׸���

	}

	public void Draw_Player() { // ������ �׸����� �׸� �κ�
		switch (player_Status) {
		case 0: // ����
			if ((cnt / 4 % 4) == 1) {
				buffg.drawImage(Player_img[0], x, y, this);
			} else if ((cnt / 4 % 4) == 2) {
				buffg.drawImage(Player_img[1], x, y, this);
			} else if ((cnt / 4 % 4) == 3) {
				buffg.drawImage(Player_img[2], x, y, this);
			} else {
				buffg.drawImage(Player_img[3], x, y, this);
			}
			break;

		case 1: // ����߻�
			if ((cnt / 4 % 4) == 1) {
				buffg.drawImage(Player_imgatk[0], x, y, this);
			} else if ((cnt / 4 % 4) == 2) {
				buffg.drawImage(Player_imgatk[1], x, y, this);
			} else if ((cnt / 4 % 4) == 3) {
				buffg.drawImage(Player_imgatk[2], x, y, this);
			} else {
				buffg.drawImage(Player_imgatk[3], x, y, this);
			} // �̻����� ��µ��� �̹����� ������ �׷��ش�.
			player_Status = 0;// �̻��� ��Ⱑ ������ �÷��̾� ���¸� 0���� ������.
			break;

		case 2: // �浹
			break;
		}
	}

	/*********************************************************************/
	/*------------------- M I S S I L E -------------------*/
	public void Draw_Missile() { // �̻��� �׸��� �޼ҵ�
		for (int i = 0; i < Missile_List.size(); ++i) {// �̻��� ���� ������ Ȯ���Ѵ�.
			ms = (Missile) (Missile_List.get(i));// �̻��� ��ġ���� Ȯ��
			if (ms.who == 0)
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(Missile_img[0], ms.x, ms.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(Missile_img[1], ms.x, ms.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(Missile_img[2], ms.x, ms.y, this);
				} else {
					buffg.drawImage(Missile_img[3], ms.x, ms.y, this);
				}
			// ms.move();// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
			if (ms.x > StartGame.SCREEN_WIDTH) { // �̻����� ȭ�� ������ ������
				Missile_List.remove(i); // �̻��� �����
			}

			// ���� ��ǥ�� �̻��� �׸���.
			// �̹��� ũ�⸦ ������ �̻��� �߻� ��ǥ�� ������.

		}
		for (int k = 0; k < BMissile_List.size(); ++k) {// �̻��� ���� ������ Ȯ���Ѵ�.
			bms = (Missile) (BMissile_List.get(k));
			if (bms.who == 2)
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(EMissile_img[0], bms.x, bms.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(EMissile_img[1], bms.x, bms.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(EMissile_img[2], bms.x, bms.y, this);
				} else {
					buffg.drawImage(EMissile_img[3], bms.x, bms.y, this);
				}
			// bms.move();// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
			if (bms.x < -200) {
				BMissile_List.remove(k);// �߻�ü ����
			}
		}
		for (int j = 0; j < EMissile_List.size(); ++j) {// �̻��� ���� ������ Ȯ���Ѵ�.
			ems = (Missile) (EMissile_List.get(j));
			if (ems.who == 1)
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(EMissile_img[0], ems.x, ems.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(EMissile_img[1], ems.x, ems.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(EMissile_img[2], ems.x, ems.y, this);
				} else {
					buffg.drawImage(EMissile_img[3], ems.x, ems.y, this);
				}
			// ems.move();// �׷��� �̻����� ������ ���ڸ�ŭ �̵���Ű��
			if (ems.x < -200) {
				EMissile_List.remove(j);// �߻�ü ����
			}
		}

	}

	/*------------------- M I S S I L E -------------------*/
	/*********************************************************************/
	/*------------------- E N E M Y  -------------------*/
	public void Draw_Enemy() {
		// if(System.currentTimeMillis() == StartGame.end) {
		if (enemy_kill == 20) {

			for (int i = 0; i < Boss_List.size(); ++i) {
				bs = (Boss) (Boss_List.get(i));
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(EnemyBoss_img[0], bs.x, bs.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(EnemyBoss_img[1], bs.x, bs.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(EnemyBoss_img[2], bs.x, bs.y, this);
				} else {
					buffg.drawImage(EnemyBoss_img[3], bs.x, bs.y, this);
				}
			}
		} else if (enemy_kill < 20) {// �� �̹����� �׸��� �κ�Logstone_img

			for (int i = 0; i < Enemy_List.size(); ++i) {
				en = (Enemy) (Enemy_List.get(i));
				// buffg.drawImage(Enemy_img, en.x, en.y, this);
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(Enemy_img[0], en.x, en.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(Enemy_img[1], en.x, en.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(Enemy_img[2], en.x, en.y, this);
				} else {
					buffg.drawImage(Enemy_img[3], en.x, en.y, this);
				} // �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
			}
		} else if (enemy_kill > 20 && enemy_kill < 40) {// �� �̹����� �׸��� �κ�Logstone_img
			for (int i = 0; i < Enemy_List.size(); ++i) {
				en = (Enemy) (Enemy_List.get(i));
				// buffg.drawImage(Enemy_img, en.x, en.y, this);
				if ((cnt / 4 % 4) == 1) {
					buffg.drawImage(Logstone_img[0], en.x, en.y, this);
				} else if ((cnt / 4 % 4) == 2) {
					buffg.drawImage(Logstone_img[1], en.x, en.y, this);
				} else if ((cnt / 4 % 4) == 3) {
					buffg.drawImage(Logstone_img[2], en.x, en.y, this);
				} else {
					buffg.drawImage(Logstone_img[3], en.x, en.y, this);
				} // �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
			}
		} else if (enemy_kill == 40) {
			for (int i = 0; i < Boss_List.size(); ++i) {
				bs = (Boss) (Boss_List.get(i));
				if ((cnt / 5 % 5) == 1) {
					buffg.drawImage(Lizamong_img[0], bs.x, bs.y, this);
				} else if ((cnt / 5 % 5) == 2) {
					buffg.drawImage(Lizamong_img[1], bs.x, bs.y, this);
				} else if ((cnt / 5 % 5) == 3) {
					buffg.drawImage(Lizamong_img[2], bs.x, bs.y, this);
				} else if ((cnt / 5 % 5) == 4) {
					buffg.drawImage(Lizamong_img[3], bs.x, bs.y, this);
				} else {
					buffg.drawImage(Lizamong_img[4], bs.x, bs.y, this);
				}
			}
		}

	}

	/*------------------- E N E M Y  -------------------*/
	/*********************************************************************/
	/*------------------- I T E M -------------------*/
	public void Draw_Item() {// ������ �̹����� �׸��� �κ�

		for (int i = 0; i < Item_List.size(); ++i) {
			itm = (Item) Item_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(item_img[0], itm.x, itm.y, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(item_img[1], itm.x, itm.y, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(item_img[2], itm.x, itm.y, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(item_img[3], itm.x, itm.y, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(item_img[4], itm.x, itm.y, this);
			} else {
				buffg.drawImage(item_img[5], itm.x, itm.y, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}

		for (int i = 0; i < Item2_List.size(); ++i) {
			itm2 = (Item) Item2_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(item2_img[0], itm2.x, itm2.y, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(item2_img[1], itm2.x, itm2.y, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(item2_img[2], itm2.x, itm2.y, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(item2_img[3], itm2.x, itm2.y, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(item2_img[4], itm2.x, itm2.y, this);
			} else {
				buffg.drawImage(item2_img[5], itm2.x, itm2.y, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}

		for (int i = 0; i < Item3_List.size(); ++i) {
			itm3 = (Item) Item3_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(item3_img[0], itm3.x, itm3.y, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(item3_img[1], itm3.x, itm3.y, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(item3_img[2], itm3.x, itm3.y, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(item3_img[3], itm3.x, itm3.y, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(item3_img[4], itm3.x, itm3.y, this);
			} else {
				buffg.drawImage(item3_img[5], itm3.x, itm3.y, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}
	}

	public void Process_Item() {
		for (int i = 0; i < Item_List.size(); ++i) {
			itm = (Item) Item_List.get(i);// �迭�� �������� �����Ǿ����� �� �ش�Ǵ� �������� �Ǻ�
			itm.moveitem(); // �������� �̵���Ų��.
			if (x < -200) { // ���� ��ǥ�� ȭ�� ������ �Ѿ��
				Item_List.remove(i); // �ش� �������� �迭���� ����
			}

			if (Crash(x, y, itm.x, itm.y, Player_img[0], item_img[0])) {// �÷��̾�� �������� �浹�� �����Ͽ�
				// boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�.
				// player_Hitpoint --; //�÷��̾� ü���� 1����ϴ�.
				Sound t1 = new Sound("Shooting��ī�������.mp3", false);
				t1.start();
				GameFrame.score += 5;
				Item_List.remove(i); // �������� �����մϴ�.
				itm.firespeed();
				// game_Score += 10;
				// ���ŵ� ������ ���ӽ��ھ 10 ������ŵ�ϴ�.

			}

		}
		for (int i = 0; i < Item2_List.size(); ++i) {
			itm2 = (Item) Item2_List.get(i);// �迭�� �������� �����Ǿ����� �� �ش�Ǵ� �������� �Ǻ�
			itm2.moveitem(); // �������� �̵���Ų��.
			if (x < -200) { // ���� ��ǥ�� ȭ�� ������ �Ѿ��
				Item2_List.remove(i); // �ش� �������� �迭���� ����
			}
			if (Crash(x, y, itm2.x, itm2.y, Player_img[0], item2_img[0])) {// �÷��̾�� �������� �浹�� �����Ͽ�
				// boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�.
				// player_Hitpoint --; //�÷��̾� ü���� 1����ϴ�.
				Sound t1 = new Sound("Shooting��ī�������.mp3", false);
				t1.start();
				GameFrame.score += 10;
				Item2_List.remove(i); // �������� �����մϴ�.
				if (missile_status == 0) {
					missile_status = 1;
				} else if (missile_status == 1) {
					missile_status = 1;
				}
			}
		}
		for (int i = 0; i < Item3_List.size(); ++i) {
			itm3 = (Item) Item3_List.get(i);// �迭�� �������� �����Ǿ����� �� �ش�Ǵ� �������� �Ǻ�
			itm3.moveitem(); // �������� �̵���Ų��.
			if (x < -200) { // ���� ��ǥ�� ȭ�� ������ �Ѿ��
				Item3_List.remove(i); // �ش� �������� �迭���� ����
			}
			if (Crash(x, y, itm3.x, itm3.y, Player_img[0], item3_img[0])) {// �÷��̾�� �������� �浹�� �����Ͽ�
				// boolean���� ���� �޾� true�� �Ʒ��� �����մϴ�.
				// player_Hitpoint --; //�÷��̾� ü���� 1����ϴ�.
				Sound t1 = new Sound("Shooting��ī�������.mp3", false);
				t1.start();
				GameFrame.score += 15;
				Item3_List.remove(i); // �������� �����մϴ�.

				if (missile_status == 0) {
					missile_status = 1;
				} else if (missile_status == 1) {
					missile_status = 2;
				} else if (missile_status == 2) {
					missile_status = 2;
				}

			}
		}

	}

	/*------------------- I T E M -------------------*/
	/*********************************************************************/

	public void Draw_Bosstext() {

		if (enemy_kill == 20 || enemy_kill == 40) {
			if (Boss_List.size() == 0) {
				buffg.drawImage(Bosstext_img, 50, 50, (ImageObserver) this);
			}
		}
	}

	/*------------------- B A C K G R O U N D -------------------*/
	public void Draw_GamePlayState() {
		if (enemy_kill == 41) {
			buffg.drawImage(GameClear_img, 165, 210, (ImageObserver) this);

			/* sleectedSound.close(); */
			pikaclear.schedule(pikaclear_task, 2000);
			trd.stop();

			bgm.threadStop(true);
			if (bgm.getState() == Thread.State.TERMINATED) {
				bgm.t2.stop();
				introSound3.close();
				introSound3.isLoop = false;
				introSound3.threadStopp(true);
				bgm.stop();
			}
		} else if (player_Hp < 1) {
			buffg.drawImage(GameOver_img, 165, 210, (ImageObserver) this);
			pikaover.schedule(pikaove_task, 2000);
			trd.stop();
			t1.close();
			bgm.threadStop(true);
			if (bgm.getState() == Thread.State.TERMINATED) {
				bgm.t2.stop();
				introSound3.close();
				introSound3.isLoop = false;
				introSound3.threadStopp(true);
				bgm.stop();
			}
			System.out.println("trd = " + trd.getState());
			System.out.println("bgm = " + bgm.getState());
			System.out.println("introSound3 = " + introSound3.getState());
			/* sleectedSound.close(); */

		}
	}

	public void Draw_Background() {
		if (enemy_kill < 21) {
			buffg.clearRect(0, 0, StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);
			// ȭ�� ����� ����� ���� ���⼭ �����մϴ�.
			if (bx > -4660) {
				// �⺻ ���� 0�� bx�� -3500 ���� ũ�� ����
				buffg.drawImage(BackGround_img, bx, 0, this);
				bx -= 4;
				// bx�� 0���� -1��ŭ ��� ���̹Ƿ� ����̹����� x��ǥ��
				// ��� �������� �̵��Ѵ�. �׷��Ƿ� ��ü ����� õõ��
				// �������� �����̰� �ȴ�.
			} else {
				bx = 0;
			}
		}
		if (enemy_kill >= 21) {
			buffg.clearRect(0, 0, StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);
			if (bx > -4660) {
				// �⺻ ���� 0�� bx�� -3500 ���� ũ�� ����
				buffg.drawImage(BackGround2_img, bx, 0, this);
				bx -= 4;
				// bx�� 0���� -1��ŭ ��� ���̹Ƿ� ����̹����� x��ǥ��
				// ��� �������� �̵��Ѵ�. �׷��Ƿ� ��ü ����� õõ��
				// �������� �����̰� �ȴ�.
			} else {
				bx = 0;
			}
		}
	}

	/*------------------- B A C K G R O U N D -------------------*/
	/*********************************************************************/
	/*------------------- E X P L O S i O N -------------------*/
	public void Draw_Explosion() {
		// ���� ����Ʈ�� �׸��� �κ� �Դϴ�.

		for (int i = 0; i < Explosion_List.size(); ++i) {
			ex = (Explosion) Explosion_List.get(i);
			// ���� ����Ʈ�� ���� ������ üũ�Ͽ� ����Ʈ�� ����.

			if (ex.damage == 0) {
				// �������� 0 �̸� ���߿� �̹��� �׸���
				if (ex.ex_cnt < 7) {
					buffg.drawImage(Explo_img[0], ex.x - Explo_img[0].getWidth(null) / 2,
							ex.y - Explo_img[0].getHeight(null) / 2, this);
				} else if (ex.ex_cnt < 14) {
					buffg.drawImage(Explo_img[1], ex.x - Explo_img[1].getWidth(null) / 2,
							ex.y - Explo_img[1].getHeight(null) / 2, this);
				} else if (ex.ex_cnt < 21) {
					buffg.drawImage(Explo_img[2], ex.x - Explo_img[2].getWidth(null) / 2,
							ex.y - Explo_img[2].getHeight(null) / 2, this);
				} else if (ex.ex_cnt < 28) {
					buffg.drawImage(Explo_img[3], ex.x - Explo_img[3].getWidth(null) / 2,
							ex.y - Explo_img[3].getHeight(null) / 2, this);
				} else if (ex.ex_cnt > 28) {
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
					// ������ ���� ī���͸� ����Ͽ�
					// �̹����� ���������� �׸�.
				}
			} else { // �������� 1�̸� �ܼ� �ǰݿ� �̹��� �׸���
				if (ex.ex_cnt < 7) {
					buffg.drawImage(Explo_img[0], ex.x + 100, ex.y + 38, this);
				} else if (ex.ex_cnt < 14) {
					buffg.drawImage(Explo_img[1], ex.x + 60, ex.y + 25, this);
				} else if (ex.ex_cnt < 21) {
					buffg.drawImage(Explo_img[2], ex.x + 5, ex.y + 5, this);
				} else if (ex.ex_cnt < 28) {
					buffg.drawImage(Explo_img[3], ex.x + 25, ex.y + 50, this);
				} else if (ex.ex_cnt < 35) {
					buffg.drawImage(Explo_img[1], ex.x - 15, ex.y + 13, this);
				}

				else if (ex.ex_cnt > 35) {
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
					// �ܼ� �ǰ� ���� ���������� �̹����� �׸�����
					// ������ ���� �ణ �ٸ� ������� �׸��ϴ�.
				}
			}
		}
	}

	public void Process_Explosion() {
		// ���� ����Ʈ ó���� �޼ҵ�
		for (int i = 0; i < Explosion_List.size(); ++i) {
			ex = (Explosion) Explosion_List.get(i);
			ex.effect();
			// ����Ʈ �ִϸ��̼��� ��Ÿ��������
			// ����Ʈ ó�� �߰��� �߻��ϸ� �ش� �޼ҵ带 ȣ��.
		}
	}

	public static boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2) {
		// ���� �浹 ���� �ҽ��� �����մϴ�.
		// ���� �̹��� ������ �ٷ� �޾� �ش� �̹����� ����, ���̰���
		// �ٷ� ����մϴ�.

		boolean check = false;

		if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2
				+ img1.getWidth(null) / 2)

				&& Math.abs((y1 + img1.getHeight(null) / 2)
						- (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
			// �̹��� ����, ���̰��� �ٷ� �޾� ����մϴ�.
			check = true;// �� ���� true�� check�� true�� �����մϴ�.
		} else {
			check = false;
		}

		return check; // check�� ���� �޼ҵ忡 ���� ��ŵ�ϴ�.
	}

	public boolean Msupgrade() {
		// ���� �浹 ���� �ҽ��� �����մϴ�.
		// ���� �̹��� ������ �ٷ� �޾� �ش� �̹����� ����, ���̰���
		// �ٷ� ����մϴ�.

		boolean check = false;

		if (Crash(x, y, itm2.x, itm2.y, Player_img[0], item2_img[0])) {
			// �̹��� ����, ���̰��� �ٷ� �޾� ����մϴ�.
			check = true;// �� ���� true�� check�� true�� �����մϴ�.
		} else {
			check = false;
		}

		return check; // check�� ���� �޼ҵ忡 ���� ��ŵ�ϴ�.
	}

	/*------------------- E X P L O S i O N -------------------*/
	/*********************************************************************/
	/*------------------- S C O R E -------------------*/
	public void Draw_Score() { // ���� üũ�� �ؽ�Ʈ�� �׸��ϴ�.

		buffg.setFont(new Font("Defualt", Font.BOLD, 20));
		// ��Ʈ ������ �մϴ�. �⺻��Ʈ, ����, ������ 20
		// buffg.drawString("Score : " + score, 600, 70);

		// buffg.drawString("SCORE : " + enemy_kill, 1000, 70);
		// ��ǥ x : 1000, y : 70�� ���ھ ǥ���մϴ�.

		// buffg.drawString("Boss Count : " + Boss_List.size(), 1000, 110);
		// ��ǥ x : 1000, y : 90�� �÷��̾� ü���� ǥ���մϴ�.

		// buffg.drawString("Missile Count : " + Missile_List.size(), 1000, 90);
		// ��ǥ x : 1000, y : 110�� ��Ÿ�� �̻��� ���� ǥ���մϴ�.

		// buffg.drawString("Enemy Count : " + Enemy_List.size(), 1000, 130);
		// ��ǥ x : 1000, y : 130�� ��Ÿ�� ���� ���� ǥ���մϴ�.

		// buffg.drawString("EMissile Count : " + EMissile_List.size(), 1000, 150);
		// ��ǥ x : 1000, y : 130�� ��Ÿ�� ���� ���� ǥ���մϴ�.

		buffg.drawString("��ī�� HP : " + player_Hp, 50, 70);
		if (enemy_kill < 20) {
			if (Enemy_List.size() == 0) {
				buffg.drawString("���ǽ�� HP : " + 3, 1100, 70);
			} else {
				buffg.drawString("���ǽ�� HP : " + 3, 1100, 70);
			}
		} else if (enemy_kill == 20) {
			if (Boss_List.size() == 0) {
				buffg.drawString("������ HP : " + 30, 1100, 70);
			} else {
				buffg.drawString("������ HP : " + 30, 1100, 70);
			}
		} else if (enemy_kill > 20 && enemy_kill<40) {
			if (Boss_List.size() == 0) {
				buffg.drawString("�ս��� HP : " + 6, 1100, 70);
			} else {
				buffg.drawString("�ս��� HP : " + 6, 1100, 70);
			}
		} else if (enemy_kill == 40) {
			if (Boss_List.size() == 0) {
				buffg.drawString("���ڸ� HP : " + 60, 1100, 70);
			} else {
				buffg.drawString("���ڸ� HP : " + 60, 1100, 70);
			}
		}

	}

	public void Draw_Score2() {
		buffg.setFont(new Font("Defualt", Font.BOLD, 50));
		buffg.drawString("Score : " + score, 520, 80);
	}

	public void Draw_ItemInfo() {// ������ �̹����� �׸��� �κ�
		ArrayList<Item> Iteminfo_List = new ArrayList<>();
		ArrayList<Item> Iteminfo2_List = new ArrayList<>();
		ArrayList<Item> Iteminfo3_List = new ArrayList<>();
		itm = new Item(0, 0, 0);
		Iteminfo_List.add(itm);
		for (int i = 0; i < Iteminfo_List.size(); ++i) {
			itm = (Item) Iteminfo_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(iteminfo_img[0], 10, 620, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(iteminfo_img[1], 10, 620, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(iteminfo_img[2], 10, 620, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(iteminfo_img[3], 10, 620, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(iteminfo_img[4], 10, 620, this);
			} else {
				buffg.drawImage(iteminfo_img[5], 10, 620, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}
		itm2 = new Item(0, 0, 0);
		Iteminfo2_List.add(itm2);
		for (int i = 0; i < Iteminfo2_List.size(); ++i) {
			itm2 = (Item) Iteminfo2_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(iteminfo2_img[0], 110, 620, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(iteminfo2_img[1], 110, 620, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(iteminfo2_img[2], 110, 620, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(iteminfo2_img[3], 110, 620, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(iteminfo2_img[4], 110, 620, this);
			} else {
				buffg.drawImage(iteminfo2_img[5], 110, 620, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}
		itm3 = new Item(0, 0, 0);
		Iteminfo3_List.add(itm3);
		for (int i = 0; i < Iteminfo3_List.size(); ++i) {
			itm3 = (Item) Iteminfo3_List.get(i);
			if ((cnt / 6 % 6) == 1) {
				buffg.drawImage(iteminfo3_img[0], 210, 620, this);
			} else if ((cnt / 6 % 6) == 2) {
				buffg.drawImage(iteminfo3_img[1], 210, 620, this);
			} else if ((cnt / 6 % 6) == 3) {
				buffg.drawImage(iteminfo3_img[2], 210, 620, this);
			} else if ((cnt / 6 % 6) == 4) {
				buffg.drawImage(iteminfo3_img[3], 210, 620, this);
			} else if ((cnt / 6 % 6) == 5) {
				buffg.drawImage(iteminfo3_img[4], 210, 620, this);
			} else {
				buffg.drawImage(iteminfo3_img[5], 210, 620, this);
			}
			// �迭�� ������ �� ���� �Ǻ��Ͽ� �̹��� �׸���
		}
	}

	/*------------------- S C O R E -------------------*/
	/*********************************************************************/
	/*------------------- K E Y E V E N T -------------------*/
	@Override
	public void keyTyped(KeyEvent e) {
		key.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Ű���尡 ���������� �̺�Ʈ ó���ϴ� ��
		key.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Ű���尡 �������ٰ� ���������� �̺�Ʈ ó���ϴ� ��
		key.keyReleased(e);
	}

	public void KeyProcess() {
		// ������ ĳ���� ������ ������ ����
		// ������ �޾Ƶ��� Ű���� ��������
		// Ű �Է½ø��� 5��ŭ�� �̵��� ��Ų��.
		key.KeyProcess();

	}
	/*------------------- K E Y E V E N T -------------------*/

	public void dispose1(GameFrame gmf) {
		// dispose();
		gmf.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	/*********************************************************************/
	Timer pikaover = new Timer();
	TimerTask pikaove_task = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Sound s1 = new Sound("Shooting��ī����ӿ���.mp3", false);
			s1.start();
		}

	};
	Timer pikaclear = new Timer();
	TimerTask pikaclear_task = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Sound s1 = new Sound("Shooting��ī�����Ŭ����.mp3", false);
			s1.start();
		}

	};
	/*
	 * public void threadStopd(boolean stop) { this.stop = stop; }
	 */

}
