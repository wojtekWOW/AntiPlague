package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.Vector;

public class ConnectionsWindow extends ListView {
    Vector<String> connections;
    Country country;
    public ConnectionsWindow(Vector<String> connections, Country country){
        this.connections=connections;
        this.country=country;
    }

    public VBox goConnectionWindow() {
        ObservableList<String> list = FXCollections.observableArrayList(connections);

        ListView<String> listView = new ListView(list);
        listView.setOrientation(Orientation.VERTICAL);

        Button removeConnection = new Button("Remove connection");
        removeConnection.setOnAction(e -> {
            int index = listView.getSelectionModel().getSelectedIndex();
            if(index>=0){
                listView.getItems().remove(index);
                this.country.connections.remove(index);
                this.connections.remove(index);
                this.country.income--;
                if(this.country.name=="United Kingdom" && index==2){
                    MyCenterPanel.svgpathUK_ES.setVisible(false);
                }
                if(this.country.name=="United Kingdom" && index==1){
                    MyCenterPanel.svgpathUK_PL.setVisible(false);
                }
                if(this.country.name=="Spain" && index==1){
                    MyCenterPanel.svgpathES_PL.setVisible(false);
                }
                if(this.country.name=="Italy" && index==6){
                    MyCenterPanel.svgpathIT_GE.setVisible(false);
                }
                if(this.country.name=="Germany" && index==6){
                    MyCenterPanel.svgpathGE_ES.setVisible(false);
                }
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(listView, removeConnection);
        return vBox;
    }
}
