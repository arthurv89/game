package nl.arthurvlug.game.level;

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
