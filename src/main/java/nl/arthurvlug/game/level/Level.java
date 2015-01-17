package nl.arthurvlug.game.level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.arthurvlug.game.Game;
import nl.arthurvlug.game.Updatable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Box;

@Component
public class Level implements Updatable {
	@Autowired private Game game;
	@Autowired private PlayerStatus playerStatus;

	private final List<Enemy> enemies = new ArrayList<Enemy>();
	
	public void initialize() {
        BulletAppState bulletAppState = new BulletAppState();
        game.getStateManager().attach(bulletAppState);

		PhysicsTestHelper.createPhysicsTestWorld(game.getRootNode(), game.getAssetManager(), bulletAppState.getPhysicsSpace());

        
		newEnemy();
		
		final List<Wall> walls = Arrays.asList(
				new Wall(new Box(5, 50, 50), new Vector3f(30, -20, -40), game)
		);
		for (final Wall wall : walls) {
			game.getRootNode().attachChild(wall);
		}
	}


	private void newEnemy() {
		final Enemy enemy = new Enemy(game, playerStatus);
		enemy.initialize();
		
		enemies.add(enemy);
	}


	@Override
	public void simpleUpdate(final float tpf) {
		for(final Enemy enemy : enemies) {
			enemy.simpleUpdate(tpf);
		}
	}
}
