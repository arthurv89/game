package nl.arthurvlug.game.level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.arthurvlug.game.Updatable;
import nl.arthurvlug.game.gui.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;

@Component
public class Level implements Updatable {
	@Autowired private Game game;
	@Autowired private Player player;

	private List<Enemy> enemies = new ArrayList<Enemy>();
	
	public void initialize() {
		newEnemy();
		
		List<Wall> walls = Arrays.asList(
				new Wall(new Box(5, 50, 50), new Vector3f(30, -20, -40), game)
		);
		for (Wall wall : walls) {
			game.getRootNode().attachChild(wall);
		}
	}


	private void newEnemy() {
		Enemy enemy = new Enemy(game, player);
		enemy.initialize();
		
		enemies.add(enemy);
	}


	@Override
	public void simpleUpdate(float tpf) {
		for(Enemy enemy : enemies) {
			enemy.simpleUpdate(tpf);
		}
	}
}
