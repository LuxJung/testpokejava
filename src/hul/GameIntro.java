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
	// 메뉴바 종료버튼 이미지
	private ImageIcon menuExitOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\barExit.png");
	private ImageIcon menuExitOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\barExit(on).png");

	// 게임 실행 졸료 이미지
	private ImageIcon introStartOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\Start(on).png");
	private ImageIcon introStartOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\Start(off).png");

	private ImageIcon introExitOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\Exit(on).png");
	private ImageIcon introExitOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\Exit(off).png");

	private Image introBackground = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\처음화면이거.png").getImage();

	private JLabel menuBar = new JLabel(
			new ImageIcon("C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\bar.png"));

	private JButton menuExit = new JButton(menuExitOn);

	private JButton startButton = new JButton(introStartOn);
	private JButton exitButton = new JButton(introExitOn);

	private int mouseX, mouseY;
	Sound introSound = new Sound("Shooting인트로브금.mp3", true);
	Sound introSound2 = new Sound("Shooting인트로버튼스타트.mp3", true);
	Sound introSound3 = new Sound("Shooting인트로버튼종료.mp3", true);
	public GameIntro() {
	
		setUndecorated(true);
		setTitle("슈우우우티이잉겜");// 프레임 이름
		setSize(StartGame.SCREEN_WIDTH, StartGame.SCREEN_HEIGHT);// 프레임 크기설정
		setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
		setLocationRelativeTo(null);// 프레임을 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame창 종료시 프로그램 종료
		setVisible(true); // 프레임을 눈에 보이게 띄웁니다.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		introSound.start();
		
		
		
		// 메뉴바종료 관련
		menuExit.setBounds(1250, 0, 30, 30);
		menuExit.setBorderPainted(false);
		menuExit.setContentAreaFilled(false);
		menuExit.setFocusPainted(false);
		menuExit.addMouseListener(new MouseAdapter() {
			// 마우스가 x버튼에 올라갔을때 변화
			@Override
			public void mouseEntered(MouseEvent e) {
				menuExit.setIcon(menuExitOff);
				menuExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 x버튼에서 떨어졌을때 변화
			@Override
			public void mouseExited(MouseEvent e) {
				menuExit.setIcon(menuExitOn);
				menuExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우스가 x버튼을 눌었을때 종료
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(menuExit);

		// 메뉴바 관련
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			// 마우스 위치를 받아옴
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			// 마우스 위치값을 통해 드래그 가능
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		// 게임시작 관련
		startButton.setBounds(60, 350, 316, 106);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			// 마우스가 x버튼에 올라갔을때 변화
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(introStartOff);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 x버튼에서 떨어졌을때 변화
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(introStartOn);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우스가 gmaestart버튼을 눌었을때 종료
			@Override
			public void mousePressed(MouseEvent e) {
				// 게임시작이벤트
				// start();
				
				ThirdReport mp3 = new ThirdReport("C:\\Users\\newjm\\Downloads\\슈팅게임효과음\\Shooting인트로버튼스타트.mp3");
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
			// 스타트 버튼을 눌렀을때에 대한 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재 띄워진 창(인트로창)을 종료시크는 메소드
				
				dispose();
				
			}
		});

		// 게임종료관련
		exitButton.setBounds(60, 500, 316, 106);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			// 마우스가 x버튼에 올라갔을때 변화
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(introExitOff);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우스가 x버튼에서 떨어졌을때 변화
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(introExitOn);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우스가 x버튼을 눌었을때 종료
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
		paintComponents(g);// 메뉴버튼
		this.repaint();

	}

	public void start() {
		startButton.setVisible(false);
		exitButton.setVisible(false);
		introBackground = new ImageIcon(// 백그라운드 애니메이션 표현을 위해 이미지를 배열로 받음
				"C:\\Users\\newjm\\OneDrive\\Desktop\\럭정\\팀노바\\기초\\4주차 클래스와 상속\\resize피카츄\\백그라운드.png").getImage();
		
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
