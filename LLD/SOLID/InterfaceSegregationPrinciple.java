package LLD.SOLID;

interface RiderInterfacee{
    void bookRide();
    void payRide();
}

interface DriverInterface{
    void acceptRide();
    void drive();
    void endRide();
}

class Rider implements RiderInterfacee{

    public void bookRide(){

    }
    public void payRide(){

    }
}

class Driver implements  DriverInterface{
    public void acceptRide(){

    }
    public void drive(){

    }
    public void endRide(){

    }
}

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {

    }
}
