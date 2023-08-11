package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Vector;


public class MyBottomPanel extends GridPane {

    Stage gameWindow, connectionsWindow;
    Scene gameScene, menuScene, connectionsScene;
    public static Mnemonic pausemn;
    public GridPane createBottomPanel(){
        menuScene = Main.primStage.getScene();
        GridPane flowPane = new GridPane();

        Button pauseBTN = new Button("Pause");
        pauseBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(!GameManager.pause) {
                    GameManager.pause();
                    gameWindow = (Stage) pauseBTN.getScene().getWindow();
                    menuScene.lookup("#resume").setVisible(true);
                    gameWindow.setScene(menuScene);
                }
            }
        });
        KeyCombination kc = new KeyCodeCombination(KeyCode.P, KeyCombination.ALT_DOWN);
        pausemn = new Mnemonic(pauseBTN, kc);

        Button closeConnectionBTN = new Button("Close Connection");
        closeConnectionBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country country = GameManager.getCurrent_country();
                List<Map<Country, ConnectionType>> conn = country.connections;
                Vector<String> connections = new Vector<>();
                int no_conections = conn.size();
                for(int i =0; i < no_conections; i++){
                    for(Map.Entry<Country, ConnectionType> entry: conn.get(i).entrySet()){
                        connections.add(entry.getKey() + " " + entry.getValue());
                    }
                }

                ConnectionsWindow connWindow = new ConnectionsWindow(connections, country);
                connectionsScene=new Scene(connWindow.goConnectionWindow(),300,400);
                connectionsWindow = new Stage();
                connectionsWindow.setTitle("Connections from " + country);
                connectionsWindow.setScene(connectionsScene);
                connectionsWindow.show();
            }
        });

        Button buyRespBTN = new Button("Buy Respirators $200");
        buyRespBTN.setOnAction(e -> {
            Country curent_country = GameManager.getCurrent_country();
            if(curent_country.budget<200){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("New Respirators");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " has not enough funds to buy respirators!");

                alert.showAndWait();
            }

            if((curent_country.death_resistance+20)>100){
                curent_country.death_resistance=100;
            }else
                curent_country.death_resistance+=20;
            curent_country.budget-=200;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Respirators");
            alert.setHeaderText(null);
            alert.setContentText(curent_country + " has been equiped with respirators!");
            alert.showAndWait();
        });

        Button developMedBTN = new Button("Develop Medicine $500");
        developMedBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.budget<500){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("New Medicine");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has not enough funds to research new medicine!");
                    alert.showAndWait();
                    return;
                }

                if((curent_country.virus_resistance+20)>100){
                    curent_country.virus_resistance=100;
                }else
                    curent_country.virus_resistance+=20;
                if((curent_country.death_resistance+20)>100){
                    curent_country.death_resistance=100;
                }else
                    curent_country.death_resistance+=20;
                curent_country.budget-=500;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("New Medicine");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " has researched new medicine!");
                alert.showAndWait();
            }
        });

        Button socialPropBTN = new Button("Social Propaganda $500");
        socialPropBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.budget<500){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Social Propaganda");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has not enough funds to influence media!");
                    alert.showAndWait();
                    return;
                }

                if((curent_country.virus_resistance+10)>100){
                    curent_country.virus_resistance=100;
                }else
                    curent_country.virus_resistance+=10;
                curent_country.budget-=500;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Social Propaganda");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " has bought mainstream TV!");
                alert.showAndWait();
            }
        });

        Button closeForestBTN = new Button("Close Forest $100");
        closeForestBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.budget<100){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Close forest");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has not enough funds to close forests!");
                    alert.showAndWait();
                    return;
                }

                if((curent_country.virus_resistance+5)>100){
                    curent_country.virus_resistance=100;
                }else
                    curent_country.virus_resistance+=5;
                curent_country.budget-=100;
                ((Button)e.getSource()).setDisable(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Close forest");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " has prohibit entry to forest!");
                alert.showAndWait();
            }
        });

        Button wearMaskBTN = new Button("Wear masks $100");
        wearMaskBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.budget<100){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Face masks");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has not enough funds to buy masks!");
                    alert.showAndWait();
                    return;
                }
                if(curent_country.masks){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Face masks");
                    alert.setHeaderText(null);
                    alert.setContentText("In " + curent_country + " wearing face masks has been already enforced before!");
                    alert.showAndWait();
                    return;
                }

                if((curent_country.virus_resistance+15)>100){
                    curent_country.virus_resistance=100;
                }else
                    curent_country.virus_resistance+=15;
                curent_country.masks=true;
                curent_country.budget-=100;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Face masks");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " enforces masks in public places!");
                alert.showAndWait();
            }
        });

        Button vaccinateBTN = new Button("Vaccinate 10% population $600");
        vaccinateBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.budget<600){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vaccine 10%");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has not enough funds to vaccinate population!");
                    alert.showAndWait();
                    return;
                }

                if(curent_country.vaccinated<curent_country.pop){
                    if(curent_country.healthy<1){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Vaccine 10%");
                        alert.setHeaderText(null);
                        alert.setContentText("In " +curent_country + "there is no one healthy to vaccinate!");
                        alert.showAndWait();
                        return;
                    }
                    if(curent_country.vaccinated>=curent_country.healthy){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Vaccine 10%");
                        alert.setHeaderText(null);
                        alert.setContentText("In " +curent_country + "there is no one left to vaccinate!");
                        alert.showAndWait();
                        return;
                    }
                    if((curent_country.virus_resistance+20)>100){
                        curent_country.virus_resistance=100;
                    }else{
                        curent_country.virus_resistance+=20;
                    }
                    curent_country.vaccinated += (curent_country.healthy/10);
                    curent_country.healthy-= curent_country.healthy/10;
                    curent_country.budget-=600;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vaccine 10%");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + "'s 10% of healthy people has been vaccinated!");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vaccine 100%");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + "'s whole population has been already vaccinated!");
                    alert.showAndWait();
                }
            }
        });

        Button printMoneyBTN = new Button("Print Money");
        printMoneyBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if(curent_country.virus_resistance<30){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Print Money");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " has to low virus resistance to print money!");
                    alert.showAndWait();
                    return;
                }

                curent_country.budget+=1000;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Print Money");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " has printed $1000!");
                alert.showAndWait();
            }
        });

        Button increaseTax = new Button("Increase Tax");
        increaseTax.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Country curent_country = GameManager.getCurrent_country();
                if((curent_country.infected+curent_country.dead)>(curent_country.pop/10)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Increase Tax");
                    alert.setHeaderText(null);
                    alert.setContentText(curent_country + " Raising tax will create revolt!!");
                    alert.showAndWait();
                    return;
                }

                curent_country.income+=5;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Increase Tax");
                alert.setHeaderText(null);
                alert.setContentText(curent_country + " raised TAX by $5!");
                alert.showAndWait();
            }
        });

        pauseBTN.getStyleClass().add("bottomBTNClass");
        closeConnectionBTN.getStyleClass().add("bottomBTNClass");
        buyRespBTN.getStyleClass().add("bottomBTNClass");
        developMedBTN.getStyleClass().add("bottomBTNClass");;
        socialPropBTN.getStyleClass().add("bottomBTNClass");
        closeForestBTN.getStyleClass().add("bottomBTNClass");
        wearMaskBTN.getStyleClass().add("bottomBTNClass");
        vaccinateBTN.getStyleClass().add("bottomBTNClass");
        printMoneyBTN.getStyleClass().add("bottomBTNClass");
        increaseTax.getStyleClass().add("bottomBTNClass");

        flowPane.add(pauseBTN,0,0);
        flowPane.add(closeConnectionBTN,0,1);
        flowPane.add(buyRespBTN,1,0);
        flowPane.add(developMedBTN,2,0);
        flowPane.add(socialPropBTN,3,0);
        flowPane.add(closeForestBTN,1,1);
        flowPane.add(wearMaskBTN,2,1);
        flowPane.add(vaccinateBTN,3,1);
        flowPane.add(increaseTax,4,0);
        flowPane.add(printMoneyBTN,4,1);
//        flowPane.getChildren().addAll(, buyRespBTN, , , , , , , );

        return flowPane;
    }
}
