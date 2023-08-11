package sample;

import javafx.concurrent.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInit {
    public GameManager gameManager;
    public Virus virus;
    int dificulty;
    static List<Country> countries;
    Country virus_origin;

    public GameInit(int diff) {
        this.dificulty = diff;
        GameInit.countries = new ArrayList<>();
        String[] countryarray = {"Poland", "Czech", "Germany", "France", "Spain", "Switzerland", "Nederland", "Austria", "United Kingdom", "Italy"};
        Country poland = new Country("Poland", 300, 40000000);
        Country czech = new Country("Czech", 900, 5000000);
        Country germany = new Country("Germany", 1000, 80000000);
        Country france = new Country("France", 1500, 80000000);
        Country spain = new Country("Spain", 200, 70000000);
        Country switzerland = new Country("Switzerland", 2000, 1500000);
        Country nederland = new Country("Nederland", 800, 7000000);
        Country austria = new Country("Austria", 500, 5000000);
        Country united_kingdom = new Country("United Kingdom", 2000, 80000000);
        Country italy = new Country("Italy", 500, 30000000);
        GameInit.countries.add(poland);
        GameInit.countries.add(czech);
        GameInit.countries.add(germany);
        GameInit.countries.add(france);
        GameInit.countries.add(spain);
        GameInit.countries.add(switzerland);
        GameInit.countries.add(nederland);
        GameInit.countries.add(austria);
        GameInit.countries.add(united_kingdom);
        GameInit.countries.add(italy);

        poland.addConnection(czech, ConnectionType.PLANES);
        poland.addConnection(czech, ConnectionType.PASSENGER_SHIPS);
        poland.addConnection(germany, ConnectionType.CARGO_SHIPS);
        poland.addConnection(france, ConnectionType.CARS);
        poland.addConnection(france, ConnectionType.TRAINS);
        poland.addConnection(italy, ConnectionType.PLANES);

        germany.addConnection(czech, ConnectionType.PASSENGER_SHIPS);
        germany.addConnection(france, ConnectionType.TRAINS);
        germany.addConnection(france, ConnectionType.TRUCKS);
        germany.addConnection(italy, ConnectionType.TRAINS);
        germany.addConnection(italy, ConnectionType.CARS);
        germany.addConnection(spain, ConnectionType.PLANES);

        spain.addConnection(poland, ConnectionType.PLANES);
        spain.addConnection(czech, ConnectionType.PLANES);
        spain.addConnection(czech, ConnectionType.PASSENGER_SHIPS);
        spain.addConnection(czech, ConnectionType.CARGO_SHIPS);
        spain.addConnection(france, ConnectionType.TRUCKS);
        spain.addConnection(switzerland, ConnectionType.CARGO_SHIPS);
        spain.addConnection(switzerland, ConnectionType.PLANES);
        spain.addConnection(italy, ConnectionType.PLANES);

        czech.addConnection(nederland, ConnectionType.CARGO_SHIPS);
        czech.addConnection(nederland, ConnectionType.PLANES);
        czech.addConnection(united_kingdom, ConnectionType.PLANES);
        czech.addConnection(switzerland, ConnectionType.PLANES);
        czech.addConnection(austria, ConnectionType.CARGO_SHIPS);

        nederland.addConnection(switzerland, ConnectionType.CARGO_SHIPS);
        nederland.addConnection(switzerland, ConnectionType.PLANES);
        nederland.addConnection(austria, ConnectionType.PLANES);
        nederland.addConnection(france, ConnectionType.PLANES);

        united_kingdom.addConnection(poland, ConnectionType.PLANES);
        united_kingdom.addConnection(spain, ConnectionType.PLANES);
        united_kingdom.addConnection(switzerland, ConnectionType.PLANES);
        united_kingdom.addConnection(austria, ConnectionType.PLANES);
        united_kingdom.addConnection(austria, ConnectionType.CARS);
        united_kingdom.addConnection(austria, ConnectionType.TRUCKS);
        united_kingdom.addConnection(france, ConnectionType.CARGO_SHIPS);

        austria.addConnection(france, ConnectionType.CARGO_SHIPS);
        austria.addConnection(italy, ConnectionType.PLANES);

        france.addConnection(italy, ConnectionType.TRAINS);

        italy.addConnection(germany, ConnectionType.PLANES);

        Random rand = new Random();
        this.virus_origin = countries.get(rand.nextInt(countries.size()));
        this.virus_origin.infected = 100;
        this.virus_origin.infected_today.put(1, (double) 100);
        this.virus_origin.healthy = this.virus_origin.healthy - this.virus_origin.infected;
        Country.total_infected = 100;
        Country.total_healthy = Country.total_healthy - Country.total_infected;

        virus = new Virus(diff);
        gameManager = new GameManager();
        TimeThread l = new TimeThread();
        Task timeWorker = l.createWorker(virus, countries);

        Thread threadTH = new Thread(timeWorker);
        threadTH.setDaemon(true);
        threadTH.start();

    }
    public static List<Country> getCountries(){
        return GameInit.countries;
    }
}

