package zhipong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author zhipong
 * @Description TODO
 * @Date 2021/5/6
 * @Version Maven 3.6.3
 * @Version Spring 5.3.6
 * @Version SpringBoot 2.4.5
 */
@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
