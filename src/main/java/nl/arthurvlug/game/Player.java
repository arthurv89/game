package nl.arthurvlug.game;

import org.springframework.stereotype.Component;

@Component
public class Player {
	private int bullets = 10;

	public void decreaseBullet() {
		bullets--;
	}

	public void incrementBullets(final int plus) {
		bullets += plus;
	}

	public int getBullets() {
		return bullets;
	}
}
