package az.test.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Controller
@RequiredArgsConstructor
public class JspController {

    @GetMapping("/time")
    public String time(Model model) {
        model.addAttribute("time", ""+System.currentTimeMillis());
        model.addAttribute("isoDateTime", ISO_DATE_TIME.format(LocalDateTime.now()));

        return "time";
    }
}
