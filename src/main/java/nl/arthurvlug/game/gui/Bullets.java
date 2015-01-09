package nl.arthurvlug.game.gui;

import nl.arthurvlug.game.level.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;

@Component
public class Bullets {
	@Autowired private Layout layout;
	@Autowired private Player player;
	@Autowired private Game game;
	private BitmapText bulletsLabel;

	public void initialize() {
		BitmapFont font = layout.getFont();
		bulletsLabel = new BitmapText(font);
		bulletsLabel.setSize(font.getCharSet().getRenderedSize());
		
		refresh();
		
		game.getGuiNode().attachChild(bulletsLabel);
	}

	public void refresh() {
		bulletsLabel.setText(player.getBullets() + " bullets");
		bulletsLabel.setLocalTranslation(
				game.getSettings().getWidth() - bulletsLabel.getLineWidth() - 10,
				game.getSettings().getHeight() - bulletsLabel.getLineHeight(),
				0);
	}
}
