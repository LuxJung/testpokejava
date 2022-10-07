package hul;

public class SoundEffect extends Thread {
	// Sound t1 = new Sound("ShootingÀÏ¹Ý¸÷ºê±Ý.mp3", true);
	Sound t2 = new Sound("Shootingº¸½º¸÷ºê±Ý.mp3", true);
	private boolean stop;
	GameFrame gf;

	// Sound t3 = new Sound("Shootingº¸½º¸÷ºê±Ý.mp3", true);
	public SoundEffect(Sound introSound3) {
		this.t2 = introSound3;
		this.stop = false;
	}

	public void run() {
		while (!stop) {
			synchronized (GameFrame.trd) {
				if (GameFrame.enemy_kill > 20) {
					Sound t2 = new Sound("Shootingº¸½º¸÷ºê±Ý.mp3", true);
					 t2.start();
					if (t2.getState() == Thread.State.NEW) {
						if (GameFrame.trd.getState()==Thread.State.TIMED_WAITING) {
							t2.stop();
						}else {
						t2.start();}

					}else if(gf.player_Hp<1) {
						t2.close();
					}

				}
			}
		}
	}

	public void threadStop(boolean stop) {
		this.stop = stop;
	}

}
