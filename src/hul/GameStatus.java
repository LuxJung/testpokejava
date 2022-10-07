package hul;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStatus extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	public GameFrame gf = null;
	private ImageIcon optExitOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âxoff.png");
	private ImageIcon optExitOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âxon.png");

	private ImageIcon optReStartOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âreStart(on).png");
	private ImageIcon optReStartOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âreStart(off).png");

	private ImageIcon optGoMainOn = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âgoMain(on).png");
	private ImageIcon optGoMainOff = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�âgoMain(off).png");

	private Image StatusBackground = new ImageIcon(
			"C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�â.png").getImage();

	private JLabel optBar = new JLabel(
			new ImageIcon("C:\\Users\\newjm\\OneDrive\\Desktop\\����\\�����\\����\\4���� Ŭ������ ���\\resize��ī��\\�ɼ�â��.png"));

	//private JButton optExit = new JButton(optExitOn);

	private JButton reStartButton = new JButton(optReStartOn);
	private JButton goMainButton = new JButton(optGoMainOn);

	private int mouseX, mouseY;

	Keyevent key;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public GameStatus() {
		setUndecorated(true);
		setTitle("������Ƽ���װ�");// ������ �̸�

		setSize(500, 500);// ������ ũ�⼳��
		setResizable(false); // �������� ũ�⸦ ���Ƿ� ������ϰ� ����
		setLocationRelativeTo(null);// �������� �߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrameâ ����� ���α׷� ����
		setVisible(true); // �������� ���� ���̰� ���ϴ�.

		setBackground(new Color(0, 0, 0, 122));
		setLayout(null);

		// �޴������� ����
		/*optExit.setBounds(435, 20, 30, 30);
		optExit.setBorderPainted(false);
		optExit.setContentAreaFilled(false);
		optExit.setFocusPainted(false);
		optExit.addMouseListener(new MouseAdapter() {
			// ���콺�� x��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				optExit.setIcon(optExitOff);
				optExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� x��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				optExit.setIcon(optExitOn);
				optExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� x��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {
				optExit.addActionListener((ActionListener) new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
						GameFrame.trd.resume();
						GameFrame.KeyEnter = false;
					}
				});
			}
		});
		add(optExit);*/

		// �޴��� ����
		optBar.setBounds(0, 0, 500, 50);
		optBar.addMouseListener(new MouseAdapter() {
			// ���콺 ��ġ�� �޾ƿ�
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		add(optBar);

		// ���������
		reStartButton.setBounds(100, 100, 300, 100);
		reStartButton.setBorderPainted(false);
		reStartButton.setContentAreaFilled(false);
		reStartButton.setFocusPainted(false);
		reStartButton.addMouseListener(new MouseAdapter() {
			// ���콺�� x��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				reStartButton.setIcon(optReStartOff);
				reStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� x��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				reStartButton.setIcon(optReStartOn);
				reStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� x��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				GameFrame.trd.resume();
				GameFrame.KeyEnter = false;
			}
		});
		add(reStartButton);

		// ����GoMain����
		goMainButton.setBounds(100, 300, 300, 100);
		goMainButton.setBorderPainted(false);
		goMainButton.setContentAreaFilled(false);
		goMainButton.setFocusPainted(false);
		goMainButton.addMouseListener(new MouseAdapter() {
			// ���콺�� GoMain��ư�� �ö����� ��ȭ
			@Override
			public void mouseEntered(MouseEvent e) {
				goMainButton.setIcon(optGoMainOff);
				goMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� GoMain��ư���� ���������� ��ȭ
			@Override
			public void mouseExited(MouseEvent e) {
				goMainButton.setIcon(optGoMainOn);
				goMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� GoMain��ư�� �������� ����
			@Override
			public void mousePressed(MouseEvent e) {

				goMainButton.addActionListener((ActionListener) new ActionListener() {
					@SuppressWarnings("null")
					@Override
					public void actionPerformed(ActionEvent e) {						
						gf.dispose1(gf);
						gf.actionPerformed(e);
						GameFrame.trd.stop();
					}
				});
				
				System.exit(0);
				
			}
		});
		add(goMainButton);

	}

	

	public void paint(Graphics g) {
		screenImage = createImage(500, 500);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(StatusBackground, 0, 0, null);
		paintComponents(g);// �޴���ư
		this.repaint();
	}
/*
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER: // ����Ű �Է� ó�� �߰�
			GameFrame.KeyEnter = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER: // ����Ű �Է� ó�� �߰�
			GameFrame.KeyEnter = false;
			break;
		}
	}*/
}
