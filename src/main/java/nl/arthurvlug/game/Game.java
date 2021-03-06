package nl.arthurvlug.game;

import nl.arthurvlug.game.gui.Gui;
import nl.arthurvlug.game.level.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

@Component
public class Game extends SimpleApplication {
	@Autowired private Gui gui;
	@Autowired private Level level;

	public void simpleInitApp() {
		gui.initialize();
		level.initialize();
	}
	
	@Override
	public void simpleUpdate(final float tpf) {
		level.simpleUpdate(tpf);
	}
	
	
	
	
	
	public AppSettings getSettings() {
		return settings;
	}

	public Gui getGui() {
		return gui;
	}
	
	public static void main(final String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Game game = (Game) applicationContext.getBean("game");
        game.start();
	}
}
