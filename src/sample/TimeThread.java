package sample;

import javafx.concurrent.Task;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Vector;


public class TimeThread{

    public Task createWorker(Virus virus, List<Country> countries ) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("Start123");

                while(!GameManager.game_over){
                    try {
                        Thread.sleep(1000);
                        if(!GameManager.pause){

                            System.out.println("Timing " + GameManager.time);
                            virus.spread(countries);
                            virus.infect(countries);
                            virus.cure(countries);
                            Country.updateStats(countries);
                            GameManager.time++;

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if((Country.total_pop-Country.total_vaccinated-Country.total_dead)<1 || Country.total_healthy<1){
                    String m = JOptionPane.showInputDialog("Game over! Virus has infected entire world!\nOnly those who took vaccine survived.\nYour sore is: " + GameManager.score + "\nWrite your name");
                    System.out.println(m);
                    saveUserToScoreList(m);
                }
                if(Country.total_infected<1){
                    String m = JOptionPane.showInputDialog("Victory! Virus has been contained!\\n Your sore is: " + GameManager.score + " \nWrite your name");
                    System.out.println(m);
                    saveUserToScoreList(m);
                }
                return null;
            }
        };

    }
    public void saveUserToScoreList(String s) throws IOException{

        Vector<ScoreItem> lines1 = new Vector<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects.bin"))) {
            ScoreItem scoreItem;
            while (true) {
                scoreItem = (ScoreItem) inputStream.readObject();
                lines1.add(scoreItem);
            }
        }  catch (EOFException e) {
            System.out.println("File ended");
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("objects.bin"))) {
            ScoreItem scoreItem = new ScoreItem(s, (int)Math.round(GameManager.score));
            lines1.add(scoreItem);
            for( ScoreItem scores: lines1){
                outputStream.writeObject(scores);
                System.out.println(scores);
            }
        }
    }
}
