package LLD.BehavioralDesignPatterns;

import java.util.*;

interface Subscriber{
    void update(String videoTitle);
}

class EmailSubscriber implements Subscriber{
    private String email;

    public EmailSubscriber(String email){
        this.email = email;
    }

    @Override
    public void update(String videoTitle){
        System.out.println("Email sent to " + email + ": New video uploaded - " + videoTitle);
    }
}

class MobileAppSubscriber implements Subscriber {
    private String username;

    public MobileAppSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("In-app notification for " + username + ": New video - " + videoTitle);
    }
}


interface Channel{
    void subscribe(Subscriber subscriber);
    void unSubscribe(Subscriber subscriber);
    void notifySubscriber(String videoTitle);
}

class YoutubeChannel implements Channel{
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YoutubeChannel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(String videoTitle){
        for(Subscriber sub: subscribers){
            sub.update(videoTitle);
        }
    }

    public void uploadVideo(String videoTitle){
        System.out.println(this.channelName + " uploaded: " + videoTitle + "\n");
        notifySubscriber(videoTitle);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        YoutubeChannel tuf = new YoutubeChannel("CPPro");

        tuf.subscribe(new MobileAppSubscriber("Yash"));
        tuf.subscribe(new EmailSubscriber("Lemon"));

        // Upload video and notify all observers
        tuf.uploadVideo("observer-pattern");
    }
}
