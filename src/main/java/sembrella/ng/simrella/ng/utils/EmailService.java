package sembrella.ng.simrella.ng.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void  sendRegistrationEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Registration Successful!");
        message.setText("Dear " + username + ",\n\nThank you for registering with us!\n\nBest regards,\nTech team");
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);
    }
}