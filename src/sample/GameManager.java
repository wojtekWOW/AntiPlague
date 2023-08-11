package sample;

public class GameManager {
    public static boolean game_over = false;
    public static boolean pause = false;
    public static int time = 1;
    public static double score;
    public static Country current_country;

    public static int getTime(){
        return time;
    }
    public static void pause(){
        pause = true;
    }
    public static void resume() {
        pause = false;
    }
    public static Country getCurrent_country(){
        return current_country;
    }
    public static void setCurrent_country(Country country){
        current_country = country;
    }
}
