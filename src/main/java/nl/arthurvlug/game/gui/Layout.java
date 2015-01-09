package nl.arthurvlug.game.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jme3.font.BitmapFont;

@Component
public class Layout {
	@Autowired private Game game;
	
	public BitmapFont getFont() {
		return game.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
	}
}
