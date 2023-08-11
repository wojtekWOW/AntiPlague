package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyRightPanel extends Pane {
    public VBox createRightPanel(){
        VBox vBox = new VBox();

        Label worldSTATS = new Label("World Stats");
        Label total_healthyLabel = new Label("1");
        Label total_infectedLabel = new Label("1");
        Label total_curedLabel = new Label("1");
        Label total_deadLabel = new Label("1");
        Label total_vaccinatedLabel = new Label("1");
        VBox worldstatsPanel = new VBox();

        worldstatsPanel.getChildren().addAll(worldSTATS,total_healthyLabel, total_infectedLabel, total_curedLabel, total_deadLabel, total_vaccinatedLabel);
        ScrollPane scrollPane1 = new ScrollPane(worldstatsPanel);
        vBox.getChildren().add(scrollPane1);

        total_healthyLabel.setText("Healthy: " + (Country.total_healthy));
        total_infectedLabel.setText("Infected: " + (Country.total_infected));
        total_curedLabel.setText("Cured: " + (Country.total_cured));
        total_deadLabel.setText("Dead: " + (Country.total_dead));
        total_vaccinatedLabel.setText("Vaccinated: " + (Country.total_vaccinated));

        worldSTATS.getStyleClass().add("ccLabel");
        total_healthyLabel.getStyleClass().add("labelClass");
        total_infectedLabel.getStyleClass().add("labelClass");
        total_curedLabel.getStyleClass().add("labelClass");
        total_deadLabel.getStyleClass().add("labelClass");
        total_vaccinatedLabel.getStyleClass().add("labelClass");

        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                while(!GameManager.game_over){
                    Thread.sleep(1000);
                    if(!GameManager.pause){

                        if (isCancelled()) break;
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                total_healthyLabel.setText("Healthy: " + Math.round(Country.total_healthy));
                                total_infectedLabel.setText("Infected: " + Math.round(Country.total_infected));
                                total_curedLabel.setText("Cured: " + Math.round(Country.total_cured));
                                total_deadLabel.setText("Dead: " + Math.round(Country.total_dead));
                                total_vaccinatedLabel.setText("Vaccinated: " + Math.round(Country.total_vaccinated));
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

        Label current_countyLabel = new Label("Country");
        current_countyLabel.getStyleClass().add("ccLabel");
        Label current_country_budget = new Label("Budget");
        Label current_country_income = new Label("Budget");
        Label country_healthyLabel = new Label("0");
        Label country_infectedLabel = new Label("0");
        Label country_curedLabel = new Label("0");
        Label country_deadLabel = new Label("0");
        Label country_vaccinatedLabel = new Label("0");
        Label country_virus_resistanceLabel = new Label("0");
        Label country_death_resistanceLabel = new Label("0");

        current_country_budget.getStyleClass().add("labelClass2");
        current_country_income.getStyleClass().add("labelClass2");
        country_healthyLabel.getStyleClass().add("labelClass2");
        country_infectedLabel.getStyleClass().add("labelClass2");
        country_curedLabel.getStyleClass().add("labelClass2");
        country_deadLabel.getStyleClass().add("labelClass2");
        country_vaccinatedLabel.getStyleClass().add("labelClass2");
        country_virus_resistanceLabel.getStyleClass().add("labelClass2");
        country_death_resistanceLabel.getStyleClass().add("labelClass2");

        VBox countrystatsPanel = new VBox();
        countrystatsPanel.getChildren().addAll(current_countyLabel, current_country_budget, current_country_income, country_healthyLabel, country_infectedLabel, country_curedLabel, country_deadLabel, country_vaccinatedLabel, country_virus_resistanceLabel, country_death_resistanceLabel);
        ScrollPane scrollPane2 = new ScrollPane(countrystatsPanel);
        vBox.getChildren().add(scrollPane2);



        Task<Void> task2 = new Task<Void>() {
            @Override protected Void call() throws Exception {
                while(!GameManager.game_over){
                    Thread.sleep(1000);
                    if(!GameManager.pause){

//                        if (isCancelled()) break;
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                Country cc = GameManager.getCurrent_country();
                                if(cc!=null){
                                    current_countyLabel.setText("" + cc);
                                    current_country_budget.setText("Budget      " + Math.round(cc.budget));
                                    current_country_income.setText("Income      " + Math.round(cc.income));
                                    country_healthyLabel.setText("Healthy:     " + Math.round(cc.healthy));
                                    country_infectedLabel.setText("Infected:    " + Math.round(cc.infected));
                                    country_curedLabel.setText("Cured:        " + Math.round(cc.cured));
                                    country_deadLabel.setText("Dead:         " + Math.round(cc.dead));
                                    country_vaccinatedLabel.setText("Vaccinated: " + Math.round(cc.vaccinated));
                                    country_virus_resistanceLabel.setText("Resistance to infection: " + Math.round(cc.virus_resistance));
                                    country_death_resistanceLabel.setText("Resistance to death:     " + Math.round(cc.death_resistance));
                                }
                            }
                        });
                    }

                }
                return null;
            }
        };
        Thread th2 = new Thread(task2);
        th2.setDaemon(true);
        th2.start();

        return vBox;
    }
}
