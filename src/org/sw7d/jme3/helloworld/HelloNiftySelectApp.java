package org.sw7d.jme3.helloworld;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import de.lessvoid.nifty.Nifty;
import java.util.HashMap;
import java.util.Map;

public class HelloNiftySelectApp extends SimpleApplication {

    private Nifty nifty;
    Material selectedColor;
    Map<String, ColorRGBA> colorSelections = new HashMap<String, ColorRGBA>();

    public void simpleInitApp() {        
        colorSelections.put("Red", ColorRGBA.Red);
        colorSelections.put("Blue", ColorRGBA.Blue);
        
        createBox();
        
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort, 2048, 2048);
        nifty = niftyDisplay.getNifty();
        HelloNiftySelectController controller = new HelloNiftySelectController(this);
        nifty.fromXml("Interface/hello-nifty-select-gui.xml", "select", controller);
        guiViewPort.addProcessor(niftyDisplay);
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
    }
    
    public void doneSelecting() {
        nifty.gotoScreen("start");
        flyCam.setEnabled(true);
        inputManager.setCursorVisible(false);
    }
    
    public void colorSelected(String color) {
       selectedColor.setColor("Color", colorSelections.get(color)); 
    }
    
    private void createBox() {
        Box box = new Box(1,1,1);
        Geometry geometry = new Geometry("Box", box);
        selectedColor = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        selectedColor.setColor("Color", colorSelections.values().iterator().next());
        geometry.setMaterial(selectedColor);
        rootNode.attachChild(geometry);
    }
}