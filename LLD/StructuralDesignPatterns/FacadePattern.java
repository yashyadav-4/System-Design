package LLD.StructuralDesignPatterns;

// service class responsible for handling payments
class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of ₹" + amount + " successful for account " + accountId);
    }
}

// service class responsible for reserving seats
class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}

// service class responsible for sending notifications
class NotificationService{
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

// service class for managing loyalty/reward points
class LoyaltyPointsService{
    public void addPoints(String accountId, int points){
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

// service class for generating movie tickets
class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}

// Facade class

class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;


    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(String accountId, String movieId , String seatNumber , String userEmail , double amount){

        paymentService.makePayment(accountId, amount);
        seatReservationService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        loyaltyPointsService.addPoints(accountId , 5);
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("movie ticket booking successful :) ");
    }
}

public class FacadePattern {
    public static void main(String[] args) {

        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovieTicket("Yash" , "dhurandhar" , "b46" , "yash@cppro.dev" , 600);
    }
}
