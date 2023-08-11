package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class MyCenterPanel extends AnchorPane {
    public static SVGPath svgpathUK_PL;
    public static SVGPath svgpathES_PL;
    public static SVGPath svgpathIT_GE;
    public static SVGPath svgpathGE_ES;
    public static SVGPath svgpathUK_ES ;

    List<Country> countries;
    public MyCenterPanel(){
        this.countries=GameInit.getCountries();
    }

    public AnchorPane createCentralPanel() throws FileNotFoundException {


        double r = 255;
        double g = 255;
        double b = 255;
        AnchorPane anchorPane = new AnchorPane();

        Button polandBTN = new Button("Poland");
        Button czechBTN = new Button("Czech");
        Button germanyBTN = new Button("Germany");
        Button franceBTN = new Button("France");
        Button spainBTN = new Button("Spain");
        Button nederlandsBTN = new Button("NL");
        Button austriaBTN = new Button("Austria");
        Button ukBTN = new Button("UK");
        Button italyBTN = new Button("Italy");
        Button swizBTN = new Button("Swiz");

        Label timeLabel = new Label("Time: 1");
        Label scoreLabel = new Label("Score: 0");

//        Image plane = new Image(new FileInputStream("C:\\Users\\wstachyr\\Documents\\studia\\PJA\\GUI\\Java\\AntiPlagueFXML\\src\\plane.png"));
//        ImageView imageView = new ImageView(plane);
//        imageView.setX(180);
//        imageView.setY(160);
//        imageView.setFitHeight(40);
//        imageView.setFitWidth(40);
//        imageView.setPreserveRatio(true);

        svgpathUK_PL = new SVGPath();
        svgpathES_PL = new SVGPath();
        svgpathIT_GE = new SVGPath();
        svgpathGE_ES = new SVGPath();
        svgpathUK_ES = new SVGPath();

        svgpathUK_PL.setId("planeUK_PL");
        svgpathES_PL.setId("planeES_PL");
        svgpathIT_GE.setId("planeIT_GE");
        svgpathGE_ES.setId("planeGE_ES");
        svgpathUK_ES.setId("planeUK_ES");

        String path = "M116 348 c3 -13 13 -35 20 -50 8 -15 14 -41 14 -58 0 -29 -2 -30 -43  -30 -33 0 -49 6 -67 25 -30 32 -42 32 -29 -2 7 -20 6 -28 -3 -35 -11 -6 -11  -10 0 -16 9 -7 10 -15 3 -35 -13 -34 -1 -34 29 -2 18 19 34 25 67 25 41 0 43  -1 43 -30 0 -17 -6 -43 -14 -58 -20 -40 -28 -72 -18 -72 4 0 33 36 63 79 l54  80 50 3 c36 2 50 7 50 18 0 11 -14 16 -50 18 l-50 3 -54 80 c-54 78 -79 100  -65 57z";

        svgpathUK_PL.setContent(path);
        svgpathES_PL.setContent(path);
        svgpathIT_GE.setContent(path);
        svgpathGE_ES.setContent(path);
        svgpathUK_ES.setContent(path);

//        svgpathES_PL.setTranslateX(60);
//        svgpathES_PL.setTranslateY(350);
        svgpathES_PL.setScaleX(0.1);
        svgpathES_PL.setScaleY(0.1);

//        svgpathIT_GE.setTranslateX(310);
//        svgpathIT_GE.setTranslateY(300);
        svgpathIT_GE.setScaleX(0.1);
        svgpathIT_GE.setScaleY(0.1);

//        svgpathGE_ES.setTranslateX(110);
//        svgpathGE_ES.setTranslateY(50);
        svgpathGE_ES.setScaleX(0.1);
        svgpathGE_ES.setScaleY(0.1);

//        svgpathUK_ES.setTranslateX(110);
//        svgpathUK_ES.setTranslateY(50);
        svgpathUK_ES.setScaleX(0.1);
        svgpathUK_ES.setScaleY(0.1);

//        svgpathUK_PL.setTranslateX(110);
//        svgpathUK_PL.setTranslateY(50);
        svgpathUK_PL.setScaleX(0.1);
        svgpathUK_PL.setScaleY(0.1);

        PathTransition pTUK_PL = new PathTransition(Duration.seconds(5), svgpathUK_PL);
        PathTransition pTES_PL = new PathTransition(Duration.seconds(5), svgpathES_PL);
        PathTransition pTIT_GE = new PathTransition(Duration.seconds(5), svgpathIT_GE);
        PathTransition pTGE_ES = new PathTransition(Duration.seconds(5), svgpathGE_ES);
        PathTransition pTUK_ES = new PathTransition(Duration.seconds(5), svgpathUK_ES);

        Path pathUK_PL = new Path();
        pathUK_PL.getElements().add(new MoveTo(230, 160));
        pathUK_PL.getElements().add(new LineTo(550, 210));
        pTUK_PL.setNode(svgpathUK_PL);
        pTUK_PL.setPath(pathUK_PL);
        pTUK_PL.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pTUK_PL.setDelay(Duration.seconds(3));
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        SequentialTransition seq = new SequentialTransition(pTUK_PL, pause);
        seq.setCycleCount(Animation.INDEFINITE);
        seq.play();

        Path pathES_PL = new Path();
        pathES_PL.getElements().add(new MoveTo(170, 380));
        pathES_PL.getElements().add(new LineTo(550, 210));
        pTES_PL.setNode(svgpathES_PL);
        pTES_PL.setPath(pathES_PL);
        pTES_PL.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pTES_PL.setDelay(Duration.seconds(8));
        PauseTransition pause1 = new PauseTransition(Duration.seconds(5));
        SequentialTransition seq1 = new SequentialTransition(pTES_PL, pause1);
        seq1.setCycleCount(Animation.INDEFINITE);
        seq1.play();

        Path pathIT_GE = new Path();
        pathIT_GE.getElements().add(new MoveTo(430, 380));
        pathIT_GE.getElements().add(new LineTo(450, 210));
        pTIT_GE.setNode(svgpathIT_GE);
        pTIT_GE.setPath(pathIT_GE);
        pTIT_GE.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pTIT_GE.setDelay(Duration.seconds(6));
        PauseTransition pause2 = new PauseTransition(Duration.seconds(3));
        SequentialTransition seq2 = new SequentialTransition(pTIT_GE, pause2);
        seq2.setCycleCount(Animation.INDEFINITE);
        seq2.play();

        Path pathGE_ES = new Path();
        pathGE_ES.getElements().add(new MoveTo(450, 210));
        pathGE_ES.getElements().add(new LineTo(170, 380));
        pTGE_ES.setNode(svgpathGE_ES);
        pTGE_ES.setPath(pathGE_ES);
        pTGE_ES.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pTGE_ES.setDelay(Duration.seconds(4));
        PauseTransition pause3 = new PauseTransition(Duration.seconds(9));
        SequentialTransition seq3 = new SequentialTransition(pTGE_ES, pause3);
        seq3.setCycleCount(Animation.INDEFINITE);
        seq3.play();

        Path pathUK_ES = new Path();
        pathUK_ES.getElements().add(new MoveTo(230, 160));
        pathUK_ES.getElements().add(new LineTo(170, 380));
        pTUK_ES.setNode(svgpathUK_ES);
        pTUK_ES.setPath(pathUK_ES);
        pTUK_ES.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pTUK_ES.setDelay(Duration.seconds(7));
        PauseTransition pause4 = new PauseTransition(Duration.seconds(8));
        SequentialTransition seq4 = new SequentialTransition(pTUK_ES, pause4);
        seq4.setCycleCount(Animation.INDEFINITE);
        seq4.play();

        polandBTN.setId("pl");
        czechBTN.setId("cz");
        germanyBTN.setId("de");
        franceBTN.setId("fr");
        swizBTN.setId("swiz");
        nederlandsBTN.setId("nl");
        austriaBTN.setId("au");
        ukBTN.setId("uk");
        italyBTN.setId("it");
        spainBTN.setId("es");
        timeLabel.setId("timeLabel");
        scoreLabel.setId("scoreLabel");

        polandBTN.getStyleClass().add("country-button");
        czechBTN.getStyleClass().add("country-button");
        germanyBTN.getStyleClass().add("country-button");
        franceBTN.getStyleClass().add("country-button");
        swizBTN.getStyleClass().add("country-button");
        nederlandsBTN.getStyleClass().add("country-button");
        austriaBTN.getStyleClass().add("country-button");
        ukBTN.getStyleClass().add("country-button");
        italyBTN.getStyleClass().add("country-button");
        spainBTN.getStyleClass().add("country-button");

        polandBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        czechBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        germanyBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        franceBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        swizBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        nederlandsBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        austriaBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        ukBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        italyBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");
        spainBTN.setStyle("-fx-background-color: rgb("+0+"," +g+","+0+")");

        ukBTN.setPrefSize(155, 180);
        franceBTN.setPrefSize(183, 181);
        nederlandsBTN.setPrefSize(78, 92);
        germanyBTN.setPrefSize(132,162);
        polandBTN.setPrefSize(136,118);
        czechBTN.setPrefSize(112,60);
        swizBTN.setPrefSize(70, 45);
        austriaBTN.setPrefSize(119, 60);
        italyBTN.setPrefSize(189, 214);
        spainBTN.setPrefSize(189, 175);

        polandBTN.setOnAction(buttonHandler);
        czechBTN.setOnAction(buttonHandler);
        germanyBTN.setOnAction(buttonHandler);
        franceBTN.setOnAction(buttonHandler);
        spainBTN.setOnAction(buttonHandler);
        nederlandsBTN.setOnAction(buttonHandler);
        austriaBTN.setOnAction(buttonHandler);
        ukBTN.setOnAction(buttonHandler);
        italyBTN.setOnAction(buttonHandler);
        swizBTN.setOnAction(buttonHandler);

//        AnchorPAne set anchors
        AnchorPane.setTopAnchor(ukBTN, 40.0);
        AnchorPane.setLeftAnchor(ukBTN, 120.0);

        AnchorPane.setTopAnchor(franceBTN, 220.0);
        AnchorPane.setLeftAnchor(franceBTN, 200.0);

        AnchorPane.setTopAnchor(nederlandsBTN, 172.0);
        AnchorPane.setLeftAnchor(nederlandsBTN, 300.0);

        AnchorPane.setTopAnchor(germanyBTN, 148.0);
        AnchorPane.setLeftAnchor(germanyBTN, 348.0);

        AnchorPane.setTopAnchor(polandBTN, 149.0);
        AnchorPane.setLeftAnchor(polandBTN, 465.0);

        AnchorPane.setTopAnchor(italyBTN, 312.0);
        AnchorPane.setLeftAnchor(italyBTN, 356.0);

        AnchorPane.setTopAnchor(spainBTN, 340.0);
        AnchorPane.setLeftAnchor(spainBTN, 100.0);

        AnchorPane.setTopAnchor(czechBTN, 230.0);
        AnchorPane.setLeftAnchor(czechBTN, 420.0);

        AnchorPane.setTopAnchor(austriaBTN, 270.0);
        AnchorPane.setLeftAnchor(austriaBTN, 400.0);

        AnchorPane.setTopAnchor(swizBTN, 295.0);
        AnchorPane.setLeftAnchor(swizBTN, 350.0);

        AnchorPane.setTopAnchor(timeLabel, 10.0);
        AnchorPane.setLeftAnchor(timeLabel, 10.0);
        AnchorPane.setTopAnchor(scoreLabel, 10.0);
        AnchorPane.setLeftAnchor(scoreLabel, 470.0);


        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                while(!GameManager.game_over){
                    Thread.sleep(1000);
                    if(!GameManager.pause){
                        if (isCancelled()) break;
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                timeLabel.setText("Time: "+String.valueOf(GameManager.getTime()));
                                scoreLabel.setText("Score: "+ String.valueOf(Math.round(GameManager.score)));

                                polandBTN.setStyle("-fx-background-color: rgb("+countries.get(0).red+"," +countries.get(0).green+","+countries.get(0).blue+")");
                                czechBTN.setStyle("-fx-background-color: rgb("+countries.get(1).red+"," +countries.get(1).green+","+countries.get(1).blue+")");
                                germanyBTN.setStyle("-fx-background-color: rgb("+countries.get(2).red+"," +countries.get(2).green+","+countries.get(2).blue+")");
                                franceBTN.setStyle("-fx-background-color: rgb("+countries.get(3).red+"," +countries.get(3).green+","+countries.get(3).blue+")");
                                spainBTN.setStyle("-fx-background-color: rgb("+countries.get(4).red+"," +countries.get(4).green+","+countries.get(4).blue+")");
                                swizBTN.setStyle("-fx-background-color: rgb("+countries.get(5).red+"," +countries.get(5).green+","+countries.get(5).blue+")");
                                nederlandsBTN.setStyle("-fx-background-color: rgb("+countries.get(6).red+"," +countries.get(6).green+","+countries.get(6).blue+")");
                                austriaBTN.setStyle("-fx-background-color: rgb("+countries.get(7).red+"," +countries.get(7).green+","+countries.get(7).blue+")");
                                ukBTN.setStyle("-fx-background-color: rgb("+countries.get(8).red+"," +countries.get(8).green+","+countries.get(8).blue+")");
                                italyBTN.setStyle("-fx-background-color: rgb("+countries.get(9).red+"," +countries.get(9).green+","+countries.get(9).blue+")");
                            }
                        });
                    }

                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        anchorPane.setId("centerPanel");
        anchorPane.getChildren().addAll(polandBTN, czechBTN, germanyBTN, franceBTN, spainBTN, nederlandsBTN, austriaBTN, ukBTN, italyBTN, swizBTN, timeLabel, scoreLabel, svgpathUK_PL, svgpathES_PL, svgpathIT_GE, svgpathGE_ES, svgpathUK_ES);

        return anchorPane;
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            String [] countryarray = {"Poland", "Czech", "Germany", "France", "Spain", "Swiz", "NL", "Austria", "UK", "Italy"};

            Button b = (Button)event.getSource();
            String country_name = b.getText();
            int i = findIndex(countryarray, country_name);

            GameManager.setCurrent_country(GameInit.countries.get(i));
            System.out.println(GameManager.getCurrent_country());
            event.consume();
        }
    };


    public static int findIndex(String arr [], String s){
        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (arr[i] == s) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }
}
