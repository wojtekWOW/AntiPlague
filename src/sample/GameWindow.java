package sample;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;

public class GameWindow  {

    public BorderPane gotToGameWindow() throws FileNotFoundException {
        BorderPane root = new BorderPane();

        MyCenterPanel myCenterPanel = new MyCenterPanel();
        myCenterPanel.setId("centerPanel");
        MyRightPanel myRightPanel = new MyRightPanel();
        MyBottomPanel myBottomPanel = new MyBottomPanel();

        root.setCenter(myCenterPanel.createCentralPanel());
        root.setRight(myRightPanel.createRightPanel());
        root.setBottom(myBottomPanel.createBottomPanel());

        return root;
    }
}