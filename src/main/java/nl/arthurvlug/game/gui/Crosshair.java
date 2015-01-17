package nl.arthurvlug.game.gui;

import nl.arthurvlug.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;

@Component
public class Crosshair {
	@Autowired private Game game;

	public void initialize() {
		BitmapFont guiFont = game.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
		final BitmapText crosshair = new BitmapText(guiFont);
		crosshair.setText("+");
		crosshair.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		crosshair.setLocalTranslation((game.getSettings().getWidth() / 2) - (guiFont.getCharSet().getRenderedSize() / 3 * 2), (game.getSettings().getHeight() / 2) + (crosshair.getLineHeight() / 2), 0);
		game.getGuiNode().attachChild(crosshair);
	}
}
