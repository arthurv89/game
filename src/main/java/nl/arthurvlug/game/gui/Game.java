package nl.arthurvlug.game.gui;

import java.util.ArrayList;
import java.util.List;

import nl.arthurvlug.game.Enemy;
import nl.arthurvlug.game.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

@Component
public class Game extends SimpleApplication {
	@Autowired private Player player;
	@Autowired private Crosshair crosshair;
	@Autowired private Gui gui;
	private List<Enemy> enemies = new ArrayList<Enemy>();

	public void simpleInitApp() {
		gui.initialize();
		newEnemy();
	}

	public void newEnemy() {
		Enemy enemy = new Enemy(this, player);
		enemy.initialize();
		
		enemies.add(enemy);
	}
	
	public AppSettings getSettings() {
		return settings;
	}

	public Gui getGui() {
		return gui;
	}
	
	@Override
	public void simpleUpdate(final float tpf) {
		for(Enemy enemy : enemies) {
			enemy.simpleUpdate(tpf);
		}
	}
	
	public static void main(final String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Game game = (Game) applicationContext.getBean("game");
        game.start();
	}
}
