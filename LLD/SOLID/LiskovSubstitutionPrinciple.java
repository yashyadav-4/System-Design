package LLD.SOLID;

class Notification{
    public void sendNotification(){
        System.out.println("Email Notfication sent");
    }
}

class TextNotfication extends  Notification{
    @Override
    public void sendNotification(){
        System.out.println("Text Notification sent");
    }
}

//substituting parent class with child class doesn't break its behavior
public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        Notification notification = new TextNotfication();
        notification.sendNotification();
    }
}
