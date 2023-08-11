package sample;

import java.util.List;
import java.util.Map;

public class Virus {
    int contagiousness;
    int sick_time;
    public Virus(int diff){
        this.contagiousness=diff*2;
        this.sick_time=diff*2+30;
    }
    public void spread(List<Country> countries){
        int time = GameManager.time;
        for(Country c: countries){
            if(c.infected>0){
                List<Map<Country, ConnectionType>> connections = c.connections;
                int no_connections = connections.size();
//                System.out.println(no_connections);
                for(int i =0; i < no_connections; i++){
//                    System.out.println(connections.get(i).entrySet());
                    for(Map.Entry<Country, ConnectionType> entry: connections.get(i).entrySet()){
                        Country countryToInfect = entry.getKey();
//                        System.out.println(countryToInfect);

//                        System.out.println(factor);
                        if(countryToInfect.healthy>0){
                            double factor = Math.round((this.contagiousness * c.infected/(float)500000) * ((100 -  countryToInfect.virus_resistance)/100));
                            System.out.println("Spreading from "+ c + " to " + countryToInfect + " " + factor);
                            double today_inf =0;
                            switch(entry.getValue()){
                                case CARGO_SHIPS:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor)){
                                        countryToInfect.infected+= Math.round(factor) ;
                                        countryToInfect.healthy-=Math.round(factor);
                                        today_inf+=(Math.round(factor));
                                    }
                                    break;
                                case PASSENGER_SHIPS:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor *40)){
                                        countryToInfect.infected+=Math.round(factor *40);
                                        countryToInfect.healthy-=Math.round(factor *40);
                                        today_inf+=( Math.round(factor * 40));
                                    }
                                    break;
                                case PLANES:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor *800)){
                                        countryToInfect.infected+=Math.round(factor *800);
                                        countryToInfect.healthy-=Math.round(factor *800);
                                        today_inf+=(Math.round(factor*800));
                                    }
                                    break;
                                case CARS:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor *500)){
                                        countryToInfect.infected+=Math.round(factor *500);
                                        countryToInfect.healthy-=Math.round(factor *500);
                                        today_inf+=(Math.round(factor*500));
                                    }
                                    break;
                                case TRAINS:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor *300)){
                                        countryToInfect.infected+=Math.round(factor *300);
                                        countryToInfect.healthy-=Math.round(factor *300);
                                        today_inf+=(Math.round(factor*300));
                                    }
                                    break;
                                case TRUCKS:
                                    if((countryToInfect.healthy - countryToInfect.vaccinated)>Math.round(factor *50)){
                                        countryToInfect.infected+=Math.round(factor *50);
                                        countryToInfect.healthy-=Math.round(factor *50);
                                        today_inf+=(Math.round(factor*50));
                                    }
                                    break;
                                default:
                                    System.out.println("No more connections from " + c);
                            }
                            if(countryToInfect.infected_today.containsKey(time)){
                                countryToInfect.infected_today.put(time, countryToInfect.infected_today.get(time)+today_inf);
//                                System.out.println(countryToInfect.infected_today.get(time) +" aleady infected");
                            }else{
                                countryToInfect.infected_today.put(time, today_inf);
//                                System.out.println(countryToInfect.infected_today.get(time) +" first infection today");
                            }


                        }
//                        else if (countryToInfect.healthy<0){countryToInfect.healthy=0;}
                    }
                }
            }
        }
    }

    public void infect(List<Country> countries){
        int time = GameManager.time;
        for(Country c: countries){
            if(!(c.infected_today.containsKey(time))) {
                c.infected_today.put(time, (double)0);
            }
            if(c.infected>0){
                if((c.healthy-c.vaccinated)<1){
                    //do nothing
                }else{
                    double infect = Math.round((c.infected/(double)50*this.contagiousness) * (((double)100 - c.virus_resistance)/(double)100));
//                    if((c.infected+infect)>c.pop-c.dead
                    System.out.println(infect+ " " + c.infected + " " + c.virus_resistance);
                    if((c.healthy-c.vaccinated-(double)Math.round(infect))<1){
                        infect+=c.healthy-c.vaccinated;
                        c.healthy=c.vaccinated;

                        if(c.infected_today.containsKey(time)){
                            c.infected_today.put(time, c.infected_today.get(time)+infect);
//                           System.out.println(c.infected_today.get(time) +" aleady infected");
                        }else{
                            c.infected_today.put(time, infect);
//                           System.out.println(c.infected_today.get(time) +" first infection today");
                        }

                    }else{
                        c.infected+= (double)Math.round(infect);
                        c.healthy-= (double)Math.round(infect);

                        if(c.infected_today.containsKey(time)){
                            c.infected_today.put(time, c.infected_today.get(time)+(double)Math.round(infect));
//                           System.out.println(c.infected_today.get(time) +" aleady infected");
                        }else{
                            c.infected_today.put(time, (double)Math.round(infect));
//                           System.out.println(c.infected_today.get(time) +" first infection today");
                        }
                    }

                }
            }
        }
    }
    public void cure(List<Country> countries){
        int time = GameManager.time;
        for(Country c: countries){
            if(c.infected>0){
//                System.out.println(c+" "+c.infected_today.get(time - 1));
                double cure_today;
                double infected_that_day;
                double die_today;

                if(!(c.infected_today.get(time - this.sick_time)==null )){
                    infected_that_day = (c.infected_today.get(time - this.sick_time));
                    cure_today = Math.round(infected_that_day*(c.death_resistance/100));
                    die_today = Math.round(infected_that_day*(((100-c.death_resistance)/100)));
////                    System.out.println(this.mortality);
                    System.out.println(c+" Infected " +infected_that_day);
                    System.out.println(c+" Cured " +cure_today);
                    System.out.println(c+" Die " +die_today);
//                    c.healthy+=cure_today;
                    c.infected-=infected_that_day;
                    c.cured+=cure_today;
                    c.dead+=die_today;
                    c.infected_today.remove(time - this.sick_time);
                }
            }
            if(c.infected<0){
                c.infected=0;
            }
        }
    }
}

