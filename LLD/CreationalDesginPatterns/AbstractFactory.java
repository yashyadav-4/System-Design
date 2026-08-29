package LLD.CreationalDesginPatterns;

// ==================== Interfaces ===============================
interface PaymentGateway{
    void processPayment(double amount);
}

interface Invoice{
    void generateInvoice();
}


// =================== India implementation ===========================
class RazorpayGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: " + amount);
    }
}

class PayUGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: " + amount);
    }
}

class GSTInvoice implements Invoice{
    @Override
    public void generateInvoice(){
        System.out.println("Generating GST Invoice for India.");
    }
}


// ====================== US Implementation =======================

class PayPalGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing USD payment via PayPal: " + amount);
    }
}

class StripeGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing USD payment via Stripe: " + amount);
    }
}

class USInvoice implements Invoice{
    public void generateInvoice(){
        System.out.println("Generating Invoice as per US norms.");
    }
}


// ============= Abstract Factory ==============

interface RegionFactory{
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}


// =============== Concrete Factories ==================

class IndiaFactory implements RegionFactory{
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if(gatewayType.equalsIgnoreCase("razorpay")){
            return new RazorpayGateway();
        }else if(gatewayType.equalsIgnoreCase("payu")){
            return new PayUGateway();
        }
        throw new IllegalArgumentException("Unsupported gate for india : " + gatewayType);
    }

    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory implements RegionFactory{
    public PaymentGateway createPaymentGateway(String gatewayType){
        if(gatewayType.equalsIgnoreCase("paypal")) {
            return new PayPalGateway();
        }else if(gatewayType.equalsIgnoreCase("stripe")){
            return new StripeGateway();
        }
        throw new IllegalArgumentException("Unsupported gateway for US: " + gatewayType);
    }

    public Invoice createInvoice(){
        return new USInvoice();
    }
}


// =============== Checkout Service =======================

class CheckoutService{
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    private String gatewayType;

    public CheckoutService(RegionFactory factory , String gatewayType){
        this.gatewayType = gatewayType;
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {

        //india
        CheckoutService indiaCheckout = new CheckoutService(new IndiaFactory(), "razorpay");
        indiaCheckout.completeOrder(1999.0);

        //us
        CheckoutService usCheckout = new CheckoutService(new USFactory(), "paypal");
        usCheckout.completeOrder(49.99);
    }
}
