package pl.ssdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ssdemo.entity.User;
import pl.ssdemo.entity.UserRole;
import pl.ssdemo.repository.UserRepository;
import pl.ssdemo.repository.UserRoleRepository;
import pl.ssdemo.security.MyUserDetailsService;

@Controller
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
        UserRole userRole = new UserRole();
        userRole.setRole("ADMIN");
        user.setUserRole(userRole);
        userRoleRepository.save(userRole);
        userRepository.save(user);
        System.out.println(user);
        return "dodano uzytkownika";
    }

    @GetMapping("/newuser2")
    @ResponseBody
    public String newUser2() {
        User user = new User();
        user.setUsername("Paul2");
        user.setPassword(bCryptPasswordEncoder.encode("abc1234"));
        user.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setRole("USER");
        user.setUserRole(userRole);
        userRoleRepository.save(userRole);
        userRepository.save(user);
        System.out.println(user);
        return "dodano uzytkownika";
    }
}
