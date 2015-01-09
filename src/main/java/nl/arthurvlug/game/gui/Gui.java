package nl.arthurvlug.game.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gui {
	@Autowired private Crosshair crosshair;
	@Autowired private Bullets bullets;
	
	public void initialize() {
		crosshair.initialize();
		bullets.initialize();
	}

	public void refreshBullets() {
		bullets.refresh();
	}
}
