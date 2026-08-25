package LLD.SOLID;

///*
//open close principle
interface TaxCalculator{
    public double amountAfterTax(double amount);
}

class IndianTax implements TaxCalculator{
    public double amountAfterTax(double amount){
        return (0.18 * amount);
    }
}

class UsTax implements TaxCalculator{
    public double amountAfterTax(double amount){
        return (0.28 * amount);
    }
}

class Invoice{
    private double amount;
    private TaxCalculator taxCalculator;
    public Invoice(double amount , TaxCalculator taxCalculator){
        this.amount = amount;
        this.taxCalculator = taxCalculator;
    }

    public double getTotalAmount(){
        return amount + taxCalculator.amountAfterTax(amount);
    }
}
//*/
public class OpenClosePrinciple {
    public static void main(String[] args) {

        double amount = 100;

        Invoice indiaInvoice = new Invoice(amount , new IndianTax());
        System.out.println("total :" +indiaInvoice.getTotalAmount());
    }
}


