package nl.arthurvlug.game.level;

import nl.arthurvlug.game.Game;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Wall extends Geometry {
	public Wall(final Box box, final Vector3f position, final Game game) {
		super("wall", box);
		
		final Material material = new Material(game.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		material.setColor("Color", ColorRGBA.Red);
		setMaterial(material);
		
		setLocalTranslation(position);
	}
}
