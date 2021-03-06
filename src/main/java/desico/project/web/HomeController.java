package desico.project.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }
    @GetMapping("/offer")
    public String ourOffer() {
        return "offer";
    }


}