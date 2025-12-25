package com.expense.tracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
    @RequestMapping("/greet")
    public String greet()
    {
        return "hello";
    }
}
