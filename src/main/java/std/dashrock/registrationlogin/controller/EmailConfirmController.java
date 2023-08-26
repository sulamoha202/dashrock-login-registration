package std.dashrock.registrationlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import std.dashrock.registrationlogin.dto.UserDto;
import std.dashrock.registrationlogin.entity.ConfirmationToken;
import std.dashrock.registrationlogin.entity.User;
import std.dashrock.registrationlogin.repository.ConfirmationTokenRepository;
import std.dashrock.registrationlogin.repository.UserRepository;
import std.dashrock.registrationlogin.service.EmailSendService;
import std.dashrock.registrationlogin.service.UserService;

@Controller
public class EmailConfirmController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSendService emailSendService;

    @RequestMapping(value="/api/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView,User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register2");
        return modelAndView;
    }

    @RequestMapping(value = "api/register/save", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView,UserDto userDto){
        User user = userService.setUser(userDto);
        User existingUser = userService.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null){
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        }else{
            userService.saveUser(userDto);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage =  new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("dashrockstaff@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "+
            "http://localhost:8080/confirm-account?tocken="+ confirmationToken.getConfirmationToken());
            emailSendService.sendEmail(mailMessage);
            modelAndView.addObject("email:" , user.getEmail());
            modelAndView.setViewName("successfulRegistration");

        }
        
        return modelAndView;
    }
    
    @RequestMapping(value="/api/confirm-account", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView,@RequestParam("token")String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token !=null){
            User user = userService.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userService.saveUser(user);
            modelAndView.setViewName("accountVerified");
        }else{
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }



}
