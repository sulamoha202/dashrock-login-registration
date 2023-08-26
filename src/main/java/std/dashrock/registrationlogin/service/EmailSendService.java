package std.dashrock.registrationlogin.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSendService {
    
    public void sendEmail(SimpleMailMessage email);
}
