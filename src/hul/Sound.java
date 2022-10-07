package hul;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound extends Thread {

	public Player player;
	public boolean isLoop;
	public File file;
	public FileInputStream fis;
	public BufferedInputStream bis;
	public boolean stop;
	public Sound(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(StartGame.class.getResource("../sound/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			this.stop = false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void threadStopp(boolean stop) {
		this.stop = stop;
	}

}
