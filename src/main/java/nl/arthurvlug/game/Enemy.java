package nl.arthurvlug.game;

import nl.arthurvlug.game.gui.Game;

import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Enemy implements Updatable {
	private static final String LEFT_CLICK = "LeftClick";
	
	private final Game game;
	private final Player player;
	private Geometry boundingBox;
	
	public Enemy(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public void initialize() {
		boundingBox = createEnemy();
		
		addClickHandler();

		game.getRootNode().attachChild(boundingBox);
	}

	/* Use the main event loop to trigger repeating actions. */
	@Override
	public void simpleUpdate(final float tpf) {
		boundingBox.rotate(tpf, 2 * tpf, 3*tpf);
		boundingBox.move(-5 * tpf, 0, 0);
	}

	private Geometry createEnemy() {
		final Box box = new Box(2, 3, 4);

		final Material material = new Material(game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		material.setColor("Color", ColorRGBA.Blue);
		
		final Geometry enemy = new Geometry("blue cube", box);
		enemy.setMaterial(material);
		setRandomLocation(enemy);

		return enemy;
	}

	private void setRandomLocation(final Geometry movingBox) {
		movingBox.setLocalTranslation(
				(float) -(Math.random()*10),
				(float) -(Math.random()*10),
				(float) -(Math.random()*50 + 50));
	}

	private void addClickHandler() {
		game.getInputManager().addMapping(LEFT_CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		game.getInputManager().addListener(new ActionListener() {
			@Override
			public void onAction(final String name, final boolean isPressed, final float tpf) {
				if (isPressed) {
					if (isHit()) {
						player.incrementBullets(5);
						setRandomLocation(boundingBox);
					} else {
						player.decreaseBullet();
					}
					game.getGui().refreshBullets();
				}
			}

			private boolean isHit() {
				final Ray ray = new Ray(game.getCamera().getLocation(), game.getCamera().getDirection());
				return boundingBox.collideWith(ray, new CollisionResults()) > 0;
			}
		}, LEFT_CLICK);
	}
}
