package LLD.StructuralDesignPatterns;

interface Pizza{
    String getDescription();
    double getCost();
}

class PlainPizza implements Pizza{
    @Override
    public String getDescription(){
        return "Plain Pizza";
    }

    @Override
    public double getCost(){
        return 150.00;
    }
}

class MargheritaPizza implements Pizza{
    @Override
    public String getDescription(){
        return "Margerita Pizza";
    }

    @Override
    public double getCost(){
        return 200.00;
    }
}

abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

class ExtraCheese extends PizzaDecorator{

    public ExtraCheese(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 40.0;
    }
}

class Olives extends PizzaDecorator{

    public Olives(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 30.0;
    }
}

class StuffedCrust extends PizzaDecorator {
    public StuffedCrust(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Stuffed Crust";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {

        Pizza pizza = new MargheritaPizza();

        pizza = new ExtraCheese(pizza);

        pizza = new Olives(pizza);

        pizza = new StuffedCrust(pizza);

        System.out.println("Pizza Description: " + pizza.getDescription());
        System.out.println("Total Cost: ₹" + pizza.getCost());
    }
}
