package hul;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyevent implements KeyListener{
	
	
	/*------------------- K E Y E V E N T -------------------*/
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Ű���尡 ���������� �̺�Ʈ ó���ϴ� ��
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			GameFrame.KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			GameFrame.KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			GameFrame.KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			GameFrame.KeyRight = true;
			break;
		case KeyEvent.VK_SPACE: // �����̽�Ű �Է� ó�� �߰�
			GameFrame.KeySpace = true;
			break;
		case KeyEvent.VK_ENTER: // ����Ű �Է� ó�� �߰�
			GameFrame.KeyEnter = true;

			
			
			break;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Ű���尡 �������ٰ� ���������� �̺�Ʈ ó���ϴ� ��
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			GameFrame.KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			GameFrame.KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			GameFrame.KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			GameFrame.KeyRight = false;
			break;
		case KeyEvent.VK_SPACE: // �����̽�Ű �Է� ó�� �߰�
			GameFrame.KeySpace = false;
			break;
		case KeyEvent.VK_ENTER: // ����Ű �Է� ó�� �߰�
			GameFrame.KeyEnter = false;
			break;
			
		}
	}

	public void KeyProcess() {
		// ������ ĳ���� ������ ������ ����
		// ������ �޾Ƶ��� Ű���� ��������
		// Ű �Է½ø��� 5��ŭ�� �̵��� ��Ų��.
		if (GameFrame.KeyUp == true)
			if (GameFrame.y > 80)
				GameFrame.y -= 5;
		GameFrame.player_Status = 0;
		// �̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
		if (GameFrame.KeyDown == true)
			if (GameFrame.y + GameFrame.Player_img[0].getHeight(null) < StartGame.SCREEN_HEIGHT - 60)
				GameFrame.y += 5;
		GameFrame.player_Status = 0;
		// �̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
		if (GameFrame.KeyLeft == true)
			if (GameFrame.x > 0)
				GameFrame.x -= 5;
		GameFrame.player_Status = 0;
		// �̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
		if (GameFrame.KeyRight == true)
			if (GameFrame.x + GameFrame.Player_img[0].getWidth(null) < StartGame.SCREEN_WIDTH)
				GameFrame.x += 5;
		GameFrame.player_Status = 0;
		// �̵�Ű�� �������� �÷��̾� ���¸� 0���� �����ϴ�.
		
		//if (GameFrame.KeyEnter == true);
			
		
	}
	/*------------------- K E Y E V E N T -------------------*/
}
