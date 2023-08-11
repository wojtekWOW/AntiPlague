package sample;

import java.util.*;
import java.util.Random;

public class Country extends Stats{
    String name;
    int budget;
    int income;
    boolean masks;
    double virus_resistance;
    double death_resistance;
    int red;
    int green;
    int blue;

    Map<Integer, Double> infected_today = new HashMap<>();
    List<String> upgrades;
    public List<Map<Country, ConnectionType>> connections;

    public Country(String name, int budget, long pop){
        this.name = name;
        this.budget = budget;
        this.income = 10;
        this.masks = false;

        Random rand = new Random();
        this.virus_resistance = 5 + rand.nextInt(15+1);
        this.death_resistance = 20 + rand.nextInt( 50 + 1);
        connections = new ArrayList<>();
        this.pop = pop;
        this.healthy= this.pop;
        this.dead= 0;
        this.cured= 0;
        this.vaccinated=0;
        this.infected=0;
        this.red=(int)Math.round(this.dead/this.pop) * 255;
        this.green=255-((int)Math.round(this.dead/this.pop) * 255);
        total_pop= total_pop + this.pop;
        total_healthy=total_pop;
        total_cured =0;
        total_dead=0;
        total_infected=0;
        total_vaccinated=0;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addConnection(Country country, ConnectionType connectionType){

        connections.add(new HashMap<>());
        connections.get(connections.size()-1).put(country, connectionType);
        country.connections.add(new HashMap<>());
        country.connections.get(country.connections.size()-1).put(this, connectionType);
    }

    public static void updateStats(List<Country> countries){

        long t_pop=0;
        long t_healthy=0;
        long t_vaccinated=0;
        long t_infected=0;
        long t_cured=0;
        long t_dead=0;
        for( Country c: countries){
            t_pop += c.pop;
            t_healthy+=c.healthy;
            t_vaccinated+=c.vaccinated;
            t_infected+=c.infected;
            t_cured+=c.cured;
            t_dead+=c.dead;
            c.budget +=c.income;
            c.red=(int)Math.round(c.infected/100000 * 255);
            c.green=255-((int)Math.round(c.infected/100000 * 255));
            if(c.dead>=c.pop){
                c.red=255;
                c.green=255;
                c.blue=255;
            }else if((c.dead>=(c.pop-c.vaccinated))&c.vaccinated>0){
                c.dead=c.pop-c.vaccinated;
                c.healthy=c.vaccinated;
                c.infected=0;
                c.red=150;
                c.green=150;
                c.blue=150;
            }
        }
        Country.total_healthy=t_healthy;
        Country.total_cured=t_cured;
        Country.total_infected=t_infected;
        Country.total_dead=t_dead;
        Country.total_vaccinated=t_vaccinated;
        Country.total_pop=t_pop;
        GameManager.score= (total_cured + total_vaccinated)/GameManager.getTime();
        if((Country.total_pop-Country.total_vaccinated-Country.total_dead)<1 || Country.total_healthy<1 || Country.total_infected<1){
            if(Country.total_healthy>1){
                System.out.println("Victory");
                GameManager.score=GameManager.score+(Country.total_healthy/10000);
            }
            System.out.println("Defeat");
            GameManager.game_over=true;
        }
    }
    public static void clearStats(List<Country> countries){
        for(Country c: countries){
            c.income = 10;
            Random rand = new Random();
            c.virus_resistance = 5 + rand.nextInt(15+1);
            c.death_resistance = 20 + rand.nextInt( 50 + 1);
            c.connections.clear();
            c.healthy= c.pop;
            c.dead= 0;
            c.cured= 0;
            c.vaccinated=0;
            c.infected=0;
            total_pop= total_pop + c.pop;
            total_healthy=total_pop;
            total_cured =0;
            total_dead=0;
            total_infected=0;
            total_vaccinated=0;
        }
    }
}