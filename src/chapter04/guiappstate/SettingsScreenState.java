package chapter04.guiappstate;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
  * This demo package shows a game UI switcher with three AppStates. 
  * This state is active while the game is paused and the user changes settings.
 */
public class SettingsScreenState extends AbstractAppState {

    private ViewPort viewPort;
    private Node rootNode;
    private Node guiNode;
    private AssetManager assetManager;
    private SimpleApplication app;
    private InputManager inputManager;
    private Node localRootNode = new Node("Settings Screen RootNode");
    private Node localGuiNode = new Node("Settings Screen GuiNode");
    private final ColorRGBA backgroundColor = ColorRGBA.DarkGray;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.rootNode = this.app.getRootNode();
        this.viewPort = this.app.getViewPort();
        this.guiNode = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        
        rootNode.attachChild(localRootNode);
        guiNode.attachChild(localGuiNode);
        viewPort.setBackgroundColor(backgroundColor);
        inputManager.setCursorVisible(false);
        this.app.getFlyByCamera().setDragToRotate(true);


        /** Load this scene */
        viewPort.setBackgroundColor(backgroundColor);

        Box mesh = new Box(new Vector3f(-1, -1, 0), .5f, .5f, .5f);
        Geometry geom = new Geometry("Box", mesh);
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        geom.setMaterial(mat);
        geom.setLocalTranslation(1, 0, 0);
        localRootNode.attachChild(geom);

        /** Load the HUD */
        BitmapFont guiFont = assetManager.loadFont(
                "Interface/Fonts/Default.fnt");
        BitmapText displaytext = new BitmapText(guiFont);
        displaytext.setSize(guiFont.getCharSet().getRenderedSize());
        displaytext.move(10, displaytext.getLineHeight() + 20, 0);
        displaytext.setText("Settings screen. Press RETURN to save "
                + "and return to start screen.");
        localGuiNode.attachChild(displaytext);
    }

    @Override
    public void update(float tpf) {
        /** some action can happen here */
    }
 
    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);
        guiNode.detachChild(localGuiNode);
    }
}