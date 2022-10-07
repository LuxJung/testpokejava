package hul;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameIntro extends JFrame implements Runnable{

	private Image screenImage;
	private Graphics screenGraphic;
	public GameFrame gf ;
	// �޴��� �����ư �̹���
	private ImageIcon menuExitOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\barExit.png");
	private ImageIcon menuExitOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\barExit(on).png");

	// ���� ���� ���� �̹���
	private ImageIcon introStartOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\Start(on).png");
	private ImageIcon introStartOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\Start(off).png");

	private ImageIcon introExitOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\Exit(on).png");
	private ImageIcon introExitOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\Exit(off).png");

	private Image introBackground = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\ó��ȭ���̰�.png").getImage();

	private JLabel menuBar = new JLabel(
			new ImageIcon("C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\bar.png"));

	private JButton menuExit = new JButton(menuExitOn);

	private JButton startButton = new JButton(introStartOn);
	private JButton exitButton = new JButton(introExitOn);

	private int mouseX, mouseY;
	Sound introSound = new Sound("Shooting��Ʈ�κ��.mp3", true);
	Sound introSound2 = new Sound("Shooting��Ʈ�ι�ư��ŸƮ.mp3", true);
	Sound introSound3 = new Sound("Shooting��Ʈ�ι�ư����.mp3", true);
	public GameIntro() {
	
		setUndecorated(true);
		setTitle("������Ƽ���װ�");// ������ �̸�
		setSize(StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);// ������ ũ�⼳��
		setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		setLocationRelativeTo(null);// �������� �߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrameâ ����� ���α׷� ����
		setVisible(true); // �������� ���� ���̰� ���ϴ�.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		introSound.start();
		
		
		
		// �޴������� ����
		menuExit.setBounds(1250, 0, 30, 30);
		menuExit.setBorderPainted(false);
		menuExit.setContentAreaFilled(false);
		menuExit.setFocusPainted(false);
		menuExit.addMouseListener(new MouseAdapter() {
			// ���콺�� x��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				menuExit.setIcon(menuExitOff);
				menuExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� x��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				menuExit.setIcon(menuExitOn);
				menuExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� x��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(menuExit);

		// �޴��� ����
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			// ���콺 ��ġ�� �޾ƿ�
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			// ���콺 ��ġ���� ���� �巡�� ����
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		// ���ӽ��� ����
		startButton.setBounds(60, 350, 316, 106);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			// ���콺�� x��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(introStartOff);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� x��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(introStartOn);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� gmaestart��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {
				// ���ӽ����̺�Ʈ
				// start();
				
				ThirdReport mp3 = new ThirdReport("C:\\Users\\newjm\\Downloads\\���ð���ȿ����\\Shooting��Ʈ�ι�ư��ŸƮ.mp3");
				mp3.Play();
				introSound.close();
				intro_timer.schedule(start_task, 2200);
				try {
					gf = new GameFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		add(startButton);
		startButton.addActionListener((ActionListener) new ActionListener() {
			// ��ŸƮ ��ư�� ���������� ���� �׼�
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ����� â(��Ʈ��â)�� �����ũ�� �޼ҵ�
				
				dispose();
				
			}
		});

		// �����������
		exitButton.setBounds(60, 500, 316, 106);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// ���콺�� x��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(introExitOff);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� x��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(introExitOn);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� x��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {
				introSound3.start();
				exit_progrem();
				
				
			}
		});
		add(exitButton);
		}
	

	public void paint(Graphics g) {
		screenImage = createImage(StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		paintComponents(g);// �޴���ư
		this.repaint();

	}

	public void start() {
		startButton.setVisible(false);
		exitButton.setVisible(false);
		introBackground = new ImageIcon(// ��׶��� �ִϸ��̼� ǥ���� ���� �̹����� �迭�� ����
				"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\��׶���.png").getImage();
		
	}
	

	public void exit_progrem() {
		intro_timer.schedule(exit_task, 1000);
	}
	
	Timer intro_timer = new Timer();
	TimerTask exit_task = new TimerTask() {

		@Override
		public void run() {
			System.exit(0);
		}
		
	};
	TimerTask start_task = new TimerTask() {
		@Override
		public void run() {
			//introSound2.close();
			introSound3.close();
			
		}
		
	};
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
