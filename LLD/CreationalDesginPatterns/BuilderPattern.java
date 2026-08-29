package LLD.CreationalDesginPatterns;

import java.util.List;

class BurgerMeal{
    //mandatory
    private final String bunType;
    private final String patty;

    //optional
    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    private BurgerMeal(BurgerBuilder burgerBuilder){
        this.bunType = burgerBuilder.bunType;
        this.patty = burgerBuilder.patty;
        this.hasCheese = burgerBuilder.hasCheese;
        this.side = burgerBuilder.side;
        this.toppings = burgerBuilder.toppings;
        this.drink = burgerBuilder.drink;
    }

    public static class BurgerBuilder{
        private final String bunType;
        private final String patty;

        private boolean hasCheese;
        private List<String> toppings;
        private String side;
        private String drink;

        public BurgerBuilder(String bunType , String patty){
            this.bunType = bunType;
            this.patty = patty;
        }

        public BurgerBuilder withCheese(boolean hasCheese){
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withToppings(List<String> toppings){
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide(String side){
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink(String drink){
            this.drink = drink;
            return this;
        }
        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }

}

public class BuilderPattern {
    public static void main(String[] args) {
        BurgerMeal burger1 = new BurgerMeal.BurgerBuilder("wheat" , "veg").build();
        BurgerMeal burger2 = new BurgerMeal.BurgerBuilder("wheat" , "veg").withCheese(true).withDrink("thumbs Up").build();

    }
}


// if this burger builder can be used outside u can keep it outside the class too rather than inner class but don't do if not necessary