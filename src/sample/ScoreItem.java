package sample;

import java.io.Serializable;

public class ScoreItem implements Serializable{
    String user_name;
    int score;
    public ScoreItem(String name, int score){
        this.user_name=name;
        this.score=score;
    }

    @Override
    public String toString() {
        return user_name + " score=" + score;
    }
}
