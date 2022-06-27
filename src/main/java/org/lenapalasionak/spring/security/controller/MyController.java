package org.lenapalasionak.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
     public String getInfoForAllEmps() {
return "view_for_all_employees";
     }

    @GetMapping("/hr-info")
    public String getInfoForOnlyForHR() {
        return "view_for_hr";
    }

    @GetMapping("/manager-info")
    public String getInfoForOnlyForManagers() {
        return "view_for_managers";
    }
}
//A a !