package LLD.StructuralDesignPatterns;

interface PaymentGateway{
    void pay(String orderId , double amount);
}

class PayUGateway implements PaymentGateway{
    @Override
    public void pay(String orderId , double amount){
        System.out.println("Paid Rs." + amount + " using PayU for order: " + orderId);
    }
}

class RazorpayApi{
    public void makePayment(String invoiceId, double amountInRupees){
        System.out.println("Paid Rs." + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

class RazorpayAdaptor implements PaymentGateway{
    private RazorpayApi razorpayApi;

    public RazorpayAdaptor(){
        this.razorpayApi = new RazorpayApi();
    }

    @Override
    public void pay(String orderId , double amount){
        razorpayApi.makePayment(orderId , amount);
    }
}

class CheckoutService{
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId , double amount){
        paymentGateway.pay(orderId , amount);
    }
}

public class AdaptorPattern {
    public static void main(String[] args) {

        CheckoutService checkoutService = new CheckoutService(new RazorpayAdaptor());
        checkoutService.checkout("12413" , 54);
    }
}
