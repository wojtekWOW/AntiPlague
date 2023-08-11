package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Vector;

public class Main extends Application {

    public static Stage primStage;
    public static GameInit gameInit;
    public static Stage gameWindow, diffChoiceWindow;
    public static Scene gameScene, menuWindow;
    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox vBox = new VBox();
        vBox.setId("menu");

        Button resumeBTN = new Button("Resume");
        resumeBTN.setId("resume");
        resumeBTN.setVisible(false);
        resumeBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    GameManager.resume();
                    gameWindow.setScene(gameScene);
                }
        });

        Button exitBTN = new Button("Exit");
        exitBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        KeyCombination kc = new KeyCodeCombination(KeyCode.Q, KeyCombination.ALT_DOWN);
        Mnemonic mn = new Mnemonic(exitBTN, kc);

//        Button pauseBTN = new Button("Pause");
//        pauseBTN.setVisible(false);
//        pauseBTN.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                if(!GameManager.pause) {
//                    System.out.println("PAUSE");
//                    GameManager.pause();
//                    gameWindow = (Stage) pauseBTN.getScene().getWindow();
//                    menuWindow.lookup("#resume").setVisible(true);
//                    gameWindow.setScene(menuWindow);
//                }
//            }
//        });
//        KeyCombination pausekc = new KeyCodeCombination(KeyCode.P, KeyCombination.ALT_DOWN);
//        Mnemonic pausemn = new Mnemonic(pauseBTN, pausekc);

        Button newGameBTN = new Button("New Game");
        newGameBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuWindow = Main.primStage.getScene();
                resumeBTN.setVisible(true);
                Main.primStage.close();
                diffChoiceWindow = new Stage();
                diffChoiceWindow.setTitle("AntiPlagueFX Difficulty");
                VBox vBox1 = new VBox();
                Button easyBTN = new Button("Easy");
                easyBTN.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        gameInit = new GameInit(1);
                        GameWindow gW = new GameWindow();
                        try {
                            gameScene=new Scene(gW.gotToGameWindow(),1000,600);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        gameScene.addMnemonic(mn);
                        gameScene.addMnemonic(MyBottomPanel.pausemn);
                        gameWindow = (Stage) easyBTN.getScene().getWindow();
                        gameWindow.setScene(gameScene);
                        gameScene.getStylesheets().add("style.css");

                    }
                });
                Button mediumBTN = new Button("Medium");
                mediumBTN.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        gameInit = new GameInit(2);
                        GameWindow gW = new GameWindow();
                        try {
                            gameScene=new Scene(gW.gotToGameWindow(),1000,600);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        gameScene.addMnemonic(mn);
                        gameScene.addMnemonic(MyBottomPanel.pausemn);
                        gameWindow = (Stage) easyBTN.getScene().getWindow();
                        gameWindow.setScene(gameScene);
                        gameScene.getStylesheets().add("style.css");
                    }
                });
                Button difficultBTN = new Button("Difficult");
                difficultBTN.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        gameInit = new GameInit(3);
                        GameWindow gW = new GameWindow();
                        try {
                            gameScene=new Scene(gW.gotToGameWindow(),1000,600);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        gameScene.addMnemonic(mn);
                        gameScene.addMnemonic(MyBottomPanel.pausemn);
                        gameWindow = (Stage) easyBTN.getScene().getWindow();
                        gameWindow.setScene(gameScene);
                        gameScene.getStylesheets().add("style.css");
                    }
                });
                Button deadlyBTN = new Button("Deadly");
                deadlyBTN.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        gameInit = new GameInit(4);
                        GameWindow gW = new GameWindow();
                        try {
                            gameScene=new Scene(gW.gotToGameWindow(),1000,600);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        gameScene.addMnemonic(mn);
                        gameScene.addMnemonic(MyBottomPanel.pausemn);
                        gameWindow = (Stage) easyBTN.getScene().getWindow();
                        gameWindow.setScene(gameScene);
                        gameScene.getStylesheets().add("style.css");
                    }
                });

                easyBTN.setMaxWidth(Double.MAX_VALUE);
                mediumBTN.setMaxWidth(Double.MAX_VALUE);
                difficultBTN.setMaxWidth(Double.MAX_VALUE);
                deadlyBTN.setMaxWidth(Double.MAX_VALUE);
                vBox1.getChildren().addAll(easyBTN,mediumBTN,difficultBTN,deadlyBTN);
                vBox1.setSpacing(1.0);
                vBox1.setPadding(new Insets(0, 50, 10, 50));
                diffChoiceWindow.setScene(new Scene(vBox1, 300, 124));

                diffChoiceWindow.show();
            }
        });

        Button highScoresBTN = new Button("High Scores");
        highScoresBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Vector<String> lines1 = new Vector<String>();
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects.bin"))) {
                    ScoreItem scoreItem;
                    while (true) {
                        scoreItem = (ScoreItem) inputStream.readObject();
                        lines1.add(scoreItem.toString());
                    }
                }  catch (EOFException ex) {
                    System.out.println("File ended2");
                }  catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                ObservableList<String> list = FXCollections.observableArrayList(lines1);

                ListView<String> listView = new ListView(list);
                listView.setOrientation(Orientation.VERTICAL);
                listView.setId("listViewScores");

                VBox vBox = new VBox();
                vBox.setId("vBoxScores");
                ScrollPane scrollPane = new ScrollPane(listView);
                scrollPane.setId("scrollPaneScores");
                vBox.getChildren().addAll(scrollPane);

                Scene highScoreScene=new Scene(vBox,300,350);
                highScoreScene.getStylesheets().add("style.css");
                Stage highScoresWindow = new Stage();
                highScoresWindow.setTitle("High Scores");
                highScoresWindow.setScene(highScoreScene);

                highScoresWindow.show();
            }
        });

        newGameBTN.setMaxWidth(Double.MAX_VALUE);
        highScoresBTN.setMaxWidth(Double.MAX_VALUE);
        exitBTN.setMaxWidth(Double.MAX_VALUE);
        resumeBTN.setMaxWidth(Double.MAX_VALUE);
        vBox.getChildren().addAll(newGameBTN, highScoresBTN, exitBTN, resumeBTN);
        vBox.setPadding(new Insets(0, 50, 10, 50));
        vBox.setSpacing(1.0);

        primaryStage.setTitle("AntiPlagueFX");
        Scene menuScene = new Scene(vBox, 300,150);
        menuScene.addMnemonic(mn);
//        menuScene.addMnemonic(pausemn);
        primaryStage.setScene(menuScene);
        menuScene.getStylesheets().add("style.css");
        primStage=primaryStage;
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
