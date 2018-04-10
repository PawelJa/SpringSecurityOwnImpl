package pl.ssdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ssdemo.entity.User;
import pl.ssdemo.repository.UserRepository;
import pl.ssdemo.security.MyUserDetailsService;

@Controller
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/test")
    public String test() {
        User user = userRepository.findOneByUsername("Paul");
        System.out.println(user);
        myUserDetailsService.loadUserByUsername("Paul");
        return "test";
    }

    @GetMapping("/newuser")
    @ResponseBody
    public String newUser() {
        User user = new User();
        user.setUsername("Paul");
        user.setPassword("abc123");
        user.setEnabled(true);
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
        System.out.println(user);
        return "dodano uzytkownika";
    }
}
