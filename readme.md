Jmonkey Nifty Starter
=========
## Hit the ground running with JMonkey and NiftyGUI

![Archeology3d screenshot](/docs/jmonkey-nifty-select.png "Project screenshot")

Note: The project uses JMonkey 3's Netbeans based IDE. When initially trying to build, you may get exception: java.lang.ClassNotFoundException: org.jglfont.spi.BitmapFontRenderer. This is due to a bug in current jmonkey, and you will need to manually add jglfont-core-1.3.jar to the project.

When working on Archeology3d I hit a long learning curve trying to get some simple things done with NiftyGUI, which is Jmonkey's recommended widget framework. So I created this project for other developers who just want to have a simple working example.

In this tutorial, you will see how you can change colors of a node using Nifty’s list box.

There are 3 entities interacting in this demo: the app, the controller and nifty GUI.
* HelloNiftySelectApp.java – app wires everything up, displays the box
* HelloNiftySelectController.java – controller receives events from the GUI, and passes them on to the app
* hello-nifty-select-gui.xml – GUI contains the selection screen layout

# The app 
The important parts to pay attention to in the simpleInitApp method are around the controller creation. In the constructor, we pass reference to the app to the controller, so it can notify the app when GUI events are happening. We then pass this controller to nifty, and we tell nifty to start with “select” screen. We also tell nifty to initialize GUI screen layout based on hello-nifty-select-gui.xml. When select screen is displayed, user will need a mouse pointer to interact with the selections, so we disable the flycam and display the mouse. Now take a look at the two notification methods that the controller will use. First one, colorSelected, tells the box to change colors. New color will be displayed on the screen during the next app update method. Second notification method is called when the done button is clicked. We want to hide the selection screen and return to the game. To hide the selection screen, we tell nifty to display a special empty screen called “start.” We also re-enable the flycam and hide mouse pointer. 

# The controller

Bind method gets called by nifty when it initializes the screen. Nifty knows which controller class to bind to which screen, because it is specified in the screen layout xml file (see below). It also knows to bind the specific instance of the controller, because we passed it in the fromXml method in the app. Next, controller declares that it would like to be notified when list box “colorSelectionBox” selection changes, and when the done button is clicked.

# The GUI

We tell nifty that we will have two screens: one for selecting, and one for hiding. “Start” is a special screen that is required by the nifty framework, and here we use it to put nifty in idle state while we return main interaction to the game. While hiding nifty screens could also be accomplished by using nifty’s exit method, with the current version of the framework, you will save yourself from problems if you just follow the pattern in this tutorial.

Next, note that the select screen needs to specify the fully qualified name of the controller class. When fromXml method is called, nifty will pair up screens to controllers based on this assignments. If no instances of controllers are passed in, nifty will construct them automatically. As controllers are paired to screens, each controller is registered for event notifications and then bind method is called.

Another thing to point out is that the containing panel has “visibleToMouse” property set. If this property is not set, the contained controls will not respond to mouse events. Finally, note the control ids: doneButton and colorSelectionBox. These are strings that nifty uses to connect controller to the screen elements, the button and the listbox.

# Next steps

As you see, even a simple GUI interaction is a fairly complex matter to wire up. As interaction complexity grows, you will wonder how to split up responsibilities between classes and where to put certain logic. Most GUI developers follow a Model-View-Controller pattern to help them make these decisions.

References:

* Nifty controls page: http://sourceforge.net/apps/mediawiki/nifty-gui/index.php?title=Standard_Controls_ListBox 
* Nifty API: http://nifty-gui.sourceforge.net/projects/nifty/apidocs/index.html 
* Nifty controls API: http://nifty-gui.sourceforge.net/projects/1.3/nifty-default-controls/apidocs/index.html?de/lessvoid/nifty/controls/ListBox.html
* JMonkey Overlay Tutorial: http://hub.jmonkeyengine.org/wiki/doku.php/jme3:advanced:nifty_gui_overlay

