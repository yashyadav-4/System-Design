package LLD.SOLID;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class UserRepository {
    public void save(User user) {
        System.out.println("Saving " + user.getName() + " to database...");
    }
}

class EmailService {
    public void sendEmail(User user) {
        System.out.println("Sending email to " + user.getEmail());
    }
}

class UserReport {
    public void generate(User user) {
        System.out.println("Generating report for " + user.getName());
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        User user = new User("Yash", "yash@example.com");

        UserRepository repository = new UserRepository();
        EmailService emailService = new EmailService();
        UserReport report = new UserReport();

        repository.save(user);
        emailService.sendEmail(user);
        report.generate(user);
    }
}
