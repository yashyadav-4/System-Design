package LLD.CreationalDesginPatterns;

import java.util.HashMap;
import java.util.Map;

interface EmailTemplate extends Cloneable{
    EmailTemplate clone();
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome...";
        this.content = "Hello....";
    }

    @Override
    public WelcomeEmail clone(){
        try {
            return(WelcomeEmail) super.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public void setContent(String content){
        this.content = content;
    }

    @Override
    public void send(String to){
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}

class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        templates.put("welcome" , new WelcomeEmail());
    }
    public static EmailTemplate getTemplate(String type){
        return templates.get(type).clone(); // clone to avoid modifying original
    }

}

public class PrototypePattern {
    public static void main(String[] args) {

        EmailTemplate welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Hi user, welcome to cppro");
        welcomeEmail1.send("user@cppro.dev");

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("hi Bob, thanks for joining");
        welcomeEmail2.send("bob@cppro.dev");

    }
}
