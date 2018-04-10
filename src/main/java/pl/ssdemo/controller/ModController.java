package pl.ssdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mod")
public class ModController {

    @GetMapping("/secured")
    public String userView() {
        return "secured/mod";
    }
}
