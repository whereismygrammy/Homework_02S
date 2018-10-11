package pl.swiatek.homework_2.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HeaderController {

    @GetMapping("/showUserAgent")
    public String showUserAgent(@RequestHeader("user-agent") String userAgent, Model model){
        model.addAttribute("ua", userAgent);
        return "userAgent";
    }
}
