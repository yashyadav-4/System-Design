package LLD.CreationalDesginPatterns;

interface Logistics{
    void send();
}

class Road implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by road");
    }
}

class Air implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by air");
    }
}

class Train implements Logistics{
    @Override
    public void send(){
        System.out.println("Sending by Train");
    }
}

// separating logic of object creation using factory method so logisticsService class doesn't ignore single responsibility principle rule
class LogisticsFactory{
    public static Logistics getLogistics(String mode){
        if(mode == "road"){
            return new Road();
        }
        else if(mode ==  "train") return new Train();
        else return new Air();
    }
}

class LogisticsService{
    public void send(String mode){
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        LogisticsService logisticsService = new LogisticsService();
        logisticsService.send("road");
    }
}
