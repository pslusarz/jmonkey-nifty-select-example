package org.sw7d.jme3.helloworld;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.List;

public class HelloNiftySelectController implements ScreenController {

    private final HelloNiftySelectApp app;

    public HelloNiftySelectController(HelloNiftySelectApp app) {
        this.app = app;
    }

    public void bind(Nifty nifty, Screen screen) {
        ListBox theBox = screen.findNiftyControl("colorSelectionBox", ListBox.class);
        for (String color : app.colorSelections.keySet()) {
            theBox.addItem(color);
        }

    }

    @NiftyEventSubscriber(id = "colorSelectionBox")
    public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
        List<String> selection = event.getSelection();
        app.colorSelected(selection.get(0));
    }

    @NiftyEventSubscriber(id = "doneButton")
    public void onDoneButtonClicked(final String id, final ButtonClickedEvent event) {
        app.doneSelecting();

    }
    
    public void onStartScreen() {
    }

    public void onEndScreen() {
    }
}
