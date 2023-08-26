package std.dashrock.registrationlogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import std.dashrock.registrationlogin.service.EmailSendService;

@Service("emailSenderService")
public class EmailSendServiceImpl implements EmailSendService{
    
    private JavaMailSender javaMailSender;

    /**
     * @param javaMailSender
     */
     @Autowired
    public EmailSendServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }
    
}
